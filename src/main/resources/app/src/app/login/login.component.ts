import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from "../authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  invalidLogin = false;

  constructor(
    private router: Router,
    private loginService: AuthenticationService
  ) {
  }

  ngOnInit() {
  }

  checkLogin() {
    this.loginService.authenticate(this.username, this.password).subscribe(
      response => {
        this.router.navigate(['']);
        this.invalidLogin = false;
      },
      error => {
        this.invalidLogin = true;
        // @ts-ignore
        sessionStorage.removeItem('btoa');
        // @ts-ignore
        console.error(error);
      }
    )
  }

}
