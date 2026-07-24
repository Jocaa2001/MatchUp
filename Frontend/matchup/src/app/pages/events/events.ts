import { Component, inject, OnInit, signal } from '@angular/core';
import { EventCard } from "../../components/event-card/event-card";
import { EventFilter } from "../../components/event-filter/event-filter";
import { EventsService } from '../../services/events.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-events',
  imports: [EventCard, EventFilter, RouterLink],
  standalone: true,
  templateUrl: './events.html',
  styleUrl: './events.scss',
})
export class Events implements OnInit {
  eventsService = inject(EventsService);
  events = toSignal(this.eventsService.getEvents(), {
  initialValue: []
});

  ngOnInit(): void {

  }

}
