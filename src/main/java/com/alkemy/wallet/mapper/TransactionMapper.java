package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.basicDto.UserBasicDTO;
import com.alkemy.wallet.model.Transaction;
import com.alkemy.wallet.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapper {
    public TransactionDto map(Transaction transaction){
        TransactionDto response = new TransactionDto();
        response.setAmount(transaction.getAmount());
        response.setType(transaction.getType());
        response.setDescription(transaction.getDescription());
        response.setTransactionDate(transaction.getTransactionDate());
        return response;
    }

    public Transaction map(TransactionDto transaction){
        Transaction entity = new Transaction();
        entity.setAmount(transaction.getAmount());
        entity.setType(transaction.getType());
        entity.setDescription(transaction.getDescription());
        entity.setTransactionDate(transaction.getTransactionDate());
        return entity;
    }


}
