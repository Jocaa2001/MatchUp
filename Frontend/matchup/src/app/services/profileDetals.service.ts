import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
providedIn: 'root',
})
export class ProfileDetailsService {

private http = inject(HttpClient);

getAvatarByAvatarUrl(url: string) {
    return this.http.get(`http://localhost:8080/api/user-profiles/avatar/${url}`,{responseType: 'blob'}
  );
}

}