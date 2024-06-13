package org.tasks.dockertask.business_logic.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@RequiredArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class UsersResponseDTO {

    private final List<UserResponseDTO> users;

}
