import { Component, effect, inject, OnInit, signal } from '@angular/core';
import { EventCard } from "../../components/event-card/event-card";
import { EventFilter } from "../../components/event-filter/event-filter";
import { EventsService } from '../../services/events.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { Router, RouterLink } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';
import { timeout } from 'rxjs';

@Component({
  selector: 'app-events',
  imports: [EventCard, EventFilter, RouterLink, TranslatePipe],
  standalone: true,
  templateUrl: './events.html',
  styleUrl: './events.scss',
})
export class Events {
  eventsService = inject(EventsService);
  events = toSignal(this.eventsService.getEvents(), {
  initialValue: []
});

  constructor() {
    effect(() => {
      console.log('EVENTS:', this.events());
    });
  }
}
