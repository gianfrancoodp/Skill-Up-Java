package com.alkemy.wallet.mapper;

import com.alkemy.wallet.controller.UserController;
import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.model.UserEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler  extends RepresentationModelAssemblerSupport<UserEntity, UserBasicDTO> {


    public UserAssembler() {
        super(UserController.class, UserBasicDTO.class);
    }

    @Override
    public UserBasicDTO toModel(UserEntity userEntity){
        UserBasicDTO userBasicDTO = instantiateModel(userEntity);
        userBasicDTO.setId(userEntity.getId());
        userBasicDTO.setEmail(userEntity.getEmail());
        userBasicDTO.setFirstName(userEntity.getFirstName());
        userBasicDTO.setLastName(userEntity.getLastName());
        return userBasicDTO;
    }

    @Override
    public CollectionModel<UserBasicDTO> toCollectionModel(Iterable<? extends UserEntity> entities)
    {
        CollectionModel<UserBasicDTO> users = super.toCollectionModel(entities);

        return users;
    }
}
