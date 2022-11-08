package com.alkemy.wallet.service;

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
}
