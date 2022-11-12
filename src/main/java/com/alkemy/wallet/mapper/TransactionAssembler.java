package com.alkemy.wallet.mapper;

import com.alkemy.wallet.controller.TransactionController;
import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.model.Transaction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TransactionAssembler extends RepresentationModelAssemblerSupport<Transaction, TransactionDto> {

    public TransactionAssembler() {
        super(TransactionController.class, TransactionDto.class);
    }

    @Override
    public TransactionDto toModel(Transaction transaction){
        TransactionDto transactionDto = instantiateModel(transaction);
        return transactionDto;
    }

    @Override
    public CollectionModel<TransactionDto> toCollectionModel(Iterable<? extends Transaction> entities)
    {
        CollectionModel<TransactionDto> transactions = super.toCollectionModel(entities);

        return transactions;
    }

}
