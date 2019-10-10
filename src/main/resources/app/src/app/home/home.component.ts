import {Component, OnInit} from '@angular/core';
import {HttpClientService, User} from "../http-client.service";
import {Router} from "@angular/router";
import {AuthenticationService} from "../authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;

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
    this.httpClientService.getUser().subscribe(
      response => this.handleSuccessfulResponse(response),
      error => {
        console.error(error);
        return this.router.navigate(['login']);
      }
    );
  }

  handleSuccessfulResponse(response) {
    this.user = response;
  }
}
