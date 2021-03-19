package com.example.avatars.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int age;
    private String avatar;
    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "profile")
    private User user;
}
