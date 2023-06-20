package com.study.figure.dto;

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
}
