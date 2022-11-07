package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.TransactionDto;
import com.alkemy.wallet.dto.basicDto.UserBasicDTO;
import com.alkemy.wallet.model.Transaction;

import java.util.List;

public interface ITransactionService {
    List<TransactionDto> listUserId(long id);

    TransactionDto listDetail(long id);

    List<UserBasicDTO> listUser();
   TransactionDto edit(TransactionDto t);
}
