package com.study.figure.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.study.figure.dto.Notice;

@Mapper
@Repository
public interface NoticeMapper {
    
    public List<Notice> getUserNotices(Long userId);
}
