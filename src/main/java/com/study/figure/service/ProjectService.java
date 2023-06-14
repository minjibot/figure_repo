package com.study.figure.service;

import java.util.List;

import com.study.figure.dto.Project;

public interface ProjectService {
    public List<Project> getUserProjects(Long userId) throws Exception;
}
