package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.FixedTermDepositDto;
import com.alkemy.wallet.dto.basicDTO.FixedTermDepositDtoSimulation;
import com.alkemy.wallet.exception.CreateNewTransactionFailException;
import com.alkemy.wallet.exception.InsufficientFundForFixedTermDepositException;
import com.alkemy.wallet.exception.InvalidFixedTermDepositTimeException;
import com.alkemy.wallet.exception.NonOwnAccountException;
import com.alkemy.wallet.mapper.FixedTermDepositMapper;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.IFixedTermDepositRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IFixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static com.alkemy.wallet.util.Type.*;

@Service
public class FixedTermDepositServiceImpl implements IFixedTermDepositService {

    @Autowired
    private IFixedTermDepositRepository fixedTermDepositRepository;
    private FixedTermDepositMapper fixedTermDepositMapper;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public FixedTermDepositDto save(FixedTermDepositDto dto, String userName) throws Exception {
        // First, is necessary to find the user with the "UserName" input
        List<UserEntity> users = userRepository.findAll();
        Optional<UserEntity> user = users.stream().filter(u -> u.getEmail().equals(userName)).findFirst();
        // So, we have to find the Account with the accountId input by the authenticated user
        Account account = accountRepository.getReferenceById(dto.getAccountId());
        // Also, we need to validate the ClosingDate. It must be greater than 30 days.
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime dateAllowed = now.plusDays(+30);
        ZonedDateTime closingDate = ZonedDateTime.parse(dto.getClosingDate());

        if (!account.getUserId().equals(user)) { // User Validation
            throw new NonOwnAccountException("The selected account does not belong to you.");
        } if (account.getBalance() < dto.getAmount()) { // Balance Validation
            throw new InsufficientFundForFixedTermDepositException("You do not have sufficient founds to create a new Fixed-Term Deposit.");
        } if (closingDate.toInstant().isBefore(dateAllowed.toInstant())) { // Date Validation
            throw new InvalidFixedTermDepositTimeException("The closing date must be greater than 30 days.");
        }

        // Once the DTO has been validated, is necessary to convert the DTO to Entity
        FixedTermDeposit entity = fixedTermDepositMapper.fixedTermDepositDTO2Entity(dto, account.getAccountId(), user.get().getId());

        // Let´s create a new Transaction
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setUser(user.get());
        transaction.setType(payment);
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        transaction.setDescription("New Fixed-Term Deposit.");
        try {
            Transaction transactionSaved = transactionService.savePayment(transaction);
        } catch (Exception e) {
            throw new CreateNewTransactionFailException("The transaction could not be created.", e);
        }

        // Let´s save the Fixed-Term Deposit in the database
        FixedTermDeposit entitySave = fixedTermDepositRepository.save(entity);

        // Finally, the result of the DTO is returned to the Controller
        FixedTermDepositDto newDto = fixedTermDepositMapper.fixedTermDepositEntity2DTO(entitySave);
        return newDto;
    }

    public String accreditFixedTermDeposit(long fixedTermDepositId, String userName) throws Exception {

        // First, is necessary to create an instance of Fixed-Term Deposit
        FixedTermDeposit fixedTermDeposit = fixedTermDepositRepository.getReferenceById(fixedTermDepositId);
        // Then, let´s save the actual date to validate with the Fixed-Term Deposit ClosingDate
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        // After that, let´s check if the userName is the real owner of the Fixed-Term Deposit
        List<UserEntity> users = userRepository.findAll();
        Optional<UserEntity> user = users.stream().filter(u -> u.getEmail().equals(userName)).findFirst();

        if (user.get().getId() != (fixedTermDeposit.getUserId())) { // User Validation
            throw new NonOwnAccountException("The selected Fixed-Term Deposit does not belong to you.");
        } if (now.before(fixedTermDeposit.getClosingDate())) { // Credit Date Validation
            return "You cannot credit the Fixed-Term Deposit now. The accreditation will be available from " +fixedTermDeposit.getClosingDate();
        }

        // Let´s create the new transaction to credit the Fixed-Term Deposit
        Transaction creditedFixedTermDeposit = new Transaction();
        // In this case, is necessary to calculate compound interest of the Fixed-Term Deposit from the CreationDate to the ClosingDate.
        long daysBetween = Duration.between(LocalDateTime.now(), fixedTermDeposit.getClosingDate().toInstant()).toDays();
        // We have to fill the Transactions fields
        creditedFixedTermDeposit.setType(income);
        creditedFixedTermDeposit.setDescription("The Fixed-Term Deposit is being processed in your account");
        creditedFixedTermDeposit.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        creditedFixedTermDeposit.setUser(user.get());
        creditedFixedTermDeposit.setAccount(fixedTermDeposit.getAccount());
        // Let´s calculate the compound interest
        double result = calculateCompoundInterest(fixedTermDeposit.getAmount(), daysBetween, fixedTermDeposit.getInterest());
        creditedFixedTermDeposit.setAmount(result);
        // Is necessary to save the Transaction entity using the Repository interface
        transactionService.saveDeposit(creditedFixedTermDeposit);
        // Finally, the Credit Fixed-Term Deposit status is returned as a String to the user
        return "The Fixed-Term Deposit was correctly credited to your account. You have received a income for " +result+ " " +fixedTermDeposit.getAccount().getCurrency();
    }

    public FixedTermDepositDtoSimulation simulateFixedTermDeposit(FixedTermDepositDtoSimulation dto) throws Exception {
        FixedTermDepositDtoSimulation simulation = new FixedTermDepositDtoSimulation();
        // As a first step, we need to calculate the number of days
        long duration = Duration.between(LocalDate.parse(dto.getCreationDate()), LocalDate.parse(dto.getClosingDate())).toDays();
        // Let´s create a temporal entity to get the interest value, since we don´t want to hardcode it.
        FixedTermDeposit temp = new FixedTermDeposit();
        // Now, let´s calculate the total earned amount (including the compound interest)
        double totalEarned = calculateCompoundInterest(dto.getAmount(), duration, temp.getInterest());
        // Then, with the total earned value we can get the earned interest amount.
        double interestEarned = totalEarned - dto.getAmount();
        // Finally, let´s fill the result properties of the DTO Simulation.
        simulation.setAmount(dto.getAmount());
        simulation.setInterestEarned(interestEarned);
        simulation.setTotalEarned(totalEarned);
        simulation.setCreationDate(dto.getCreationDate());
        simulation.setClosingDate(dto.getClosingDate());
        return simulation;
    }

    @Override
    public List<FixedTermDepositDto> getAll() {
        List<FixedTermDeposit> fixedTermDepositEntities = fixedTermDepositRepository.findAll();
        List<FixedTermDepositDto> fixedTermDepositDtos = fixedTermDepositMapper.fixedTermDepositEntityList2DTOList(fixedTermDepositEntities);
        return fixedTermDepositDtos;
    }

    @Override
    public FixedTermDepositDto getById(long id) {
        FixedTermDeposit entity = fixedTermDepositRepository.getReferenceById(id);
        FixedTermDepositDto newDto = fixedTermDepositMapper.fixedTermDepositEntity2DTO(entity);
        return newDto;
    }

    @Override
    public List<FixedTermDeposit> findAll() {
        return fixedTermDepositRepository.findAll();
    }

    public double calculateCompoundInterest(double amount, long days, double interest) {
        int count = 0;
        double result = amount;
        while (count < days) {
            result = (result * interest) + result;
            count++;
        }
        return result;
    }
}
