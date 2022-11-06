package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.RoleDto;
import com.alkemy.wallet.mapper.RoleMapper;
import com.alkemy.wallet.model.Role;
import com.alkemy.wallet.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * Insert a new Role to the Database.
     * curl --location --request POST 'http://localhost:8081/role'
     * @param roleDto
     * @return Role inserted or error.
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody RoleDto roleDto) throws Exception {

        Role roleToSave = roleService.update(roleMapper.dtoToEntity(roleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleDto);
    }

    /**
     * Removes a Role from the database, given its Id number.
     * @param id
     * @return ok or error
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception{

        roleService.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * This method returns a list of Roles persisted in the database.
     * @return List<Role>
     */
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Role>> getAll() {
        List<Role> answer = roleService.getAll();
        return new ResponseEntity<List<Role>>(answer, HttpStatus.OK);
    }




}
