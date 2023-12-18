import {Injectable} from '@angular/core';
import {KeycloakEventType, KeycloakService} from "keycloak-angular";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  constructor(public service:KeycloakService) {
    this.init();
  }
  init(){

    this.service.keycloakEvents$.subscribe({
      next:(e)=>{
        if(e.type==KeycloakEventType.OnTokenExpired){
          this.service.updateToken(20);
        }
      }
      }
    );
  }
}
