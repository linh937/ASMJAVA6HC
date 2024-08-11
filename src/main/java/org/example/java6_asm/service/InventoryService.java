package org.example.java6_asm.service;

import org.example.java6_asm.model.Inventory;
import org.example.java6_asm.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> getInventoryById(int id) {
        return inventoryRepository.findById(id);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(int id) {
        inventoryRepository.deleteById(id);
    }

    public Optional<Inventory> getInventoryByProductId(int productId) {
        return inventoryRepository.findByProduct_ProductId(productId);
    }
}
