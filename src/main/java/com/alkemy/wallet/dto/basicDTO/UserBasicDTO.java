package com.alkemy.wallet.dto.basicDTO;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserBasicDTO   extends RepresentationModel<UserBasicDTO> {
    private long id;
    private String firstName;
    private String lastName;
    private String email;


}
