import { HttpClient } from '@angular/common/http';
import { inject, Injectable, OnInit, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { EventResponse } from '../models/responses/eventResponse';
import { CreateEventRequest } from '../models/requests/createEventRequest';
import { ParticipationResponse } from '../models/responses/participationResponse';

@Injectable({
  providedIn: 'root',
})
export class EventsService{

  private http = inject(HttpClient);


  getEvents(): Observable<EventResponse[]>{
    return this.http.get<EventResponse[]>('http://localhost:8080/api/events/all')
  }

  createEvent(data: CreateEventRequest): Observable<EventResponse>{
    return this.http.post<EventResponse>('http://localhost:8080/api/events/create-event', data)
  }

  getEventById(id: Number){
    return this.http.get<EventResponse>(`http://localhost:8080/api/events/${id}`)
  }

  getParticipantsByEventId(id: number) {
  return this.http.get<ParticipationResponse[]>(
    `http://localhost:8080/api/events/${id}/participants`
  );
}
  
}
