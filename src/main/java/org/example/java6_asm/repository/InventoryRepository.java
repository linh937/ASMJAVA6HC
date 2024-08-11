package org.example.java6_asm.repository;

import org.example.java6_asm.model.Inventory;
import org.example.java6_asm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Optional<Inventory> findByProduct_ProductId(int productId);

}

