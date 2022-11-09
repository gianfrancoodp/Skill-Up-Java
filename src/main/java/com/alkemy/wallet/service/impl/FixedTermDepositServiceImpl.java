package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.FixedTermDepositDto;
import com.alkemy.wallet.exception.InsufficientFundForFixedTermDepositException;
import com.alkemy.wallet.exception.InvalidFixedTermDepositTimeException;
import com.alkemy.wallet.exception.NonOwnAccountException;
import com.alkemy.wallet.mapper.FixedTermDepositMapper;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.FixedTermDeposit;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.AccountRepository;
import com.alkemy.wallet.repository.FixedTermDepositRepository;
import com.alkemy.wallet.repository.UserRepository;
import com.alkemy.wallet.service.IFixedTermDepositService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public class FixedTermDepositServiceImpl implements IFixedTermDepositService {

    @Autowired
    private FixedTermDepositMapper fixedTermDepositMapper;
    @Autowired
    private FixedTermDepositRepository fixedTermDepositRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public FixedTermDepositDto save(FixedTermDepositDto dto, String userName) {
        // First, is necessary to find the user with the "UserName" input
        List<UserEntity> users = userRepository.findAll();
        Optional<UserEntity> user = users.stream().filter(u -> u.getEmail().equals(userName)).findFirst();
        // So, we have to find the Account with the accountId input by the authenticated user
        Account account = accountRepository.getReferenceById(dto.getAccountId());
        // Also, we need to validate the ClosingDate. It must be greater than 30 days.
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime dateAllowed = now.plusDays(+30);
        ZonedDateTime closingDate = ZonedDateTime.parse(dto.getClosingDate());

        if (!account.getUserEntityId().equals(user.get().getId())) {
            throw new NonOwnAccountException("The selected account does not belong to you.");
        }
        if (account.getBalance() < dto.getAmount()){
            throw new InsufficientFundForFixedTermDepositException("You do not have sufficient founds to create a new Fixed-Term Deposit.");
        }
        if (closingDate.toInstant().isBefore(dateAllowed.toInstant())) {
            throw new InvalidFixedTermDepositTimeException("The closing date must be greater than 30 days.");
        }

        // Finally, once the DTO has been validated, is necessary to convert the DTO to Entity
        FixedTermDeposit entity = fixedTermDepositMapper.fixedTermDepositDTO2Entity(dto, account.getAccountId(), user.get().getId());
        FixedTermDeposit entitySave = fixedTermDepositRepository.save(entity);
        FixedTermDepositDto newDto = fixedTermDepositMapper.fixedTermDepositEntity2DTO(entitySave);
        return newDto;
    }

    @Override
    public List<FixedTermDepositDto> getAll() {
        return null;
    }

    @Override
    public FixedTermDepositDto getById(long id) {
        return null;
    }

    @Override
    public FixedTermDepositDto update(long id, FixedTermDepositDto dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
