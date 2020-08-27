package me.andrewq.coffeeshop.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.andrewq.coffeeshop.models.Confirmation;
import me.andrewq.coffeeshop.models.Orders;

@RestController
public class OrderController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql = "INSERT INTO `CoffeeShop`.`orders`"
            + "(`order_number`,`first_name`,`last_name`,`email`,`order_list`,`isPayed`,`Date`)" + "VALUES"
            + "(UUID_TO_BIN(UUID()), ?, ?, ?, ?, ?, ?);";

    private String getOrderNumber = "SELECT t.order_number, t.email" 
            + "from orders t" 
            + "inner join ("
            + "select order_number, max(date) as MaxDate"
            + " from orders"
            + "group by ? )"
            + "tm on t.email = tm.email and t.date = tm.MaxDate";

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
            String cartOrder = new String(data);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = sdf.format(order.getDate());

            System.out.println(date);
            jdbcTemplate.update(sql, order.getFname(), order.getLName(), order.getEmail(), cartOrder, order.getIsPayed(),date);


            // return jdbcTemplate.query(getOrderNumber, new Object[] {order.getEmail()}, 
            // (rs, rowNum)-> new Confirmation(order.getFname(), order.getLName(), order.getEmail(), rs.getString("order_number"), false)).get(0);
            
            //Get the lastest order by this email to get the UUID to send with the email
        } catch (IOException e) {
            
            e.printStackTrace();
        }

        //Get confirmation number saved on DB

        //Send back Confirmation
        
        return null;
    }
}