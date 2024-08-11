package org.example.java6_asm.controller;

import org.example.java6_asm.model.Branch;
import org.example.java6_asm.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "http://localhost:5173")
public class BranchController {

    @Autowired
    private BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<Branch>> getAllBranches() {
        List<Branch> branches = branchService.getAllBranches();
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable("id") int id) {
        Optional<Branch> branch = branchService.getBranchById(id);
        return branch.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Branch> addBranch(@RequestBody Branch branch) {
        Branch savedBranch = branchService.saveBranch(branch);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable("id") int id, @RequestBody Branch branch) {
        if (!branchService.getBranchById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        branch.setBrands_id(id); // Ensure the ID is set for updating
        Branch updatedBranch = branchService.saveBranch(branch);
        return new ResponseEntity<>(updatedBranch, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable("id") int id) {
        if (!branchService.getBranchById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
