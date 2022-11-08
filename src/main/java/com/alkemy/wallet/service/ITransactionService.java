package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.util.Type;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface ITransactionService {


    TransactionDto sendUsd(long accountId, long userId, double amount, long accountToId, Type type) throws Exception;
}
