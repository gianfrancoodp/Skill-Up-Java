package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleService extends JpaRepository<Role,Long> {

    /**
     * Deletes a Role from the database using its Id number.
     * @param id
     * @throws Exception
     */
    public void delete(Long id) throws Exception;

    /**
     * Return a list containing all roles persisted in Database.
     * @return Role List
     */
    public List<Role> getAll();


}
