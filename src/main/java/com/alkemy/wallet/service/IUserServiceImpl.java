package com.alkemy.wallet.service;

import com.alkemy.wallet.model.User;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;
import java.util.List;


@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private Validator validator;
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
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
