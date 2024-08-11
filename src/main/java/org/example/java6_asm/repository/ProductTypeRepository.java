package org.example.java6_asm.repository;

import org.example.java6_asm.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {

}
