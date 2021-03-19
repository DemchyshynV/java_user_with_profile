package com.example.avatars.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;
}
