package com.example.springsecuritysystem.entity.system.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ROLE")
public class Role implements Serializable {

    private static final long serialVersionUID = 1262348688386928631L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;

    @Column(name = "ROLE", unique = true, nullable = false)
    private String role;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Users> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "PERMISSION_ID")
    @JsonIgnore
    private Set<Permission> permissions = new HashSet<>();
}
