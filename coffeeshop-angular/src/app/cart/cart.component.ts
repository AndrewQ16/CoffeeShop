import { Component, OnInit } from '@angular/core';

import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';
import { Menu } from '../menu_items/menu';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private menuService: MenuService, private orderService: OrderService) { }


  ngOnInit(): void {
      
  }

  getTotal() {
    return this.orderService.getTotal();
  }

  getItemCart() {
    return this.orderService.getItemCart(); 
  }

  removeItemFromCart(item: Menu){
    this.orderService.removeItemFromCart(item);
  }

}
