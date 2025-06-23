package com.telusko.spring_sec_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//@Table(name = "users")
@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    private int id;
    private String username;
    private String password;

}
