package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.TransactionPaymentDto;
import com.alkemy.wallet.model.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class TransactionMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public TransactionPaymentDto entityToDto(Transaction transaction){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TransactionPaymentDto result = this.modelMapper.map(transaction, TransactionPaymentDto.class);
        return result;
    }

    public Transaction dtoToEntity(TransactionPaymentDto transaction){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Transaction result = this.modelMapper.map(transaction, Transaction.class);
        return result;
    }
}
