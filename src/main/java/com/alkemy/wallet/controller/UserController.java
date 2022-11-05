package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
   private IUserService userService;


    @GetMapping("/users")
    public List<UserBasicDTO> getUsers() {
        return userService.getUsers();}

    @GetMapping("accounts/userId")


    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUserById (@PathVariable Long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.ok().build();

    }}