package com.study.figure.dto;

import java.time.LocalDateTime;

import io.micrometer.common.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {

    private Long projectId;
    private String name;
    private String jDesc;
    private String status;
    private String createUserId;
    private LocalDateTime createDateTime;
    private String updateUserId;
    private LocalDateTime updateDateTime;

    public boolean validation() {
        // userId도 추가해야함
        return StringUtils.isEmpty(this.name);
    }

}
