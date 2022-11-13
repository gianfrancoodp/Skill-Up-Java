package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.basicDTO.UserBasicDTO;
import com.alkemy.wallet.mapper.UserAssembler;
import com.alkemy.wallet.model.UserEntity;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserAssembler userAssembler;
    @Autowired
    private PagedResourcesAssembler<UserEntity> pagedResourcesAssembler;

    /**
     * Returns a list of all users registrered
     *
     * @return List<User>
     * @throws Exception
     */
    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<UserEntity>> getAll() throws Exception {
        List<UserEntity> answer = userService.getAll();
        return new ResponseEntity<List<UserEntity>>(answer, HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserBasicDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("accounts/userId")


    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paged")
    public ResponseEntity<PagedModel<UserBasicDTO>> getAllPaged(@RequestParam(required = false, name = "page") Integer pageQuery) {
        try {
            PageRequest pageRequest = PageRequest.of(0, 10);
            if (pageQuery != null) {
                pageRequest = PageRequest.of(pageQuery, 10);
            }
            Page<UserEntity> users = userService.findAll(pageRequest);
            PagedModel<UserBasicDTO> usersDto = pagedResourcesAssembler.toModel(users, userAssembler);
            if (pageRequest.hasPrevious())
                usersDto.add(linkTo(methodOn(AccountController.class).getAll(pageRequest.getPageNumber() - 1)).withSelfRel());
            if (pageRequest.getPageNumber() < users.getTotalPages()-1) {
                usersDto.add(linkTo(methodOn(AccountController.class).getAll(pageRequest.getPageNumber() + 1)).withSelfRel());
            }
            return new ResponseEntity<>(usersDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
