import { Component, Input } from '@angular/core';
import { EventResponse } from '../../models/responses/eventResponse';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-event-card',
  imports: [DatePipe, RouterLink],
  standalone: true,
  templateUrl: './event-card.html',
  styleUrl: './event-card.scss',
})
export class EventCard {
  @Input() event!: EventResponse;
}
