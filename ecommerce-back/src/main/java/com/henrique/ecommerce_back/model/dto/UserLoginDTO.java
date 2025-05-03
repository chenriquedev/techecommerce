package com.henrique.ecommerce_back.model.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
