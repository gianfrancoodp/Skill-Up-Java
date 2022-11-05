package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.repository.UserRepository;
import com.alkemy.wallet.service.IUserService;
import com.alkemy.wallet.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/wallet")


public class UserController {

    @Autowired
   private IUserService userService;


    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return userService.getUsers();}

    @GetMapping("accounts/userId")


    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUserById (@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();

    }}