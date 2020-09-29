package me.andrewq.coffeeshop.services;


import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import me.andrewq.coffeeshop.models.Item;
import me.andrewq.coffeeshop.models.Orders;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender sender;

    private String from = "fakecafeorders@gmail.com";

    private String subject = "Order confirmation";

    public void sendConfrmationMessage(String to, Orders orders, String orderNumber) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        

        String receipt = "\nYour order:\n\n";

        NumberFormat format = NumberFormat.getCurrencyInstance();
        for(Item item: orders.getItems()){
            receipt+= "Name: " + item.getName() + "\n";
            receipt+= "Quantity: " + item.getQuantity() + "\n";

            if(item.getType().compareTo("HC") == 0 || item.getType().compareTo("IC") == 0) {
                receipt+="Size: " + item.getChosenOptions()[0][1] + "\n";
                receipt+="Creams: " + item.getChosenOptions()[1][1] + "\n";
                receipt+="Sugars: " + item.getChosenOptions()[2][1] + "\n";
            }
            receipt+= "Cost: " + format.format(item.getTotalCost()) + "\n\n";
        }

        receipt+= "Total: " + format.format(orders.getTotal());

        message.setText("Order confirmation of #:" + orderNumber + receipt);
        try {

            sender.send(message);
            
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("Didn't send mail");
        }
    }

}