package org.tasks.dockertask.controller_services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tasks.dockertask.business_logic.dto.request.UserRequestDTO;
import org.tasks.dockertask.business_logic.dto.response.UserResponseDTO;
import org.tasks.dockertask.business_logic.dto.response.UsersResponseDTO;
import org.tasks.dockertask.business_logic.entites.User;
import org.tasks.dockertask.business_logic.services.UserService;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class UserControllerService {

    private UserService userService;


    public UsersResponseDTO getUsers(){

        List<User> users =  this.userService.getUsers();

        return new UsersResponseDTO(users.stream()
            .map(UserResponseDTO::of)
            .toList());
    }

    public UserResponseDTO getUserById(Long id){

        User user = this.userService.getUser(id);

        return UserResponseDTO.of(user);
    }

    public void addUser(UserRequestDTO userDTO){

        this.userService.addUser(User.of(userDTO));
    }
}
