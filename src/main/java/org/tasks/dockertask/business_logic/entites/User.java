package org.tasks.dockertask.business_logic.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.tasks.dockertask.business_logic.dto.request.UserRequestDTO;

@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@SuperBuilder(toBuilder = true)
@FieldNameConstants
@Entity
@Table(name = "Users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private final Long id;

    @Column(name = "firstName", nullable = false)
    private final String firstName;

    @Column(name = "lastName", nullable = false)
    private final String lastName;

    @Column(name = "email", nullable = false)
    private final String email;

    @Column(name = "password", nullable = false)
    private final String password;


    public static User of(String firstName, String lastName, String email, String password) {
        return User.builder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .password(password)
            .build();
    }

    public static User of(UserRequestDTO userRequestDTO) {
        return User.builder()
            .firstName(userRequestDTO.getFirstName())
            .lastName(userRequestDTO.getLastName())
            .email(userRequestDTO.getEmail())
            .password(userRequestDTO.getPassword())
            .build();
    }
}
