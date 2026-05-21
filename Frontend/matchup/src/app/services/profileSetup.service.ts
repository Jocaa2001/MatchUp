import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { UserProfileRequest } from "../models/requests/userProfileRequest";

const token = localStorage.getItem('token');

const headers = new HttpHeaders({
  Authorization: `Bearer ${token}`
});

@Injectable({
  providedIn: 'root',
})
export class ProfileSetupService{
    private http = inject(HttpClient);
    

    setupProfile(data:UserProfileRequest){
        return this.http.put<void>('http://localhost:8080/api/users/create-profile', data, {headers});
    }


}