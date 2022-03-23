package com.example.springsecuritysystem.entity.system.user;

import com.example.springsecuritysystem.enumeration.KeyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "api_keys")
@Getter
@Setter
@NoArgsConstructor
public class ApiKeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "key_type")
    @Enumerated(EnumType.STRING)
    private KeyType keyType;
    private String keyStr;
    @Column(name = "is_enable")
    private boolean isEnable;
}
