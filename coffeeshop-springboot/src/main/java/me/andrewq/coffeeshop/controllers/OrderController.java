package me.andrewq.coffeeshop.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.andrewq.coffeeshop.models.Confirmation;
import me.andrewq.coffeeshop.models.Menu;
import me.andrewq.coffeeshop.models.Orders;


@RestController
public class OrderController {
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/test", consumes = "application/json", produces = "application/json")
    public String testPost(){

        return "test";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/guestOrder")
    public Confirmation sendConfirmation(@RequestBody Orders order){
        
        //Create confirmation table on DB

        //Get confirmation number saved on DB

        //Send back Confirmation
        
        return null;
    }
}