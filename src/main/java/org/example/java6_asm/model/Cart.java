package org.example.java6_asm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cart")
public class Cart {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username")
    User users;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    private int quantity = 1;


}
