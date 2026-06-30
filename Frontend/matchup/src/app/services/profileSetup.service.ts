import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { UserProfileRequest } from "../models/requests/userProfileRequest";



@Injectable({
  providedIn: 'root',
})
export class ProfileSetupService{
    private http = inject(HttpClient)


    
    
    setupProfile(data:UserProfileRequest){
        return this.http.put<void>('http://localhost:8080/api/users/create-profile', data);
    }


}