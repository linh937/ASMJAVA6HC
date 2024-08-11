package org.example.java6_asm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @ManyToOne
    @JoinColumn(name = "roles_id", nullable = false)
    private Role roleid;

}
