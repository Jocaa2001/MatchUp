import { inject, Injectable } from '@angular/core';
import { LoginRequest } from '../models/requests/loginRequest';
import { LoginResponse } from '../models/responses/loginResponse';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../models/requests/registerRequest';
import { RegisterResponse } from '../models/responses/registerResponse';

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

  register(data: RegisterRequest){
    return this.http.post<RegisterResponse>('http://localhost:8080/api/auth/register', data);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

}
