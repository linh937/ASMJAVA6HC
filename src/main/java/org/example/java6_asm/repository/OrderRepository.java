package org.example.java6_asm.repository;

import org.example.java6_asm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
