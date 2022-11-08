package com.alkemy.wallet.service;


import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {

    public void delete(Long id) throws Exception;


    List<UserBasicDTO> getUsers();


    User findById(long userId) throws Exception;

}
