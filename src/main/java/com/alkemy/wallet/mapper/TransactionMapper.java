package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.model.Transaction;
import org.springframework.stereotype.Component;


import com.alkemy.wallet.dto.TransactionPaymentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;


@Component
public class TransactionMapper {
    public TransactionDto map(Transaction transaction) {
        TransactionDto response = new TransactionDto();
        response.setAmount(transaction.getAmount());
        response.setType(transaction.getType());
        response.setDescription(transaction.getDescription());
        response.setTransactionDate(transaction.getTransactionDate());
        return response;
    }

    public Transaction map(TransactionDto transaction) {
        Transaction entity = new Transaction();
        entity.setAmount(transaction.getAmount());
        entity.setType(transaction.getType());
        entity.setDescription(transaction.getDescription());
        entity.setTransactionDate(transaction.getTransactionDate());
        return entity;
    }

    private ModelMapper modelMapper = new ModelMapper();

    public TransactionPaymentDto entityToDto(Transaction transaction) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TransactionPaymentDto result = this.modelMapper.map(transaction, TransactionPaymentDto.class);
        return result;
    }

    public Transaction dtoToEntity(TransactionPaymentDto transaction) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Transaction result = this.modelMapper.map(transaction, Transaction.class);
        return result;
    }

}
