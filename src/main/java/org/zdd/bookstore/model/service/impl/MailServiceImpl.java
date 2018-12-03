package org.zdd.bookstore.model.service.impl;

import org.zdd.bookstore.exception.BSException;
import org.zdd.bookstore.model.service.IMailService;
import org.zdd.bookstore.web.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * 发邮件服务
 */
@Service
public class MailServiceImpl implements IMailService {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;


    @Override
    @Async//异步发邮件
    public void sendHtmlMail(String to, String subject, String content) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);

        try {
            mailSender.send(message);
            logger.info("邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送邮件时发生异常！", e);
            throw new BSException("发送邮件时发生异常！");
        }
    }

    @Override
    @Async//异步发邮件
    public void sendSimpleMail(String to, String subject, String content) throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
            throw new RuntimeException("发送简单邮件时发生异常！");
        }
    }
}
