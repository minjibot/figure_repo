package com.study.figure.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.figure.dto.Project;
import com.study.figure.mybatis.ProjectMapper;
import com.study.figure.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
    
    @Autowired
    ProjectMapper projectMapper;

    public List<Project> getUserProjects(Long userId) throws Exception {
        return projectMapper.getUserProjects(userId);
    }
}

