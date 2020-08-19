import { Injectable } from '@angular/core';

import { Confirmation } from './models/confirmation'
import { Item } from './models/item'
import { MealCombinations } from './models/mealCombinations';
import { Menu } from './models/menu';
import { Meals } from './models/meals';
import { MenuService } from './menu.service';
import { combineLatest } from 'rxjs';
import { HttpBackend, HttpClient } from '@angular/common/http';
import { Orders } from './models/orders';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private menuService: MenuService, private http: HttpClient) { }

  //Information about unique items
  menu: Menu[];

  //Information about meals
  meals: Meals[];

  //matches products in combos with their product IDs
  mealCombinations: MealCombinations[];

  //items in the cart that are singles and not combos
  orderItems: Item[] = [];

  //items in the cart that are part of combos
  orderCombos: MealCombinations[] = [];

  all_values = combineLatest(this.menuService.getMenu(), this.menuService.getMeals(), this.menuService.getMealCombinations());

  // Total cost of the order
  total: number = 0;

  //first name
  fname: string;

  //lst name
  lname: string;

  email: string;

  //Eventually move this url to a settings file or somewhere where only one change needs to be made
  url = "http://localhost:8080";

  ngOnInit(): void {

    this.all_values.subscribe(values => {
      this.menu = values[0];
      this.meals = values[1];
      this.mealCombinations = values[2];
    });

    this.getTotal();
  }

  addItemToCart(item: Menu){
    //add a shallow copy of the item to the cart
    var it = this.toItem(item)
    this.orderItems.push(it);
    return this.total+= it.cost;
  }

  getItemCart(): Item[] {
    return this.orderItems;
  }

  removeItemFromCart(item: Item) {
    this.total -=item.cost;
    
    const index = this.orderItems.indexOf(item);
    if (index >= 0) {
      this.orderItems.splice(index, 1);
    }
    
    return this.total
  }

  

  toItem(menuItem: Menu): Item {

    var item: Item = {productId:menuItem.productId, 
                      name:menuItem.name, 
                      cost:menuItem.price, 
                      type:menuItem.type, 
                      size:"N",
                      creams:0,
                      sugars:0};
    
    if (item.type === 'HC' || item.type === 'IC') {
      
      item.size = menuItem.currentSize;
      item.sugars = menuItem.sugars;
      item.creams = menuItem.creams;
      for(var size of menuItem.productOptions){
        if (size[0] === menuItem.currentSize) {
          item.cost =+size[1];
          
        }
      }
    } else {
      
      item.cost = menuItem.price;
    }
  
    return item;
  }

  getTotal() {
    return this.total;
  }

  guestOrder(fname: string, lname: string, email: string){
    var order: Orders = {fname:fname, lname:lname, email: email, isMember:false, items: this.orderItems}

    return this.http.post<Confirmation>(`${this.url}/guestOrder`, order, {responseType:"json"});
  } 
}
