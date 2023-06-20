package com.study.figure.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.study.figure.dto.Project;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProjectMapper {
    public List<Project> getProjects(Long userId);
    public List<Project> getUserProjects(Long userId);
    public int getBookmarkCount(Map<String, Object> data);
    public int addBookmark(Map<String, Object> data);
    public int deleteBookmark(Map<String, Object> data);
    public List<Map<String, Object>> getProjectUsers(Map<String, Object> data);
}
