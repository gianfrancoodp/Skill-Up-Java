package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.util.List;

public interface IAccountService {

    public AccountDto createAccount(AccountDto request, Long idUser);

    public List<AccountDto> accountList(Long idUser);

    public Double myBalance(Long idUser);



}
