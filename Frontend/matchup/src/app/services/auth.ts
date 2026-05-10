import { inject, Injectable } from '@angular/core';
import { LoginRequest } from '../models/requests/loginRequest';
import { LoginResponse } from '../models/responses/loginResponse';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private http = inject(HttpClient);
  
  login(data: LoginRequest) {
  return this.http.post<LoginResponse>(
    'http://localhost:8080/api/auth/login',
    data
  );
}

}
