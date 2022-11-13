package com.alkemy.wallet.mapper;

import com.alkemy.wallet.controller.AccountController;
import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.model.Account;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class AccountAssembler extends RepresentationModelAssemblerSupport<Account, AccountDto> {

    public AccountAssembler() {
        super(AccountController.class, AccountDto.class);
    }

    @Override
    public AccountDto toModel(Account account){
        AccountDto accountDto = instantiateModel(account);
        return accountDto;
    }

    @Override
    public CollectionModel<AccountDto> toCollectionModel(Iterable<? extends Account> entities)
    {
        CollectionModel<AccountDto> accounts = super.toCollectionModel(entities);

        return accounts;
    }


}
