package com.study.figure.service.impl;

import com.study.figure.mybatis.ProjectMapper;
import com.study.figure.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.figure.dto.Project;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    
    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> getProjects(Long userId) throws Exception {
        return projectMapper.getProjects(userId);
    }

    public List<Project> getUserProjects(Long userId) throws Exception {
        return projectMapper.getUserProjects(userId);
    }

    public int saveBookmark(Map<String, Object> data) throws Exception {
        if (data == null) return 0;
        int result = 0;

        int cnt = projectMapper.getBookmarkCount(data);

        if (cnt > 0) 
            result = projectMapper.deleteBookmark(data);
        else 
            result = projectMapper.addBookmark(data);
        
        return result;
    }

    public List<Map<String, Object>> getProjectUsers(Map<String, Object> data) throws Exception {
        return projectMapper.getProjectUsers(data);
    }
}                 
