package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.mapper.TransactionMapper;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.repository.TransactionRepository;
import com.alkemy.wallet.repository.UserRepository;
import com.alkemy.wallet.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    private TransactionRepository repo;
    private TransactionMapper mapper;

    private UserRepository userRepo;

    private UserMapper userMapper;

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
}
