package com.study.figure.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.figure.dto.TemporaryNumber;
import com.study.figure.service.MailService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/mail")
public class MailController {
    
    @Autowired
    private MailService mailService;

    @GetMapping("/{email}/authenticate")
    public ResponseEntity<String> login(@PathVariable String email) throws Exception {
		ResponseEntity<String> rs = null;

		try{
			if (StringUtils.isNotEmpty(email)) {
				String result = mailService.authenticateEmail(email);
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
}
