import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClientService, User, UserData} from "../http-client.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../authentication.service";
import {interval, Subscription} from "rxjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  private subscription: Subscription;

  user: User;
  scheduledMinutes = 0;
  regionCode = '';
  errors: [string];

  constructor(
    private router: Router,
    private httpClientService: HttpClientService,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    if (!this.authenticationService.isUserLoggedIn()) {
      console.warn('User not authenticated');
      this.router.navigate(['login']);
    }
    this.getUserDetails();
    const source = interval(10000);
    this.subscription = source.subscribe(next => this.getUserDetails());
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  getUserDetails() {
    this.httpClientService.getUser().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => {
        console.error(error);
        this.router.navigate(['login']);
      }
    );
  }

  updateUser() {
    this.httpClientService.updateUser(new UserData(
      this.regionCode,
      this.scheduledMinutes
    )).subscribe(
      response => {
        this.errors = [];
        console.info(response);
        this.getUserDetails();
      },
      errorResponse => {
        console.error(errorResponse);
        this.errors = Object.values(errorResponse.error);
      }
    );
  }

  handleSuccessfulResponse(response) {
    this.user = response;
    this.regionCode = this.user.regionCode;
    this.scheduledMinutes = this.user.scheduledMinutes;
  }
}
