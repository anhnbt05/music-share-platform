package com.example.musicshareserver.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    private String from;

    public void sendOtpEmail(
            String to,
            String subject,
            String otp,
            String fullName,
            String emailTitle,
            String emailHeading,
            String otpPurpose,
            String instructionText
    ) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            String html = loadTemplate()
                    .replace("{{email_title}}", emailTitle)
                    .replace("{{email_heading}}", emailHeading)
                    .replace("{{user_name}}", fullName)
                    .replace("{{otp_purpose}}", otpPurpose)
                    .replace("{{instruction_text}}", instructionText)
                    .replace("{{otp}}", otp);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Gửi email OTP thất bại", e);
        }
    }

    private String loadTemplate() throws Exception {
        ClassPathResource resource =
                new ClassPathResource("templates/email-otp.html");

        return Files.readString(
                resource.getFile().toPath(),
                StandardCharsets.UTF_8
        );
    }
}
