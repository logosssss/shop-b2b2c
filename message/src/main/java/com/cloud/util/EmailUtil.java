package com.cloud.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮件发送工具类
 */
@Component
public class EmailUtil {
    //注入Java自带的 邮件发送器
    @Autowired
    private JavaMailSender javaMailSender;
    //要发邮件的账号
    private String from = "zhu969175052@163.com";

    public void sendSimpleMail(String to,String subject,String content){
        //创建一个简单的邮件消息对象并设置参数
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发送人
        message.setFrom(from);
        //设置主题
        message.setSubject(subject);
        //设置接收人
        message.setTo(to);
        //设置消息内容
        message.setText(content);

        try{
            javaMailSender.send(message);
            System.out.println("邮件发送成功");
        }catch (Exception e){
            System.out.println("邮件发送失败");
            e.printStackTrace();
        }
    }
}
