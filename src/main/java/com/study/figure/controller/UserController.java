package com.study.figure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.figure.dto.User;
import com.study.figure.service.UserService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
	public ResponseEntity<User> signUp(@RequestBody Map<String, Object> saveData) throws Exception {
		ResponseEntity<User> rs = null;
		
		try {
			if (saveData != null) {
				User user = userService.signUpUser(saveData);
				if (user != null) {
					rs = new ResponseEntity<User>(user, HttpStatus.OK);
				}
			}
			if (rs == null)
                rs = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		return rs;
	}
}
