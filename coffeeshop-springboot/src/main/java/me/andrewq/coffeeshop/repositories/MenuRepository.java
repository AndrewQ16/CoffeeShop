package me.andrewq.coffeeshop.repositories;

import org.springframework.data.repository.CrudRepository;
import me.andrewq.coffeeshop.menu_items.Menu;

// This will be AUTO IMPLEMENTED by Spring into a Bean called menuRepository
// CRUD refers Create, Read, Update, Delete

public interface MenuRepository extends CrudRepository<Menu, Integer> {
    
}