package org.example.java6_asm.controller;


import org.example.java6_asm.model.Inventory;
import org.example.java6_asm.repository.InventoryRepository;
import org.example.java6_asm.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:5173")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/product/{productId}")
    public Optional<Inventory> getInventoriesByProductId(@PathVariable int productId) {
        return inventoryRepository.findByProduct_ProductId(productId);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable int id) {
        Optional<Inventory> inventory = inventoryService.getInventoryById(id);
        if (inventory.isPresent()) {
            return ResponseEntity.ok(inventory.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory newInventory = inventoryService.saveInventory(inventory);
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable("id") int id,
            @RequestBody Inventory inventory) {
        Optional<Inventory> existingInventoryOpt = inventoryService.getInventoryById(id);
        if (!existingInventoryOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Inventory existingInventory = existingInventoryOpt.get();
        existingInventory.setQuantity(inventory.getQuantity());
        existingInventory.setProduct(inventory.getProduct());

        Inventory updatedInventory = inventoryService.saveInventory(existingInventory);
        return ResponseEntity.ok(updatedInventory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable("id") int id) {
        if (!inventoryService.getInventoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/product/{productId}")
//    public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable int productId) {
//        Optional<Inventory> inventory = inventoryService.getInventoryByProductId(productId);
//        if (inventory.isPresent()) {
//            return ResponseEntity.ok(inventory.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
}
