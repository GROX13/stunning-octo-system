import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClientService, RegistrationData} from "../http-client.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username = '';
  scheduledMinutes = 0;
  regionCode = '';
  password = '';
  repeatPassword = '';
  errors: [];

  constructor(
    private router: Router,
    private httpClientService: HttpClientService
  ) {
  }

  ngOnInit() {
  }

  registerClient() {
    this.httpClientService.registerUser(new RegistrationData(
      this.username,
      this.password,
      this.regionCode,
      this.scheduledMinutes
    )).subscribe(
      response => {
        console.info(response);
      },
      errorResponse => {
        console.error(errorResponse);
        this.errors = Object.values(errorResponse.error);
      }
    )
  }
}
