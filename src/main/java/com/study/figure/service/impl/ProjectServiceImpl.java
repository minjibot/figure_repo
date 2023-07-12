package com.study.figure.service.impl;

import com.study.figure.mybatis.ProjectMapper;
import com.study.figure.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.figure.dto.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public Map<String, Object> createProject(Project saveData) throws Exception {
        Project project = setProjectData(saveData);
        if (project == null || project.validation())
            return null;

        String result = "fail";
        String msg = "";

        int res = projectMapper.saveProject(project);

        if (res == 1)
            result = "success";
        else
            msg = "오류가 발생했습니다.";

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", result);
        resultMap.put("msg", msg);

        return resultMap;
    }

    private Project setProjectData(Project saveData) {
        if (saveData == null)
            return null;
        Project project = new Project();
        if (saveData.getName() != null)
            project.setName(saveData.getName());
        if (saveData.getDescription() != null)
            project.setDescription(saveData.getDescription());
        return project;
    }

    public List<Project> getProjects(Long userId) throws Exception {
        return projectMapper.getProjects(userId);
    }

    public List<Project> getUserProjects(Long userId) throws Exception {
        return projectMapper.getUserProjects(userId);
    }

    public int saveBookmark(Map<String, Object> data) throws Exception {
        if (data == null)
            return 0;
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
