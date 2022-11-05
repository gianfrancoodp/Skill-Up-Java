package com.alkemy.wallet.dto;

import lombok.Data;

@Data
public class git UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long roleId;
    private boolean deleted;
}
