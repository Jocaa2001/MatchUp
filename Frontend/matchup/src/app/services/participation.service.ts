import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ParticipationService {

  private http = inject(HttpClient);

  getParticipationsByEventId(id: number){
    
  }

}
