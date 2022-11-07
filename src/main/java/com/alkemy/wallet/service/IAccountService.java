package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.AccountDto;

import java.util.List;

public interface IAccountService {

    public AccountDto createAccount(AccountDto request, Long idUser);

    public List<AccountDto> accountList(Long idUser);

    public String myBalance(Long idUser);



}
