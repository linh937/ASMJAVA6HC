package org.example.java6_asm.service;

import org.example.java6_asm.model.Branch;
import org.example.java6_asm.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Optional<Branch> getBranchById(int id) {
        return branchRepository.findById(id);
    }


    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }


    public void deleteBranch(int id) {
        branchRepository.deleteById(id);
    }
}
