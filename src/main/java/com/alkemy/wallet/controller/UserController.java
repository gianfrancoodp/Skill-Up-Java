package com.alkemy.wallet.controller;

import com.alkemy.wallet.model.User;
import com.alkemy.wallet.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * Returns a list of all users registrered
     * @return List<User>
     * @throws Exception
     */
    @GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<User>> getAll() throws Exception
    {
        List<User> answer=userService.getAll();
        return new ResponseEntity<List<User>>(answer, HttpStatus.OK);
    }

    /**
     * This method deletes an User from the database (Soft delete)
     *  curl --location --request DELETE 'http://localhost:8081/users/123'
     *  @param id id number of the user to be deleted.
     *  @return ok if it´s succesfully deleted, error if it´s not deleted
     *  @throws Exception
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception{

        userService.delete(id);

        return ResponseEntity.ok().build();
    }
}
