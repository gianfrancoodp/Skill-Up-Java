package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;

import java.util.List;

public interface IUserService {

    public void delete(Long id) throws Exception;


    List<UserBasicDTO> getUsers();




}
