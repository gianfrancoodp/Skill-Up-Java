package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IAccountService;
import com.alkemy.wallet.service.ITransactionService;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.util.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(transaction.getAccount().getUserId().equals(transaction.getUser().getId())) {
            if (accountService.limitTransactions(transaction) && accountService.accountFunds(transaction)) {
                transaction.setType(Type.payment);
                accountService.accountBalance(transaction);
                result = repo.save(transaction);
            } else
                throw new Exception("The diary transaction limit has been reached or your account funds are not enough");
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
        if(transaction.getAccount().getUserId().equals(transaction.getUser().getId())) {
            if (accountService.limitTransactions(transaction) && accountService.accountFunds(transaction)) {
                transaction.setType(Type.deposit);
                accountService.accountBalance(transaction);
                result = repo.save(transaction);
            } else
                throw new Exception("The diary transaction limit has been reached or your account funds are not enough");
        }
        else
            throw new Exception("The account does not belong to the logged user.");
        return result;
    }



    @Override
    public List<Transaction> findByAccount(Long id) {

        return repo.findByAccount(id);

    }







}
