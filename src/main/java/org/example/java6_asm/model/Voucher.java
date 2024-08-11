package org.example.java6_asm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vouchers")
public class Voucher {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voucher_id;
    private String voucher_name;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    List<Order> order;
}
