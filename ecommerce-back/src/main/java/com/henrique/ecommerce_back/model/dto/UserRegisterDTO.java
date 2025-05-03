package com.henrique.ecommerce_back.model.dto;

import java.time.LocalDate;

import com.henrique.ecommerce_back.model.enums.Gender;

import lombok.Data;

@Data
public class UserRegisterDTO {
        private String email;
        private String password;
        private String name;
        private String cpf;
        private LocalDate birthday;
        private Gender gender;
}
