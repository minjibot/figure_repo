package com.study.figure.controller;

import com.study.figure.dto.Project;
import com.study.figure.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	// userController로 옮겨야?
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createProject(@RequestBody Project saveData) throws Exception {
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

	@GetMapping("/list")
	public ResponseEntity<List<Project>> getProjects(@RequestParam(value = "userId", required = true) Long userId)
			throws Exception {
		ResponseEntity<List<Project>> rs = null;

		try {
			List<Project> results = projectService.getProjects(userId);
			rs = new ResponseEntity<List<Project>>(results, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<List<Project>>(HttpStatus.BAD_REQUEST);
		}

		return rs;
	}

	@PostMapping("/bookmark")
	public ResponseEntity<Map<String, Object>> setBookmark(@RequestBody Map<String, Object> data) throws Exception {
		ResponseEntity<Map<String, Object>> rs = null;

		try {
			if (data != null) {
				int result = projectService.saveBookmark(data);
				if (result > 0)
					rs = new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
			} else {
				rs = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}

		return rs;
	}

	@PostMapping("/users")
	public ResponseEntity<List<Map<String, Object>>> getProjectUsers(@RequestBody Map<String, Object> data)
			throws Exception {
		ResponseEntity<List<Map<String, Object>>> rs = null;

		try {
			if (data != null) {
				List<Map<String, Object>> result = projectService.getProjectUsers(data);
				rs = new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
			} else {
				rs = new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<List<Map<String, Object>>>(HttpStatus.BAD_REQUEST);
		}

		return rs;
	}
}
