package com.alkemy.wallet.controller;


import com.alkemy.wallet.model.UserEntity;

import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;

import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Returns a list of all users registrered
     * @return List<User>
     * @throws Exception
     */
    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserEntity>> getAll() throws Exception
    {
        List<UserEntity> answer=userService.getAll();
        return new ResponseEntity<List<UserEntity>>(answer, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserBasicDTO> getUsers() {
        return userService.getUsers();}

    @GetMapping("accounts/userId")


    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUserById (@PathVariable Long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.ok().build();
}

    }