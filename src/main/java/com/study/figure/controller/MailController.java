package com.study.figure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.figure.dto.TemporaryNumber;
import com.study.figure.service.MailService;
import com.study.figure.service.UserService;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("/mail")
public class MailController {
    
    @Autowired
    private MailService mailService;

	@Autowired
    private UserService userService;

    @GetMapping("/{email}/authenticate")
    public ResponseEntity<String> login(@PathVariable String email, @RequestParam(value = "type", required = true) String type) throws Exception {
		ResponseEntity<String> rs = null;

		try{
			if (!StringUtils.isAnyEmpty(email, type)) {
				String result = mailService.authenticateEmail(email, type);
				if (StringUtils.isNotEmpty(result)) {
					rs = new ResponseEntity<String>(result, HttpStatus.OK);
				}
			}
			if (rs == null)
                rs = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return rs;
	}

	@PostMapping("/temporaryNumberCheck")
    public ResponseEntity<String> temporaryNumberCheck(@RequestBody TemporaryNumber temporaryNumber) throws Exception {
		ResponseEntity<String> rs = null;

		try{
			if (temporaryNumber != null) {
				String result = mailService.temporaryNumberCheck(temporaryNumber);
				if (StringUtils.isNotEmpty(result)) {
					rs = new ResponseEntity<String>(result, HttpStatus.OK);
				}
			}
			if (rs == null)
                rs = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return rs;
	}

	@PostMapping("/user/update")
	public ResponseEntity<String> userUpdateByEmail(@RequestBody Map<String, Object> saveData) throws Exception {
		ResponseEntity<String> rs = null;
		
		try {
			if (saveData != null) {
				String result = userService.userUpdateByEmail(saveData);
				if (StringUtils.isNotEmpty(result)) {
					rs = new ResponseEntity<String>(result, HttpStatus.OK);
				}
			}
			if (rs == null)
                rs = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			rs = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return rs;
	}
}
