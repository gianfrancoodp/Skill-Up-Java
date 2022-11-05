package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.model.User;
import com.alkemy.wallet.repository.UserRepository;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<UserBasicDTO> getUsers() {
        List<User> entities = userRepository.findAll();
        List <UserBasicDTO> userBasicDTO = userMapper.userEntity2DTOList(entities);
        return userBasicDTO;
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            System.out.println("Id user not valid");
        }
        userRepository.deleteById(id);
    }


}
