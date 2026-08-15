import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventResponse } from '../models/responses/eventResponse';

@Injectable({
  providedIn: 'root',
})
export class ParticipationService {

  private http = inject(HttpClient);

  getCreatedByEventId(){
    
  }

  getParticipationsByUser(): Observable<EventResponse[]> {
    return this.http.get<EventResponse[]>(
      'http://localhost:8080/api/participations/for-user'
    );
  }

  leaveEvent(id: number): Observable<void> {
  return this.http.delete<void>(
    `http://localhost:8080/api/participations/${id}/leave`
  );
}

}
