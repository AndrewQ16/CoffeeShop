import { Component, OnInit } from '@angular/core';

import { Confirmation } from '../models/confirmation';
import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';
import { Menu } from '../models/menu';
import { Item } from '../models/item';
import { Orders } from '../models/orders'

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

  confirmation: Observable<Confirmation>;

  ngOnInit(): void {
    
  }


  /**
   * Submit the order as a post request to the backend and return the order details for the customer and ability
   */
  guestOrder(){
    this.confirmation = this.orderService.guestOrder(this.fname, this.lname, this.email);
    this.confirmation.subscribe(x => console.log(x));
  }

}
