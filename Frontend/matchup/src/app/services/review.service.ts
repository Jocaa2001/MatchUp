import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReviewEventResponse } from '../models/responses/reviewEventResponse';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {

    private http = inject(HttpClient);

  getReviewsByEventId(eventId: number): Observable<ReviewEventResponse> {
    return this.http.get<ReviewEventResponse>(
      `http://localhost:8080/api/reviews/event/${eventId}`
    );
  }

}
