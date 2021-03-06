package com.example.config;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 发送电子邮件配置
 *
 * @author Wangyl
 * @date 2022/04/21
 */
@Component
@Slf4j
public class SendEmailConfig {

    /**
     * 使用内置JavaMailSender发送邮件
     */
    private JavaMailSender mailSender;

    /**
     * 邮箱验证码发送者
     */
    @Value("${spring.mail.username}")
    private String sender;

    public synchronized void sendEmail(String emailAddress,String code){
        SimpleMailMessage message = new SimpleMailMessage();
        //设置标题
        message.setSubject("邮箱验证码");
        log.info("邮箱验证码:"+code);
        //设置邮件正文
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");
        //设置收件人
        message.setTo(emailAddress);
        //设置发件人
        message.setFrom(sender);
        if (mailSender == null){
            mailSender = SpringUtil.getBean(JavaMailSender.class);
        }
        //发送邮件
        mailSender.send(message);
    }
}
