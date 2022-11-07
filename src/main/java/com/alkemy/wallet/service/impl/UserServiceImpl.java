package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void delete(Long id) throws Exception {
        if(!userRepository.findById(id).isPresent())
        {
            throw new Exception("User not found in database");
        }
        else
            userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
