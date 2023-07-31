package com.study.figure.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.study.figure.dto.User;

@Mapper
@Repository
public interface UserMapper {
    
    public void saveUser(User user);
    public User getUserByEmail(String email);
    public int emailCheck(String email);
    public void updateUserByEmail(User user);
}
