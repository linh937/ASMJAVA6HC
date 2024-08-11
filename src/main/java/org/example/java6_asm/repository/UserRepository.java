package org.example.java6_asm.repository;

import org.example.java6_asm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

}
