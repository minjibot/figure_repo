package com.study.figure.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.study.figure.dto.TemporaryNumber;

@Mapper
@Repository
public interface TemporaryNumberMapper {
    public void saveTemporaryNumber(TemporaryNumber temporaryNumber);
    public void deleteTemporaryNumber(TemporaryNumber temporaryNumber);
    public int temporaryNumberCheck(TemporaryNumber temporaryNumber);
}
