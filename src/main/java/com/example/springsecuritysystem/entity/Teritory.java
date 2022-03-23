package com.example.springsecuritysystem.entity;

import com.example.springsecuritysystem.entity.system.user.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the TERITORY database table.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Teritory.findAll", query = "SELECT t FROM Teritory t")
public class Teritory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ADD_DATE")
    @JsonIgnore
    private Timestamp addDate;

    @Column(name = "MOD_DATE")
    @JsonIgnore
    private Timestamp modDate;

    @Column(name = "PARENT_ID")
    private BigDecimal parentId;

    @Column(name = "TERITORY_DETAILS")
    @JsonIgnore
    private String teritoryDetails;

    @Column(name = "TERITORY_NAME")
    private String teritoryName;

    @Column(name = "TERRITORY_CODE")
    private String territoryCode;



    @OneToMany(mappedBy = "teritory", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Users> users;

}