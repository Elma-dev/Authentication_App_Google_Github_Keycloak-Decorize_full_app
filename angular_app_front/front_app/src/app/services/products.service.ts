import {Injectable, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductsService implements OnInit{

  products!:Object;
  errors!:any

  constructor(private http:HttpClient) {
  }

  ngOnInit(): void {
    this.http
      .get("http://localhost:8083/products")
      .subscribe(
        {
          next:data=> {
            console.log(data)
            this.products=data},
          error:err => {
            console.log(err)
          }
        }
      )

  }
}
