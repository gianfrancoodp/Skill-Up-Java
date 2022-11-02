package com.alkemy.wallet.service;

import com.alkemy.wallet.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRoleService extends JpaRepository<Role,Long> {

    public void delete(Long id) throws Exception;

    public List<Role> getAll();


}
