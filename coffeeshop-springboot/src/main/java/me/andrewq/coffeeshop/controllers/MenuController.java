package me.andrewq.coffeeshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.andrewq.coffeeshop.models.Menu;

import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
public class MenuController {
    

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String menuQuery = "SELECT * FROM menu";

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/menu")
    public List<Menu> getMenu(){
        
        return jdbcTemplate.query(menuQuery, (rs, rowNum) -> new Menu(rs.getInt("product_id"), rs.getString("name"), rs.getDouble("price"),
            rs.getString("product_options"),rs.getString("type")));

    }

}