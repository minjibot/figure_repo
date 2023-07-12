package com.study.figure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemporaryNumber {
    private String email;
    private String temporaryNumber;

    public TemporaryNumber() {};

    public TemporaryNumber(String email, String temporaryNumber) {
        super();
        this.email = email;
        this.temporaryNumber = temporaryNumber;
    }
}
