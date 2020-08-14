package me.andrewq.coffeeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import me.andrewq.coffeeshop.menu_items.Meals;
import me.andrewq.coffeeshop.repositories.MealsRepository;

@RestController
public class MealsController {

    @Autowired
    private MealsRepository mealsRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/meals")
    public Iterable<Meals> getFullMeals(){
        return  mealsRepository.findAll();
    }

}