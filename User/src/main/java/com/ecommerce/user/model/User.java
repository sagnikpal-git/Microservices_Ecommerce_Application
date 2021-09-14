package com.ecommerce.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
   
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String userName;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private long salary;
    @Column
    private int age;      
}
