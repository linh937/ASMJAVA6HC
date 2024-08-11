package org.example.java6_asm.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brands_id;
    private String brands_name;
}
