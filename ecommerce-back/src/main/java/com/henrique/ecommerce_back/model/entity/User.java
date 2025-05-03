package com.henrique.ecommerce_back.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.henrique.ecommerce_back.model.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    private Gender gender;
    private LocalDate birthday;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
}
