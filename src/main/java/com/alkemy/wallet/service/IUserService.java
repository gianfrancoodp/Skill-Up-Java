package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;

import java.util.List;

public interface IUserService {

    //List<UserDTO> getAll();


    List<UserBasicDTO> getUsers();

    void delete(Long Id);
}
