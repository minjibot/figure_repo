package com.study.figure.service;

import java.util.Map;

import com.study.figure.dto.Project;

public interface ProjectService {
    public Map<String, Object> createProject(Project proect) throws Exception;
}
