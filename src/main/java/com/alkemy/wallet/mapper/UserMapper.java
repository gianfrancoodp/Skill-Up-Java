package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import java.util.List;
<<<<<<< HEAD
@Component
public class UserMapper {
=======


@Component
public class UserMapper {

>>>>>>> 1a79b388bb929dd14ec024a346f5246876cc74e6
    private ModelMapper modelMapper = new ModelMapper();

    public UserBasicDTO userEntity2DTO(User entity){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserBasicDTO result = this.modelMapper.map(entity, UserBasicDTO.class);
        return result;
    }

    public User userDTO2Entity(UserBasicDTO userBasicDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User result = this.modelMapper.map(userBasicDTO, User.class);
        return result;
    }

    public List<UserBasicDTO> userEntity2DTOList(List<User> entities){
        List<UserBasicDTO> dtos = new ArrayList<>();
        for(User entity:entities){
            dtos.add(this.userEntity2DTO(entity));
        }
        return dtos;
    }

<<<<<<< HEAD
    public List<User> userDTO2EntityList(List<UserBasicDTO> dtos) {
        List<User> entities = new ArrayList<>();
        for (UserBasicDTO dto : dtos) {
            entities.add(this.userDTO2Entity(dto));
        }
        return entities;
    }
}
=======
    public List<User> userDTO2EntityList(List<UserBasicDTO> dtos){
        List<User> entities = new ArrayList<>();
        for(UserBasicDTO dto:dtos){
            entities.add(this.userDTO2Entity(dto));
        }
        return entities;
    }}
>>>>>>> 1a79b388bb929dd14ec024a346f5246876cc74e6
