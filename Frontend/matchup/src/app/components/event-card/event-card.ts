import { Component, Input } from '@angular/core';
import { EventResponse } from '../../models/responses/eventResponse';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-event-card',
  imports: [DatePipe, RouterLink, TranslatePipe],
  standalone: true,
  templateUrl: './event-card.html',
  styleUrl: './event-card.scss',
})
export class EventCard {
  @Input() event!: EventResponse;
}
