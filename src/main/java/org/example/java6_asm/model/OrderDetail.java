package org.example.java6_asm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name = "detail_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long detailorder_id;
    Integer quantity;
    Long price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    Order order;
}
