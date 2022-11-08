package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.model.Account;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.CurrencyEnum;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionRepository iTransactionRepository;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IUserService iUserService;
    @Override
    public TransactionDto sendUsd(long accountFromId, long userId, double amount, long accountToId, Type type) throws Exception {
        Account accountFrom = iAccountService.findById(accountFromId);
        Account accountTo = iAccountService.findById(accountToId);
        //TODO -> Reemplazar por el usuario autenticado
        User userFrom = iUserService.findById(userId);
        User userTo = accountTo.getUserId();

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

    private void validateTransferCanProceed(Account accountFrom, Account accountTo, User userFrom, User userTo, double amount, Type type) throws Exception {
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
}
