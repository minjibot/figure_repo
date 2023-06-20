package com.study.figure.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.figure.dto.Project;
import com.study.figure.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	// userController로 옮겨야?
	@PostMapping("/user/{userId}/createProject")
	public ResponseEntity<Map<String, Object>> create(@RequestBody Project saveData) throws Exception {
		ResponseEntity<Map<String, Object>> rs = null;

		try {
			if (saveData != null) {
				Map<String, Object> project = projectService.createProject(saveData);
				if (project != null) {
					rs = new ResponseEntity<Map<String, Object>>(project, HttpStatus.OK);
				}
			}
			if (rs == null)
				rs = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}

		return rs;
	}
}
