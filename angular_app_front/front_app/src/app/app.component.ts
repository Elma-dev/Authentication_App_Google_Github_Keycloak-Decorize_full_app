import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'front_app';
  public isLoggedIn=false;
  public userProfile:KeycloakProfile|null=null;
  constructor(private keycloak:KeycloakService) {
  }
  async ngOnInit() {
   this.isLoggedIn = this.keycloak.isLoggedIn();

    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }

  logout() {
      this.keycloak.logout();
  }

  login() {
    this.keycloak.login()
  }


}
