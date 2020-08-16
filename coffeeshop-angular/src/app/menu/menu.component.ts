import { Component, OnInit } from '@angular/core';

import { MealCombinations } from '../menu_items/mealCombinations';
import { Menu } from '../menu_items/menu';
import { Meals } from '../menu_items/meals';
import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';

import { combineLatest } from 'rxjs';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
/**
 * Display a tabbed screen for shop products
 */
export class MenuComponent implements OnInit {

  constructor(private menuService: MenuService, private orderService: OrderService) { }

  menu: Menu[];
  
  /**
   * NOTE: for meals, the object fields should be changed to include a list [] of the products it can contain since
   * they are all of type Menu... This is how we'll get it cleanly done
   */
  meals: Meals[];

  sandwiches: Menu[] = [];

  pastries: Menu[] = [];

  drinks: Menu[] = [];

  mealCombinations: MealCombinations[];

  all_values = combineLatest(this.menuService.getMenu(), this.menuService.getMeals(), this.menuService.getMealCombinations());

  ngOnInit(): void {

    this.all_values.subscribe(values => {
      this.menu = values[0];
      this.meals = values[1];
      this.mealCombinations = values[2];

      this.organizeMenu();
    });
  }

  organizeMenu(): void {
    this.menu.forEach((value, index)=> {
      if(value.type === "HC" || value.type === "IC"){
        value.currentSize="S"; //default drink size will be set to S for small
        value.creams = 0;
        value.sugars = 0;
        this.drinks.push(value);
      } else if(value.type === "DO" || value.type === "MF") {
        this.pastries.push(value);
      } else {
        this.sandwiches.push(value);
      }
    });
  }

  /**
   * An onClick event to add a single item from the menu template to the cart
   * @param item
   */
  addMenuItemToCart(item: Menu) {
    
    this.orderService.addItemToCart(item);
    console.log("Added: " + item.name);
  }

  /**
   * 
   * When combo meals are offered alongside individual item purchases 
   */
  addComboToCart(){

  }

  
}
