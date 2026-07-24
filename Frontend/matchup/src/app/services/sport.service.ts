import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SportResponse } from '../models/responses/sportResponse';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class SportService {

  private http = inject(HttpClient);

  getSports(): Observable<SportResponse[]> {
    return this.http.get<SportResponse[]>('http://localhost:8080/api/sports/all');
  }

}
