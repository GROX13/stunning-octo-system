import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

export class RegistrationData {
  constructor(
    public username: string,
    public password: string,
    public regionCode: string,
    public scheduledMinutes: number
  ) {
  }
}

export class UserData {
  constructor(
    public regionCode: string,
    public scheduledMinutes: number
  ) {
  }
}

export class User {
  constructor(
    public username: string,
    public regionCode: string,
    public scheduledMinutes: number,
    public videos: Video[]
  ) {
  }
}

export class Video {
  constructor(
    public videoLink: string,
    public comment: Comment) {
  }
}

export class Comment {
  constructor(public commentLink: string) {
  }
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getUser() {
    return this.httpClient.get<User>('/user', {headers: this.getHeader()});
  }

  authenticate(auth: string) {
    return this.httpClient.get<User>('/user', {
      headers: new HttpHeaders({
        authorization: auth
      })
    });
  }

  updateUser(userdata: UserData) {
    return this.httpClient.post("/user/update", userdata, {headers: this.getHeader()});
  }

  registerUser(registrationData: RegistrationData) {
    return this.httpClient.post("/user/register", registrationData);
  }

  private getHeader() {
    return new HttpHeaders({
      authorization: sessionStorage.getItem("btoa")
    });
  }
}
