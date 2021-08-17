package com.jincheng.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class emailService {
    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void setMailSender(String email, HttpServletRequest request) {
        System.out.println(email);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("验证码邮件");//主题
        //生成验证码
        String code = randomCode();
        HttpSession session = request.getSession();
        session.setAttribute("vercode", code);

        //邮件内容
        mailMessage.setText("您收到的验证码为:" + code);
        mailMessage.setTo(email);//发给谁
        mailMessage.setFrom(from);//发邮件的邮箱
        try {
            // 使用该类的send()方法发送邮件
            mailSender.send(mailMessage);//private JavaMailSender mailSender;
            System.out.println("发送成功！");
        } catch (Exception e) {
            System.out.println("发送失败！");
            System.out.println(e.getMessage());
        }
    }

    public String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
