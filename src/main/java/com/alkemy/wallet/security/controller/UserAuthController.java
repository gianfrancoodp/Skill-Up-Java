package com.alkemy.wallet.security.controller;

import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.security.dto.AuthenticationRequestDto;
import com.alkemy.wallet.security.dto.AuthenticationResponseDto;
import com.alkemy.wallet.security.service.IUserAuthService;
import com.alkemy.wallet.security.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private IUserAuthService userAuthService;
     private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    

    @Autowired
    public UserAuthController(
            IUserAuthService userAuthService,
            AuthenticationManager authenticationManager,           
            JwtUtils jwtUtils) {
        this.userAuthService = userAuthService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> singUp(@Valid @RequestBody UserDto user) throws Exception{
        this.userAuthService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> singIn(@RequestBody AuthenticationRequestDto authRequest) throws Exception{
        UserDetails userDetails;
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword(), Collections.emptyList())
            );
            userDetails = (UserDetails) auth.getPrincipal();

        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username o password",e);
        }

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(jwt));
    }
}

