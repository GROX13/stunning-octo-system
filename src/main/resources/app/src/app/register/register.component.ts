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
  errors: string[];

  constructor(
    private router: Router,
    private httpClientService: HttpClientService
  ) {
  }

  ngOnInit() {
  }

  registerUser() {
    if (this.password !== this.repeatPassword) {
      this.errors = ['entered passwords do not match'];
    } else {
      this.httpClientService.registerUser(new RegistrationData(
        this.username,
        this.password,
        this.regionCode,
        this.scheduledMinutes
      )).subscribe(
        response => this.router.navigate(['login']),
        errorResponse => {
          // @ts-ignore
          console.error(errorResponse);
          if (errorResponse.status === 400) {
            // @ts-ignore
            this.errors = Object.values(errorResponse.error);
          } else
            this.errors = ['something went wrong on server'];
        }
      )
    }
  }
}
