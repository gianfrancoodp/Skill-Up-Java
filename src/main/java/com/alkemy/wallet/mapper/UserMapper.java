package com.alkemy.wallet.mapper;


import com.alkemy.wallet.dto.UserDto;
import com.alkemy.wallet.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public UserDto userEntity2Dto (UserEntity user){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto result = this.modelMapper.map(user, UserDto.class);
        return result;
    }

    public UserEntity UserDTO2ToEntity(UserDto userDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity result = this.modelMapper.map(userDto, UserEntity.class);
        return result;
    }
}
