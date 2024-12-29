package com.flat.service;

import com.flat.entity.Login;


public interface LoginService {
    Login authenticateUser(String username, String password);

    boolean validateUser(String username, String password);

    Login findByUsername(String username);

    String findRoleByUsername(String username);
}

