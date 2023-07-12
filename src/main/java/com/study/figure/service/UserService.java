package com.study.figure.service;

import java.util.Map;

import com.study.figure.dto.User;

public interface UserService {
    public User signUpUser(Map<String, Object> user) throws Exception;

    public Map<String, Object> loginUser(Map<String, Object> loginData) throws Exception;
}
