import { Component, OnInit } from '@angular/core';

import { MenuService } from '../menu.service';
import { OrderService } from '../order.service';


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(private menuService: MenuService, private orderService: OrderService) { }

  ngOnInit(): void {
  }

}
