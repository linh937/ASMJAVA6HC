package org.example.java6_asm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "product")
public class Product {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    private String product_name;
    private String product_img;
    private String product_describe;
    private double product_price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "brands_id")
    Branch branch;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "types_id")
    ProductType type;

    private int views;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    List<Cart> cart;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
      List<Inventory> inventories;
}
