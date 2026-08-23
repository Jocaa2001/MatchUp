import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { UserProfileRequest } from "../models/requests/userProfileRequest";
import { UserResponse } from "../models/responses/userResponse";



@Injectable({
  providedIn: 'root',
})
export class ProfileSetupService{
    private http = inject(HttpClient)


    //rename this and in backend to be more generic at one point
    setupProfile(data:UserProfileRequest){
        return this.http.put<UserResponse>('http://localhost:8080/api/users/create-profile', data);
    }

    uploadAvatar(file: File) {
    const formData = new FormData();

    formData.append('file', file);

  return this.http.post<string>('http://localhost:8080/api/user-profiles/upload', formData
  );
}

}