package com.alkemy.wallet.service;


import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    public void delete(Long id) throws Exception;


    List<UserBasicDTO> getUsers();



}
