package com.alkemy.wallet.mapper;

import com.alkemy.wallet.dto.RoleDto;
import com.alkemy.wallet.model.Role;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public RoleDto entityToDto (Role role){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RoleDto result = this.modelMapper.map(role, RoleDto.class);
        return result;
    }

    public Role dtoToEntity(RoleDto roleDto){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Role result = this.modelMapper.map(roleDto, Role.class);
        return result;
    }
}
