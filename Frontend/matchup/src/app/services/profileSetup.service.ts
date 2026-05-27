import { HttpClient, HttpHeaders } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { UserProfileRequest } from "../models/requests/userProfileRequest";



@Injectable({
  providedIn: 'root',
})
export class ProfileSetupService{
    private http = inject(HttpClient);

    
    

    setupProfile(data:UserProfileRequest){

        const token = localStorage.getItem('token');

        const headers = new HttpHeaders({
          Authorization: `Bearer ${token}`
        });
        return this.http.put<void>('http://localhost:8080/api/users/create-profile', data, {headers});
    }


}