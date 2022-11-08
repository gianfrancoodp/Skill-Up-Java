package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleService {

    /**
     * Deletes a Role from the database using its Id number.
     * @param id
     * @throws Exception
     */
    public void delete(Long id) throws Exception;

    /**
     * Return a list containing all roles persisted in Database.
     * @return List<Role>
     */
    public List<Role> getAll();

    /**
     *save or update a Role given an object of its class.
     * @return Role
     * @param role
     * @throws Exception
     */
    public Role update(Role role) throws Exception;

}
