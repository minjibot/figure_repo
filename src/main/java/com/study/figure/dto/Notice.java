package com.study.figure.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notice {
    private Long noticeId;
    private String type;
    private Project project;
    // private Category category;
    // private Work work;
    
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
}

