package com.study.figure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.figure.dto.Notice;
import com.study.figure.mybatis.NoticeMapper;
import com.study.figure.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
    
    @Autowired
    NoticeMapper noticeMapper;

    public List<Notice> getUserNotices(Long userId) throws Exception {
        return noticeMapper.getUserNotices(userId);
    }
}

