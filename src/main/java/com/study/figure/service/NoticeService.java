package com.study.figure.service;

import java.util.List;

import com.study.figure.dto.Notice;

public interface NoticeService {
    public List<Notice> getUserNotices(Long userId) throws Exception;
}
