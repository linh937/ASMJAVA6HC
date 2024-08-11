package org.example.java6_asm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users", uniqueConstraints =
        {
                @UniqueConstraint(columnNames = { "username"})
        })
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "username")
    private String username;
    private String fullname;
    private String passwords;
    private String email;
    private String phone;
    private boolean active = true;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_login")
    private Date createdate = new Date();

    private int failed_login_attempts = 0;
    private boolean blocked = false;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Log> Logs;

    @JsonIgnore
    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
    List<UserRole> UserRole;
}
