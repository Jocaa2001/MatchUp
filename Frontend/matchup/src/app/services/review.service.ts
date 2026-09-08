import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReviewEventResponse } from '../models/responses/reviewEventResponse';
import { CreateReviewRequest } from '../models/requests/CreateReviewRequest';
import { ReviewResponse } from '../models/responses/reviewResponse';

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

  createReview(request: CreateReviewRequest): Observable<ReviewResponse> {
    return this.http.post<ReviewResponse>(
      'http://localhost:8080/api/reviews/create',
      request
    );
  }

}
