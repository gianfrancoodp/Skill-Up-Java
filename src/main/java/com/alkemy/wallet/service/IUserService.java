package com.alkemy.wallet.service;

<<<<<<< HEAD
import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.model.UserEntity;
import org.springframework.stereotype.Service;
=======

import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
>>>>>>> 1a79b388bb929dd14ec024a346f5246876cc74e6

import java.util.List;

@Service
public interface IUserService {
<<<<<<< HEAD
    /**
     * Delete an User using his/her Id number.
     * @param 
     * @throws
     */
    public void delete(Long id) throws Exception;
    /**
     * Returns a List of all registered Users
     * @return List of Users
     */
    public List<UserEntity> getAll();
=======

    public void delete(Long id) throws Exception;


    List<UserBasicDTO> getUsers();



>>>>>>> 1a79b388bb929dd14ec024a346f5246876cc74e6
}
