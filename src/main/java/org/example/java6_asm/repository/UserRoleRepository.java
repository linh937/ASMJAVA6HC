package org.example.java6_asm.repository;


import org.example.java6_asm.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByUsername_Username(String username);
}