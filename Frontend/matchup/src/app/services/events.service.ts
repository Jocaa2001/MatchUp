import { HttpClient } from '@angular/common/http';
import { inject, Injectable, OnInit, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { EventResponse } from '../models/responses/eventResponse';
import { CreateEventRequest } from '../models/requests/createEventRequest';
import { ParticipationResponse } from '../models/responses/participationResponse';
import { EventCursorResponse } from '../models/responses/eventCursorResponse';

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
  joinEvent(id: Number){
     return this.http.post<ParticipationResponse>(
    `http://localhost:8080/api/participations/events/${id}`, null)
  }

  getEventsForUser() {
  return this.http.get<EventResponse[]>('http://localhost:8080/api/events/for-user');
}

updateEvent(id: number, data: Partial<EventResponse>): Observable<EventResponse> {
  return this.http.patch<EventResponse>(
    `http://localhost:8080/api/events/${id}`,
    data
  );
}

getEventsCursor(cursor?: number, limit = 6, sport?: number): Observable<EventCursorResponse> {

  let url = `http://localhost:8080/api/events/get?limit=${limit}`;

  if (cursor !== undefined && cursor !== null) {
    url += `&cursor=${cursor}`;
  }

    if (sport !== undefined && sport !== null) {
    url += `&sportId=${sport}`;
  }

  return this.http.get<EventCursorResponse>(url);
}

}
