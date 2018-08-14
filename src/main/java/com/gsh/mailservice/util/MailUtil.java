package com.gsh.mailservice.util;

import com.gsh.mailservice.constant.MailAuthConstant;
import com.gsh.mailservice.vo.AlertVo;
import com.gsh.mailservice.vo.RulesVo;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailUtil {

    private static JavaMailSenderImpl mailSender = createMailSender();

    public static JavaMailSenderImpl createMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(MailAuthConstant.MAILSERVERHOST);
        sender.setPort(MailAuthConstant.MAILSERVERPORT);
        sender.setUsername(MailAuthConstant.FROMADDRESS);
        sender.setPassword(MailAuthConstant.PASSWORD);
        sender.setDefaultEncoding("utf-8");
        Properties p = new Properties();
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtp.timeout","80000");
        p.setProperty("mail.smtp.auth","true");
        p.setProperty("mail.smtp.starttle.enable","true");
        sender.setJavaMailProperties(p);
        return sender;
    }

    public static void sendHtmlMail(AlertVo alertVo)
            throws MessagingException,UnsupportedEncodingException {

        String mailAddress = alertVo.getAddress();
        String mailSubject = alertVo.getSubject();
        String mailContent = alertVo.getContent();

        String[] toAddress = mailAddress.split(";");

        System.out.println("发送成功");

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper =
                new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(MailAuthConstant.FROMADDRESS, MailAuthConstant.FROMNAME);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(mailSubject);
        messageHelper.setText(mailContent, true);
        mailSender.send(mimeMessage);
    }
}
