package com.study.figure.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.study.figure.dto.TemporaryNumber;
import com.study.figure.mybatis.TemporaryNumberMapper;
import com.study.figure.mybatis.UserMapper;
import com.study.figure.service.MailService;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    TemporaryNumberMapper temporaryNumberMapper;

    @Value("${figure.mail}")
    String figureMail;

    @Value("${figure.mail.password}")
    String figureMailPassword;

    public String sendHtmlMail(String email, String subject, String htmlString) throws Exception {
        if(StringUtils.isAnyEmpty(figureMail, figureMailPassword)) return "fail";
        String result = "success";
		
		HtmlEmail mail = new HtmlEmail();

		mail.setCharset("euc-kr"); // 한글 인코딩
		mail.setHostName("smtp.gmail.com"); //SMTP서버 설정
		mail.setSmtpPort(465); //SMPT서버 포트번호
		
		mail.setAuthentication(figureMail, figureMailPassword);
		mail.setSSLOnConnect(true);
		mail.setStartTLSEnabled(true);
		
		try {
			mail.addTo(email, "USER"); // 수신자 추가
			mail.setFrom(figureMail, "Figure"); // 발신자 추가
			mail.setSubject(subject); // 메일 제목
			mail.setHtmlMsg(htmlString); // 메일 본문
			
			mail.send(); // 메일 보내기
		} catch (Exception e) {
			result = "fail";
		}
        return result;
    }
    
    /**
     * @param email
	 * @param type - 01: 회원가입, 02: 비밀번호 찾기
	 * @return
	 * @throws Exception
	 */
    public String authenticateEmail(String email, String type) throws Exception {
        if(StringUtils.isAnyEmpty(email, type)) return null;
        
        int emailCheck = userMapper.emailCheck(email);
        String result;
        if(StringUtils.equals(type,"01") && emailCheck > 0) {
            result = "isEmailDuplicated";
        } else if(StringUtils.equals(type,"02") && emailCheck == 0) {
            result = "isNotExistEmail";
        } else {
            String temporaryNumber =  getTemporaryNumber(); // front 쪽으로 가져가서 사용자가 입력한 임시번호값과 동일한지 체크 해야함
            result = sendHtmlMail(email, StringUtils.equals(type,"01") ? "Figure 회원가입 인증" : "Figure 비밀번호 찾기", getTemporaryNumberHtmlStr(temporaryNumber));
            if(StringUtils.equals(result, "success")) {
                TemporaryNumber tm = new TemporaryNumber(email, temporaryNumber);
                temporaryNumberMapper.saveTemporaryNumber(tm);
                scheduleTemporaryNumberCleanup(tm);
            }
        }

        return result;
    }

    private String getTemporaryNumberHtmlStr(String temporaryNumber) {
        StringBuffer sb = new StringBuffer();
        sb.append("<div>");
        sb.append("임시 번호 : " + temporaryNumber);
        sb.append("</div>");
        return sb.toString();
    }


    
    private String getTemporaryNumber() {
        int index = 0;
        char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
                                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
                                            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < 10; i++) {
            index = (int) (characterTable.length * Math.random());
            buf.append(characterTable[index]);
        }
        
        return buf.toString();
    }

    private void scheduleTemporaryNumberCleanup(TemporaryNumber tm) {
        long time = 3 * 60 * 1000; // 3분
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    temporaryNumberMapper.deleteTemporaryNumber(tm);
                } catch (Exception e) {}
            } 
        };

        timer.schedule(task, time);
    }

    public String temporaryNumberCheck(TemporaryNumber temporaryNumber) throws Exception {
        if(temporaryNumber == null) return null;

        int temporaryNumberCheck = temporaryNumberMapper.temporaryNumberCheck(temporaryNumber);
       
        return temporaryNumberCheck > 0 ? "success" : "fail";
    }
}
