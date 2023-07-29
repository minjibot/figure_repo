package com.study.figure.dto;

import java.time.LocalDateTime;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long userId;
    private String email;
    private String password;
    private String desc;
    private String name;
    private String status;
    private String salt;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public boolean validation(boolean isCreate) {
        return StringUtils.isEmpty(this.email) || StringUtils.isEmpty(this.password) || 
        StringUtils.isEmpty(this.salt) || (isCreate && StringUtils.isEmpty(this.name));
    }    

}

