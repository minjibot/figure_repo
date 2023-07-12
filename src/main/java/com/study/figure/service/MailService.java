package com.study.figure.service;

import java.util.Map;

import com.study.figure.dto.TemporaryNumber;

public interface MailService {
    public String authenticateEmail(String email) throws Exception;
    public String temporaryNumberCheck(TemporaryNumber temporaryNumber) throws Exception;
}
