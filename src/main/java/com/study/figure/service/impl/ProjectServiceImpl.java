package com.study.figure.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.figure.dto.Project;
import com.study.figure.mybatis.ProjectMapper;
import com.study.figure.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

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
        if (saveData.getJDesc() != null)
            project.setJDesc(saveData.getJDesc());
        return project;
    }
}
