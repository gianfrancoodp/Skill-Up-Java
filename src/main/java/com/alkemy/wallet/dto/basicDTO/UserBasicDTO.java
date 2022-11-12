package com.alkemy.wallet.dto.basicDTO;

import com.alkemy.wallet.dto.AccountDto;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserBasicDTO   extends RepresentationModel<AccountDto> {
    private long id;
    private String firstName;
    private String lastName;
    private String email;


}
