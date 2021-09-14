package com.ecommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int age;
    private int salary; 
}
