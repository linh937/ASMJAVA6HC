package org.example.java6_asm.repository;

import org.example.java6_asm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
