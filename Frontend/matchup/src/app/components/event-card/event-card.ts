import { Component, Input } from '@angular/core';
import { EventResponse } from '../../models/responses/eventResponse';

@Component({
  selector: 'app-event-card',
  imports: [],
  standalone: true,
  templateUrl: './event-card.html',
  styleUrl: './event-card.scss',
})
export class EventCard {
  @Input() event!: EventResponse;
}
