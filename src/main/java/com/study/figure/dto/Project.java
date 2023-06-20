package com.study.figure.dto;

import io.micrometer.common.util.StringUtils;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter

public class Project {
    private Long projectId;
    private String name;
    private String description;
    private String status;
    private Long createUserId;
    private LocalDateTime createDateTime;
    private Long updateUserId;
    private LocalDateTime updateDateTime;

    public boolean validation() {
        // userId도 추가해야함
        return StringUtils.isEmpty(this.name);
    }
}
