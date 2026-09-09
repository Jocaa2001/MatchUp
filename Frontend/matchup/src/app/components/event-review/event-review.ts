import { Component, inject, Input, OnInit, signal } from '@angular/core';
import { ReviewResponse } from '../../models/responses/reviewResponse';
import { DatePipe } from '@angular/common';
import { ReviewService } from '../../services/review.service';
import { EventStatus } from '../../models/responses/eventResponse';
import { Auth } from '../../services/auth';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-event-review',
  imports: [DatePipe,TranslatePipe],
  templateUrl: './event-review.html',
  styleUrl: './event-review.scss',
})
export class EventReview implements OnInit {
  authService = inject(Auth);
  private reviewService = inject(ReviewService);
  comment = signal('');
  selectedRating = signal(0);
  Math = Math;

  @Input() eventId!: number;
  @Input() reviews: ReviewResponse[] = [];
  @Input() averageRating = 0;
  @Input() reviewCount = 0;
  @Input() userJoined = false;
  @Input() eventStatus: EventStatus = 'OPEN';

  ngOnInit() {
  //console.log('Event ID:', this.eventId);
}

selectRating(rating: number) {
  this.selectedRating.set(rating);
}

onCommentChange(value: string) {
  this.comment.set(value);
}

submitReview() {
  if (this.selectedRating() === 0) {
    return;
  }

  this.reviewService.createReview({
    eventId: this.eventId,
    rating: this.selectedRating(),
    comment: this.comment()
  }).subscribe({
    next: (review) => {
      console.log('Review created:', review);
      window.location.reload();

    },
    error: (err) => {
      console.error('Failed to create review:', err);
    }
  });
}

deleteReview(reviewId: number) {
  this.reviewService.deleteReview(reviewId).subscribe({
    next: () => {
      console.log('Review deleted');
      window.location.reload();
    },
    error: (err) => {
      console.error('Failed to delete review:', err);
    }
  });
}

}
