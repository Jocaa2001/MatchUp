import { HttpClient } from "@angular/common/http";
import { inject, Injectable, OnInit, signal } from "@angular/core";
import { UserResponse } from "../models/responses/userResponse";

@Injectable({
  providedIn: 'root',
})
export class ProfileDetailsService{
    
    private http = inject(HttpClient);

    user = signal<UserResponse | null>(null);


    getLoggedUser() {
  return this.http.get<UserResponse>('http://localhost:8080/api/users/me');
}
}