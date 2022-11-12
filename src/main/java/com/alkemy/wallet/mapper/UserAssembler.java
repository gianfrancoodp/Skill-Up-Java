package com.alkemy.wallet.mapper;

import com.alkemy.wallet.controller.UserController;
import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.model.UserEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class UserAssembler  extends RepresentationModelAssemblerSupport<UserEntity, UserBasicDTO> {

    public UserAssembler() {
        super(UserController.class, UserBasicDTO.class);
    }

    @Override
    public UserBasicDTO toModel(UserEntity userEntity){
        UserBasicDTO userBasicDTO = instantiateModel(userEntity);
        return userBasicDTO;
    }

    @Override
    public CollectionModel<UserBasicDTO> toCollectionModel(Iterable<? extends UserEntity> entities)
    {
        CollectionModel<UserBasicDTO> users = super.toCollectionModel(entities);

        return users;
    }
}
