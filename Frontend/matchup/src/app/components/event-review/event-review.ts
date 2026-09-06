import { Component, Input } from '@angular/core';
import { ReviewResponse } from '../../models/responses/reviewResponse';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-event-review',
  imports: [DatePipe],
  templateUrl: './event-review.html',
  styleUrl: './event-review.scss',
})
export class EventReview {
  Math = Math;
  @Input() reviews: ReviewResponse[] = [];
  @Input() averageRating = 0;
  @Input() reviewCount = 0;
  @Input() userJoined = false;

}
