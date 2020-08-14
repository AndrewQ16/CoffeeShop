import { Injectable } from '@angular/core';

import { MealCombinations } from './menu_items/mealCombinations';
import { Menu } from './menu_items/menu';
import { Meals } from './menu_items/meals';
import { MenuService } from './menu.service';
import { combineLatest } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private menuService: MenuService) { }

  //Information about unique items
  menu: Menu[];

  //Information about meals
  meals: Meals[];

  //matches products in combos with their product IDs
  mealCombinations: MealCombinations[];

  //items in the cart that are singles and not combos
  orderItems: Menu[] = [];

  //items in the cart that are part of combos
  orderCombos: MealCombinations[] = [];

  all_values = combineLatest(this.menuService.getMenu(), this.menuService.getMeals(), this.menuService.getMealCombinations());

  ngOnInit(): void {

    this.all_values.subscribe(values => {
      this.menu = values[0];
      this.meals = values[1];
      this.mealCombinations = values[2];
    });
  }

  addItemToCart(item: Menu){
    //add a shallow copy of the item to the cart
    this.orderItems.push(this.deepCopy(item));
    console.log(this.orderItems);
  }

  getItemCart() {
    return this.orderItems;
  }

  /**
   * A deep copy function I found on stackoverflow
   * @param obj 
   */
  deepCopy(obj) {
    var copy;

    // Handle the 3 simple types, and null or undefined
    if (null == obj || "object" != typeof obj) return obj;

    // Handle Date
    if (obj instanceof Date) {
        copy = new Date();
        copy.setTime(obj.getTime());
        return copy;
    }

    // Handle Array
    if (obj instanceof Array) {
        copy = [];
        for (var i = 0, len = obj.length; i < len; i++) {
            copy[i] = this.deepCopy(obj[i]);
        }
        return copy;
    }

    // Handle Object
    if (obj instanceof Object) {
        copy = {};
        for (var attr in obj) {
            if (obj.hasOwnProperty(attr)) copy[attr] = this.deepCopy(obj[attr]);
        }
        return copy;
    }

    throw new Error("Unable to copy obj! Its type isn't supported.");
  }
}
