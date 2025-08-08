package com.henrique.ecommerce_back.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControler {

    @GetMapping
    public String getMethodName() {
        return "Olá Mundo";
    }

}
