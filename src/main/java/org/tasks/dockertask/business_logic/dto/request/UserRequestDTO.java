package org.tasks.dockertask.business_logic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
