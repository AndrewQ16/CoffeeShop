package me.andrewq.coffeeshop.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.andrewq.coffeeshop.models.Confirmation;
import me.andrewq.coffeeshop.models.Orders;
import me.andrewq.coffeeshop.services.EmailService;


@RestController
public class OrderController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmailService service;

    private String sql = "INSERT INTO `CoffeeShop`.`orders`"
            + "(`order_number`,`first_name`,`last_name`,`email`,`order_list`,`isPayed`,`date`)" + "VALUES"
            + "(UUID_TO_BIN(UUID()), ?, ?, ?, ?, ?, ?);";

    private String latestOrderForEmail = "SELECT BIN_TO_UUID(order_number) order_number from orders where email=? order by `date` desc limit 1";

    


    @CrossOrigin
    @PostMapping(path = "/guestOrder")
    public Confirmation sendConfirmation(@RequestBody Orders order) {
        System.out.println("Guest order processing...");
        ObjectMapper mapper = new ObjectMapper();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // return a null object if nothing could be processed
        if(order.getFname().length() == 0 || order.getLName().length() == 0 || order.getEmail().length() == 0) {
            return null;
        }

        
        System.out.println("Order arrived.");
        try {
            mapper.writeValue(out, order.getItems());


            byte[] data = out.toByteArray();
            String cartOrder = new String(data);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = sdf.format(order.getDate());

            System.out.println(date);
            jdbcTemplate.update(sql, order.getFname(), order.getLName(), order.getEmail(), cartOrder, order.getIsPayed(),date);
           
            //return a confirmation object
            Confirmation retVal = jdbcTemplate.query(latestOrderForEmail, new Object[] {order.getEmail()}, 
            (rs, rowNum)-> new Confirmation(order.getFname(), order.getLName(), order.getEmail(), rs.getString("order_number"), false)).get(0);

            
            //send email out of order
            service.sendConfrmationMessage(order.getEmail(), order, retVal.getOrderNumber());

            //return confirmation object
            System.out.print("Order processed for #:" + retVal.getOrderNumber());
            return retVal;

        } catch (IOException e) {
            
            e.printStackTrace();
            return null;
        }

        
    }
}