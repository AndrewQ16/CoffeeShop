import { Component, OnInit } from '@angular/core';

import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private menuService: MenuService, private orderService: OrderService) { }

  

  ngOnInit(): void {
      
  }

  getItemCart() {
    
    return this.orderService.getItemCart();
    
  }

}
