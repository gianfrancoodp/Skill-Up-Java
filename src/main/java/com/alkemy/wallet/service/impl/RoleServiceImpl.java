package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.Role;
import com.alkemy.wallet.repository.IRoleRepository;
import com.alkemy.wallet.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {


    @Autowired
    private IRoleRepository repository;

    @Override
    public void delete(Long id) throws Exception {
            repository.deleteById(id);
    }

    @Override
    public List<Role> getAll() {
       return repository.findAll();
    }


    @Override
    public Role update(Role role) throws Exception {
        return repository.save(role);
    }
}
