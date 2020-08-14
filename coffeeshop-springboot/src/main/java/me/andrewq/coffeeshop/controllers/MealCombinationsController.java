package me.andrewq.coffeeshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import me.andrewq.coffeeshop.menu_items.MealCombinations;
import me.andrewq.coffeeshop.repositories.MealCombinationsRepository;

@RestController
public class MealCombinationsController {
    
    @Autowired
    private MealCombinationsRepository mealCombinationsRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path="/meal_combinations")
    public Iterable<MealCombinations> getFullMealCombos(){
        return  mealCombinationsRepository.findAll();
    }
}