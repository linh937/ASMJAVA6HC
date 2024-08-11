package org.example.java6_asm.repository;

import org.example.java6_asm.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Integer> {

}
