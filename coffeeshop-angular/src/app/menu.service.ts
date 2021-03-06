import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { MealCombinations} from './models/mealCombinations';
import { Menu } from './models/menu';
import { Meals } from './models/meals';

import { environment } from './../environments/environment';
import { Observable, Subject, BehaviorSubject } from 'rxjs';
import { catchError, map, tap, shareReplay } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class MenuService {

  constructor(private http: HttpClient) {
  
    this.menu$ = this.http.get<Menu[]>(`${this.url}/menu`)
      .pipe(
        shareReplay(1)
      );

    // this.meals$ = this.http.get<Meals[]>(`${this.url}/meals`)
    //   .pipe(
    //     shareReplay(1)
    //   );

    // this.menuCombinations$ = this.http.get<MealCombinations[]>(`${this.url}/meal_combinations`)
		// 	.pipe(
		// 		shareReplay(1)
		// 	);
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

	//Eventually move this url to a settings file or somewhere where only one change needs to be made
  // url = "localhost:8080";
  url = environment.apiUrl;

  menu$ : Observable<Menu[]>;

  meals$ : Observable<Meals[]>;

  menuCombinations$ : Observable<MealCombinations[]>;


  getMenu(): Observable<Menu[]> {
    return this.menu$;
  }

  getMeals(): Observable<Meals[]> {
    return this.meals$;
  }

  getMealCombinations(): Observable<MealCombinations[]> {
    return this.menuCombinations$;
  }
}
 