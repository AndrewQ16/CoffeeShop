import { Component, OnInit } from '@angular/core';

import { Item } from '../models/item'
import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';
import { Menu } from '../models/menu';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private menuService: MenuService, private orderService: OrderService) { }


  ngOnInit(): void {
      
  }

  getTotal(): number {
    return this.orderService.getTotal();
  }

  getItemCart() {
    return this.orderService.getItemCart(); 
  }

  removeItemFromCart(item: Item){
    this.orderService.removeItemFromCart(item);
  }

}
