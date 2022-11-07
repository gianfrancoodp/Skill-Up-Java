package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AccountMapper {

    public AccountDto map(Account account){
        AccountDto response = new AccountDto();
        response.setTransactionLimit(account.getTransactionLimit());
        response.setCurrency(account.getCurrency());
        response.setBalance(account.getBalance());
        response.setCreationDate(account.getCreationDate());
        response.setUpdateDate(account.getUpdateDate());
        return response;
    }

    public Account map(AccountDto accountDto){
        Account entity = new Account();
        entity.setBalance(null);
        entity.setTransactionLimit(accountDto.getTransactionLimit());
        if (accountDto.getCurrency().getValor().equals("USD")){
            entity.setCurrency(accountDto.getCurrency());
        } else {
            entity.setCurrency(accountDto.getCurrency());
        }
        entity.setCreationDate(accountDto.getCreationDate());
        entity.setUpdateDate(accountDto.getUpdateDate());
        return entity;
    }

    public List<AccountDto> map(List<Account> accountList){

        List<AccountDto> listResponse = new ArrayList<>();

        for (Account aux: accountList) {
            listResponse.add(map(aux));
        }

        return listResponse;
    }

}
