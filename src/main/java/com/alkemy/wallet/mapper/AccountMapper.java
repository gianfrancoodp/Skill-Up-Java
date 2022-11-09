package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.model.Account;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
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
        entity.setBalance(accountDto.getBalance());
        entity.setTransactionLimit(accountDto.getTransactionLimit());
        entity.setCurrency(accountDto.getCurrency());
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
  /*  private ModelMapper modelMapper = new ModelMapper();


    public AccountDto map(Account account){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(account, AccountDto.class);
    }

    public Account map(AccountDto accountBasicDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return this.modelMapper.map(accountBasicDTO, Account.class);
    }
    public List<AccountDto> mapListDto(List<Account> accounts){
        List<AccountDto> dtos = new ArrayList<>();
        for(Account account:accounts){
            dtos.add(this.map(account));
        }
        return dtos;
    }
    public List<Account> mapListEntity(List<AccountDto> dtos){
        List<Account> accounts = new ArrayList<>();
        for(AccountDto dto:dtos){
            accounts.add(this.map(dto));
        }
        return accounts;
    }*/



}
