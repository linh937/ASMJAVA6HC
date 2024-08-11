package org.example.java6_asm.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    String roles_id;
    String roles_name;

//	@JsonIgnore
//	@OneToMany(mappedBy = "role")
//	List<Users> user;

    @JsonIgnore
    @OneToMany(mappedBy = "roleid")
    List<UserRole> UserRole;
}
