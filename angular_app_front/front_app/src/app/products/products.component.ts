import { Component } from '@angular/core';
import {ProductsService} from "../services/products.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent {
  products:any
  constructor(private http:HttpClient) {
    this.http
      .get("http://localhost:8083/products")
      .subscribe(
        {
          next:value=> {
            console.log(value)
            this.products=value},
          error:err => {
            console.log(err)
          }
        }
      )

  }



}
