package com.study.figure.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.study.figure.dto.Project;

@Mapper
@Repository
public interface ProjectMapper {
    public int saveProject(Project project);
}
