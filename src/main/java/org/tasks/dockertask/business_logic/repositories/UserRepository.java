package org.tasks.dockertask.business_logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tasks.dockertask.business_logic.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
