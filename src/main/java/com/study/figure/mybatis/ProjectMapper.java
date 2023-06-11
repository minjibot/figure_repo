package com.study.figure.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.study.figure.dto.Project;

@Mapper
@Repository
public interface ProjectMapper {
    
    public List<Project> getUserProjects(Long userId);
}
