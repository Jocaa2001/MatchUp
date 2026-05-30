import { HttpClient } from '@angular/common/http';
import { inject, Injectable, OnInit, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { EventResponse } from '../models/responses/eventResponse';

@Injectable({
  providedIn: 'root',
})
export class EventsService{

  private http = inject(HttpClient);


  getEvents(): Observable<EventResponse[]>{
    return this.http.get<EventResponse[]>('http://localhost:8080/api/events/all')
  }
  
}
