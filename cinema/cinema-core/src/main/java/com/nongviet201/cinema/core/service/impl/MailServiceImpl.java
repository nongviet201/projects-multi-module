package com.nongviet201.cinema.core.service.impl;

import com.nongviet201.cinema.core.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;
@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendMailConfirmRegistration(Map<String, String> data) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(data.get("email"));
            helper.setSubject("Xác nhận đăng ký tài khoản");

            // Create the Thymeleaf context
            Context context = new Context();
            context.setVariable("fullName", data.get("fullName"));
            context.setVariable("token", data.get("token"));
            String htmlContent = templateEngine.process("mail-templates/confirmation-account", context);
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error when sending email: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void sendMailResetPassword(Map<String, String> data) {
        log.info("sendMailResetPassword");
        log.info("Sending email request : {}", data);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(data.get("email"));
            helper.setSubject("Xác nhận đặt lại mật khẩu");

            Context context = new Context();
            context.setVariable("fullName", data.get("fullName"));
            context.setVariable("token", data.get("token"));

            // Use the template engine to process the template
            String htmlContent = templateEngine.process("mail-templates/reset-password", context);
            helper.setText(htmlContent, true); // Enable HTML content

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error when sending email: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
