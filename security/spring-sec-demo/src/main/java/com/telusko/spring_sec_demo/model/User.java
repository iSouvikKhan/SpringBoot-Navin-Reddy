package com.telusko.spring_sec_demo.model;

import jakarta.persistence.*;
import lombok.Data;

//@Table(name = "users")
@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

}
