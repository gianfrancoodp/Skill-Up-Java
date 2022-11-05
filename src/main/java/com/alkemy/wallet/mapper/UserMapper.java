package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.UserDTO;
import com.alkemy.wallet.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserMapper {




       public User userDTO2Entity(UserDTO userDTO){
            User userEntity = new User();
            userEntity.setFirstName(userDTO.getFirstName();
            userEntity.setLastName(userDTO.getLastName());
            userEntity.setEmail(userDTO.getEmail());

            return userEntity;
        }

        public UserDTO userEntity2DTO(User userEntity){
            UserDTO userDTO = new UserDTO();

            userDTO.setFirstName(userEntity.getFirstName());
            userDTO.setLastName(userEntity.getLastName());
            userDTO.setEmail(userEntity.getEmail());
            return userDTO;
        }

        public List<UserDTO> userEntity2DTOList(List<User>entities){
            List<UserDTO> dtos = new ArrayList<>();
            for(User entity: entities){
                dtos.add(this.userEntity2DTOList(entity));
            }
            return dtos;
        }

    public Set<User> userDTOList2EntityList(Set<UserDTO> userDTOList) {
        Set<User> entityList = new HashSet<>();
        for (UserDTO userDTO : userDTOList) {
            entityList.add(userDTO2Entity(userDTO));
        }
        return entityList;
    }


        }

}