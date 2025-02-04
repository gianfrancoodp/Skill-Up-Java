package com.alkemy.wallet.service.impl;

import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.mapper.UserAssembler;
import com.alkemy.wallet.exception.ParamNotFound;
import com.alkemy.wallet.mapper.UserMapper;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.repository.IUserRepository;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private PagedResourcesAssembler<UserEntity> pagedResourcesAssembler;

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

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserBasicDTO> getUsers() {
        List<UserEntity> entities = userRepository.findAll();
        List <UserBasicDTO> userBasicDTO = userMapper.userEntity2DTOList(entities);
        return userBasicDTO;
    }
    @Override
    public UserDto getUserAll(long id) {
        UserEntity entities = userRepository.getReferenceById(id);
        if (Objects.isNull(entities)) {
            throw new ParamNotFound("user ID not found");
        };
        UserDto userDTO = userMapper.userEntityDTO(entities);
        return userDTO;
    }
    @Override
    public UserDto update(Long id, UserDto userDTO) {
        UserEntity userEntity = userRepository.getReferenceById(id);
        if (Objects.isNull(userEntity)) {
            throw new ParamNotFound("user ID not found");
        }
        UserEntity userUpdate=userRepository.save(userMapper.updateUserDTO2Entity(userDTO,userEntity));
        UserDto userDTOUpdate= userMapper.userEntityDTO(userUpdate);
        return userDTOUpdate;}

    @Override
    public UserEntity findById(long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(()-> new Exception("User not found"));
    }

    @Override
    public PagedModel<UserBasicDTO> findAll(Integer page) throws Exception {
        try {
            PageRequest pageRequest = PageRequest.of(0, 10);
            if (page != null) {
                pageRequest = PageRequest.of(page, 10);
            }
            Page<UserEntity> users = userRepository.findAll(pageRequest);
            PagedModel<UserBasicDTO> usersDto = pagedResourcesAssembler.toModel(users, userAssembler);
            return usersDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}










