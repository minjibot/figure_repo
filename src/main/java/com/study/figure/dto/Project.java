package com.study.figure.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
    private Long projectId;
    private String name;
    private String desc;
    private String status;
    private String createUserId;
    private LocalDateTime createDateTime;
    private String updateUserId;
    private LocalDateTime updateDateTime;
}

