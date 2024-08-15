package com.example.ex15_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "myuser")
public class MyUser {
    @Id
    private String id;
    private String name;
}
