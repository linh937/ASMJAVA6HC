package org.example.java6_asm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "logs")
public class Log {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int log_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "username")
    User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "login_time")
    private Date login_time = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "login_out")
    private Date login_out = new Date();
}
