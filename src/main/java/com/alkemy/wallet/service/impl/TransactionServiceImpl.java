package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.CurrencyEnum;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private TransactionMapper mapper;

    @Autowired
    private IUserRepository userRepo;
    private UserMapper userMapper;
    @Autowired
    private ITransactionRepository repo;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITransactionRepository iTransactionRepository;


    @Override
    public TransactionDto sendUsd(long accountFromId, long userId, double amount, long accountToId, Type type) throws Exception {
        Account accountFrom = accountService.findById(accountFromId);
        Account accountTo = accountService.findById(accountToId);
        //TODO -> Reemplazar por el usuario autenticado
        UserEntity userFrom = userService.findById(userId);
        UserEntity userTo = accountTo.getUserId();

        validateTransferCanProceed(accountFrom, accountTo, userFrom, userTo, amount, type);
        //        if (!accountFrom.getUserId().equals(userFrom)){
//            throw new Exception("You are not the owner of the account");
//        }
//        if (accountFrom.equals(accountTo)){
//            throw new Exception("You can not send money to your own account");
//        }
//        if(accountFrom.getBalance()<amount){
//            throw new Exception("Insufficient balance");
//        }
//        if(accountFrom.getTransactionLimit()<amount){
//            throw new Exception("Transaction limit exceeded");
//        }
//        if(accountFrom.getCurrency()!= CurrencyEnum.USD){
//            throw new Exception("Your account is not a USD account");
//        }
//        if(accountTo.getCurrency()!= CurrencyEnum.USD){
//            throw new Exception("The destination account is not a USD account");
//        }
//        if(amount<=0){
//            throw new Exception("You can not make a transaction with 0 or a negative amount");
//        }
//        if(type!=Type.payment){
//            throw new Exception("To transfer money you have to select payment type");
//        }

        Transaction transactionFrom = new Transaction();
        transactionFrom.setUser(userFrom);
        transactionFrom.setAccount(accountFrom);
        transactionFrom.setAmount(-amount);
        transactionFrom.setType(type);
        transactionFrom.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        transactionFrom.setDescription(Type.payment + " to " + userTo.getLastName() + ", " + userTo.getFirstName() + ", account number: "+ accountTo.getAccountId());
        iTransactionRepository.save(transactionFrom);
        Transaction transactionTo = new Transaction();
        transactionTo.setUser(userTo);
        transactionTo.setAccount(accountTo);
        transactionTo.setAmount(amount);
        transactionTo.setType(Type.income);
        transactionTo.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
        transactionTo.setDescription(Type.income + " from " + userFrom.getLastName() + ", " + userFrom.getFirstName() + ", account number: "+ accountFrom.getAccountId());
        iTransactionRepository.save(transactionTo);
        return new TransactionDto(transactionFrom);
    }

    private void validateTransferCanProceed(Account accountFrom, Account accountTo, UserEntity userFrom, UserEntity userTo, double amount, Type type) throws Exception {
        if(userFrom==userTo){
            throw new Exception("You can not send money to yourself");
        }
        if (!accountFrom.getUserId().equals(userFrom)){
            throw new Exception("You are not the owner of the account");
        }
        if (accountFrom.equals(accountTo)){
            throw new Exception("You can not send money to your own account");
        }
        if(accountFrom.getBalance()<amount){
            throw new Exception("Insufficient balance");
        }
        if(accountFrom.getTransactionLimit()<amount){
            throw new Exception("Transaction limit exceeded");
        }
        if(accountFrom.getCurrency()!= CurrencyEnum.USD){
            throw new Exception("This is not a USD account");
        }
        if(accountTo.getCurrency()!= CurrencyEnum.USD){
            throw new Exception("This is not a USD account");
        }
        if(amount<=0){
            throw new Exception("You can not make a transaction with 0 or a negative amount");
        }
        if(type!=Type.payment){
            throw new Exception("To transfer money you have to select payment type");
        }
    }

    @Override
    public List<TransactionDto> listUserId(long id){
        List<TransactionDto> list = new ArrayList<>();
        list.add(mapper.map(repo.findByid(id)));
        return list;
    }


    @Override
    public TransactionDto listDetail(long id) {
        TransactionDto transaction = mapper.map(repo.findByid(id));
        return transaction;
    }

    @Override
    public List<UserBasicDTO> listUser(){
        List<UserBasicDTO>  userList = userMapper.userEntity2DTOList(userRepo.findAll());
        return  userList;

    }

    @Override
    public TransactionDto edit (TransactionDto t){
        Transaction transaction = mapper.map(t);
         repo.save(transaction);
        return mapper.map(transaction);
    }



    /**
     * Method to persist a payment from an account in the database.
     * @param transaction
     * @return Transaction entity.
     * @throws Exception
     */
    @Override
    public Transaction savePayment(Transaction transaction) throws Exception {
        Transaction result;
        if(transaction.getAccount().getUserId().getId() == (transaction.getUser().getId())) {
            if (accountService.accountFunds(transaction)) {
                transaction.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
                transaction.setType(Type.payment);
                accountService.accountBalance(transaction);
                result = repo.save(transaction);
            } else
                throw new Exception("Account funds are insufficient.");
        }
        else
            throw new Exception("The account does not belong to the logged user.");
        return result;
    }

    /**
     * Method to save a deposit into an account and persists it in the database.
     * @param transaction
     * @return Transaction Entity
     * @throws Exception
     */
    @Override
    public Transaction saveDeposit(Transaction transaction) throws Exception {
        Transaction result;
        if(transaction.getAccount().getUserId().getId() == (transaction.getUser().getId())) {
            transaction.setTransactionDate(Timestamp.valueOf(LocalDateTime.now()));
            transaction.setType(Type.deposit);
            accountService.accountBalance(transaction);
            result = repo.save(transaction);
        }
        else
            throw new Exception("The account does not belong to the logged user.");
        return result;
    }



    @Override
    public List<Transaction> findByAccount(Long id) {

        return repo.findByAccount(id);

    }

    @Override
    public Page<Transaction> findByUser(Long userId, Pageable pageable) throws Exception {
        try {
            Page<Transaction> accounts = repo.findByUserId(userId, pageable);
            return accounts;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
