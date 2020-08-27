import { Component, OnInit } from '@angular/core';

import { Confirmation } from '../models/confirmation';
import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';
import { Menu } from '../models/menu';
import { Item } from '../models/item';
import { Orders } from '../models/orders';
import { Guest } from '../models/guest';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(private menuService: MenuService, private orderService: OrderService, private http: HttpClient) { }

  guest: Guest;

  submitted = false;

  fname: string = "";

  lname: string = "";

  email: string = "";

  confirmation: Observable<Confirmation>;

  onSubmit() { this.submitted = true };

  get diagnostic() { return JSON.stringify(this.guest); }

  ngOnInit(): void {
    this.guest = {fname:"", lname:"", email:""};
  }


  /**
   * Submit the order as a post request to the backend and return the order details for the customer and ability
   */
  guestOrder(){
    this.confirmation = this.orderService.guestOrder(this.guest.fname, this.guest.lname, this.guest.email);
    this.confirmation.subscribe(x => console.log(x));
  }

}
