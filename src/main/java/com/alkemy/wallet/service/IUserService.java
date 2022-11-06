package com.alkemy.wallet.service;

import com.alkemy.wallet.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<User> getAll();

    public Long findByEmail(String email);

}
