package org.example.java6_asm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orders_id;
    private String fullname;
    private String email;
    private String phone;
    private String orders_address;

    @ManyToOne
    @JoinColumn(name = "voucher")
    Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "status")
    Status status;

    @Temporal(TemporalType.DATE)
    @Column(name = "orders_time")
    Date orders_time = new Date();


    @JsonIgnore
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetail;
}
