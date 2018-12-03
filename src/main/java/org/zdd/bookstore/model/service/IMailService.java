package org.zdd.bookstore.model.service;

public interface IMailService {

    void sendSimpleMail(String to, String subject, String content) throws Exception;

    void sendHtmlMail(String to, String subject, String content) throws Exception;
}
