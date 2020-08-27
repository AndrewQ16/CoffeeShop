package me.andrewq.coffeeshop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender sender;

    private String from = "fakecafeorders@gmail.com";

    private String subject = "Order confirmation";

    public void sendConfrmationMessage(String to, String text) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Order confirmation of #:" + text);
        try {

            sender.send(message);
            
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("Didn't send mail");
        }
    }

}