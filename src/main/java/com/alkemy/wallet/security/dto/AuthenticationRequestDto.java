package com.alkemy.wallet.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDto {
    @Email(message = "Username must be an email")
    private String username;
    @Size(min = 6)
    private String password;
}