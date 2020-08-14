package me.andrewq.coffeeshop.repositories;

import org.springframework.data.repository.CrudRepository;

import me.andrewq.coffeeshop.menu_items.MealCombinations;

public interface MealCombinationsRepository extends CrudRepository<MealCombinations, Integer>{
    
}