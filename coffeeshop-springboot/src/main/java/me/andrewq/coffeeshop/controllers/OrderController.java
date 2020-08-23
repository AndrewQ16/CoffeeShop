package me.andrewq.coffeeshop.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.andrewq.coffeeshop.models.Confirmation;
import me.andrewq.coffeeshop.models.Menu;
import me.andrewq.coffeeshop.models.Orders;

@RestController
public class OrderController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql = "INSERT INTO `CoffeeShop`.`orders`"
            + "(`order_number`,`first_name`,`last_name`,`email`,`order_list`,`isPayed`,`Date`)" + "VALUES"
            + "(?, ?,?, ?, ?, ?, ?);";

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/test", consumes = "application/json", produces = "application/json")
    public String testPost() {

        return "test";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/guestOrder")
    public Confirmation sendConfirmation(@RequestBody Orders order) {

        ObjectMapper mapper = new ObjectMapper();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            mapper.writeValue(out, order.getItems());

            byte[] data = out.toByteArray();

            System.out.println(new String(data));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Create confirmation entry on DB
        // jdbcTemplate.update(sql, order.getFname(), order.getLName(), order.getEmail());

        //Get confirmation number saved on DB

        //Send back Confirmation
        
        return null;
    }
}