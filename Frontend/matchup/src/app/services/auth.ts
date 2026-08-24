import { inject, Injectable, signal } from '@angular/core';
import { LoginRequest } from '../models/requests/loginRequest';
import { LoginResponse } from '../models/responses/loginResponse';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../models/requests/registerRequest';
import { RegisterResponse } from '../models/responses/registerResponse';
import { Router } from '@angular/router';
import { UserResponse } from '../models/responses/userResponse';
import { tap, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private http = inject(HttpClient);
  private router = inject(Router);
  currentUser = signal<UserResponse | null>(null);
  avatarUrl = signal<string | null>(null);
  readonly user = this.currentUser.asReadonly();

  setCurrentUser(user: UserResponse) {
  this.currentUser.set(user);
}

setAvatar(blob: Blob) {
  const url = URL.createObjectURL(blob);
  this.avatarUrl.set(url);
}

  getLoggedUser() {
      return this.http.get<UserResponse>('http://localhost:8080/api/users/me');
  }

  loadCurrentUser() {
  if (!this.isLoggedIn()) {
    return;
  }

  this.getLoggedUser().subscribe({
    next: (user) => {
      this.currentUser.set(user);      
    },
    error: () => {
      this.logout();
    }
  });
}
  
login(request: LoginRequest) {
  return this.http.post<LoginResponse>('http://localhost:8080/api/auth/login', request).pipe(
    tap(res => localStorage.setItem('token', res.token)),
    switchMap(() => this.getLoggedUser()),
    tap(user => this.currentUser.set(user))
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
  
  logout() {
  localStorage.removeItem('token');
  //this.user.set(null);
  this.router.navigate(['/login']);
}

}
