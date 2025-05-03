package com.henrique.ecommerce_back.service;

import com.henrique.ecommerce_back.model.dto.UserLoginDTO;
import com.henrique.ecommerce_back.model.dto.UserRegisterDTO;
import com.henrique.ecommerce_back.model.entity.User;

public interface UserService {
    User userLogin(UserLoginDTO userLoginDTO);

    void userRegister(UserRegisterDTO userRegister);
}
