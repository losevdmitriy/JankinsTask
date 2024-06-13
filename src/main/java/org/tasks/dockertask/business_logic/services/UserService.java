package org.tasks.dockertask.business_logic.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tasks.dockertask.business_logic.entites.User;
import org.tasks.dockertask.business_logic.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {

  UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }


}
