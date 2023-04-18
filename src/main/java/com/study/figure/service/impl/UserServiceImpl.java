package com.study.figure.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.figure.dto.User;
import com.study.figure.mybatis.UserMapper;
import com.study.figure.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserMapper userMapper;

    public User signUpUser(Map<String, Object> saveData) throws Exception {
        User user = setUserData(saveData);
        if(user == null || user.validation()) return null;
        userMapper.saveUser(user);

        return user;
    }

    private User setUserData(Map<String, Object> saveData) {
        if(saveData == null) return null;
        User user = new User();
        if(saveData.get("userId") != null)
            user.setUserId(Long.valueOf(saveData.get("userId").toString()));
        if(saveData.get("email") != null)
            user.setEmail(saveData.get("email").toString());
        if(saveData.get("name") != null)
            user.setName(saveData.get("name").toString());
        if(saveData.get("desc") != null)
            user.setDesc(saveData.get("desc").toString());
        if(saveData.get("password") != null) {
            String salt = getSalt();
            String encryptPw = getEncrypt(saveData.get("password").toString(), salt);
            user.setSalt(salt);
            user.setPassword(encryptPw);
        }
        return user;
    }

    // 무작위 문자열 Salt
    private String getSalt() {
        //1. Random, salt 생성
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[20];

        //2. 난수 생성
        sr.nextBytes(salt);

        //3. byte To String (10진수 문자열로 변경)
        StringBuffer sb = new StringBuffer();
        for(byte b : salt) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
    
    // SHA-256 알고리즘 적용
    private String getEncrypt(String pwd, String salt) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update((pwd + salt).getBytes());
            byte[] pwdSalt = md.digest();

            for(byte b : pwdSalt) {
                sb.append(String.format("%02x", b));
            }


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}

