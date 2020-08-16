import { Component, OnInit } from '@angular/core';

import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';
import { Menu } from '../menu_items/menu';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(private menuService: MenuService, private orderService: OrderService, private http: HttpClient) { }

  fname: string = "";

  lname: string = "";

  email: string = "";

  ngOnInit(): void {
    
  }

  /**
   * Submit the order as a post request to the backend and return the order details for the customer and ability
   */
  submitOrder() {
    this.http.post
  }

}
