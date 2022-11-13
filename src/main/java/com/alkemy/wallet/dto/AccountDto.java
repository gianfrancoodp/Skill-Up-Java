package com.alkemy.wallet.dto;

import com.alkemy.wallet.util.CurrencyEnum;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;

@Data
public class AccountDto extends RepresentationModel<AccountDto> {

    private CurrencyEnum currency;
    private Double transactionLimit;
    private Double balance;
    private Timestamp creationDate;
    private Timestamp updateDate;
}
