package org.tasks.dockertask.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tasks.dockertask.business_logic.dto.request.UserRequestDTO;
import org.tasks.dockertask.business_logic.dto.response.UserResponseDTO;
import org.tasks.dockertask.business_logic.dto.response.UsersResponseDTO;
import org.tasks.dockertask.controller_services.UserControllerService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserControllerService userControllerService;

    @GetMapping("/users")
    public ResponseEntity<UsersResponseDTO> getUsers(){
        UsersResponseDTO response = this.userControllerService.getUsers();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long userId){
        UserResponseDTO response = this.userControllerService.getUserById(userId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> addUser(@RequestBody UserRequestDTO user){
        this.userControllerService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
