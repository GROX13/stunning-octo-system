import {Injectable} from '@angular/core';
import {HttpClientService} from "./http-client.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClientService: HttpClientService) {
  }

  authenticate(username, password) {
    const auth: string = 'Basic ' + btoa(username + ':' + password);
    sessionStorage.setItem("btoa", auth);
    return this.httpClientService.authenticate(auth);
  }

  isUserLoggedIn() {
    let auth = sessionStorage.getItem('btoa');
    return !(auth === null);
  }

  logOut() {
    sessionStorage.removeItem('btoa');
  }
}
