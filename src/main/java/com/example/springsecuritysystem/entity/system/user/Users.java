package com.example.springsecuritysystem.entity.system.user;

import com.example.springsecuritysystem.entity.Teritory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@NamedQuery(name = "Users.findAll", query = "SELECT s FROM Users s")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDED_BY_USER_ID")
    @JsonIgnore
    private Users addedBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODIFIED_BY_USER_ID")
    @JsonIgnore
    private Users modifiedBy;

    @Column(name = "ADDED_DATE")
    private LocalDateTime addedDate;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @Column(name = "IS_ACCOUNT_NON_EXPIRED")
    private Boolean isAccountNonExpired;

    @Column(name = "IS_ACCOUNT_NON_LOCKED")
    private Boolean isAccountNonLocked;

    @Column(name = "IS_CREDENTIALS_NON_EXPIRED")
    private Boolean isCredentialsNonExpired;

    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    @Column(name = "IS_VERIFIED")
    private Boolean isVerified;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "ROLE_ID")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "PERMISSION_ID")
    @JsonIgnore
    private Set<Permission> permissions = new HashSet<>();


    @ManyToOne
    @JsonIgnore
    private Teritory teritory;


    /*@OneToOne(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private TerritoryDmoMap territoryDmoMap;*/


    public Users(Users users) {
        this.userId = users.getUserId();
        this.userName = users.getUserName();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.isAccountNonExpired = users.getIsAccountNonExpired();
        this.isAccountNonLocked = users.getIsAccountNonLocked();
        this.isCredentialsNonExpired = users.getIsCredentialsNonExpired();
        this.isEnabled = users.getIsEnabled();
        this.isVerified = users.getIsVerified();
        this.roles = users.getRoles();
        this.permissions = users.getPermissions();
        this.addedBy = users.getAddedBy();
        this.modifiedBy = users.getModifiedBy();
    }

    @Override
    public String toString() {
        return "Users{}";
    }
}
