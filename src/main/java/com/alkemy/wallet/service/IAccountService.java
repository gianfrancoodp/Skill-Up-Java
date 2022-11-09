package com.alkemy.wallet.service;


import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.util.CurrencyEnum;

import java.util.List;

public interface IAccountService {

    public AccountDto createAccount(CurrencyEnum currency, long idUser) throws Exception;

    public List<AccountDto> accountList(long idUser) throws Exception;



}
