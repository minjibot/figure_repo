package com.study.figure.service;

import com.study.figure.dto.Project;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    public List<Project> getProjects(Long userId) throws Exception;
    public List<Project> getUserProjects(Long userId) throws Exception;
    public int saveBookmark(Map<String, Object> data) throws Exception;
    public List<Map<String, Object>> getProjectUsers(Map<String, Object> data) throws Exception;
}
