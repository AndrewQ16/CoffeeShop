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
import { environment } from 'src/environments/environment';


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
  // url = "http://localhost:8080";
  url = environment.apiUrl;

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

    if(this.addQuantity(item)){
      return this.total
    }

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

      if(this.orderItems[index].quantity > 1){
        this.orderItems[index].quantity--;
        this.orderItems[index].totalCost-= this.orderItems[index].cost;
      } else {
        this.orderItems.splice(index, 1);
      }

      
    }
    
    return this.total
  }

  
  /**
   * Convert a Menu object to an Item object
   * @param menuItem 
   */
  toItem(menuItem: Menu): Item {

    var item: Item = {productId:menuItem.productId, 
                      name:menuItem.name, 
                      cost:menuItem.price, 
                      type:menuItem.type, 
                      chosenOptions:[],
                      quantity:1,
                      totalCost: menuItem.price};
    
    if (item.type === 'HC' || item.type === 'IC') {
 
      item.chosenOptions.push(["size", menuItem.currentSize.toString()]);

      item.chosenOptions.push(["creams", menuItem.sugars.toString()]);

      item.chosenOptions.push(["sugars", menuItem.creams.toString()]);

      item.cost = +this.getCostOfSize(menuItem);
    } else {
      
      item.cost = menuItem.price;
    }
  
    return item;
  }

  addQuantity(menuItem: Menu): boolean {

    if(menuItem.type === 'HC' || menuItem.type === 'IC') {
      for(var item of this.orderItems) {
        if(menuItem.productId === item.productId && menuItem.type === item.type && menuItem.currentSize === item.chosenOptions[0][1] && 
          menuItem.creams === +item.chosenOptions[1][1] && menuItem.sugars === +item.chosenOptions[2][1]){
            item.quantity++;
            item.totalCost+= item.cost;
            this.total += item.cost;
            return true;
        }
      }
    } else {

      for(var item of this.orderItems) {
        if(menuItem.productId === item.productId && menuItem.type === item.type){
          item.quantity++;
          item.totalCost+= item.cost;
          this.total+= item.cost;
          return true;
        }
      }
    }


    return false;
  }

  getTotal() {
    return this.total;
  }

  guestOrder(fname: string, lname: string, email: string){
    var date = new Date(Date.now());
    var order: Orders = {fname:fname, lname:lname, email: email, isPayed:false, items: this.orderItems, date: date}

    return this.http.post<Confirmation>(`${this.url}/guestOrder`, order, {responseType:"json"});
  } 

  

  getCostOfSize(menuItem: Menu){
    for(var size of menuItem.productOptions){
      if (size[0] === menuItem.currentSize) {
        return size[1]
      }
    }
  }
}
