package com.alkemy.wallet.service;



import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;

import com.alkemy.wallet.model.UserEntity;


import java.util.List;

public interface IUserService {
    /**
     * Delete an User using his/her Id number.
     * @param id
     * @throws Exception
     */
    public void delete(Long id) throws Exception;
    /**
     * Returns a List of all registered Users
     * @return List of Users
     */
    public List<UserEntity> getAll();

    public UserEntity findByEmail(String email);

    List<UserBasicDTO> getUsers();

    UserEntity findById(long userId) throws Exception;

    UserDto getUserAll(long id);

    UserDto update(Long id, UserDto userBasicDTO);
}
