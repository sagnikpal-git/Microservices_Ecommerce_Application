package com.ecommerce.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponse<T> {

    private int status;
    private String message;
    private Object result; 
}
