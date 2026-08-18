import { Component, inject, OnInit, signal } from '@angular/core';
import { EventsService } from '../../services/events.service';
import { EventResponse } from '../../models/responses/eventResponse';
import { ParticipationService } from '../../services/participation.service';
import { EventCard } from '../../components/event-card/event-card';
import { RouterLink } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-user-events',
  imports: [EventCard, RouterLink, TranslatePipe],
  templateUrl: './user-events.html',
  styleUrl: './user-events.scss',
})
export class UserEvents implements OnInit {
  activeTab: 'created' | 'joined' = 'created';
  eventService = inject(EventsService)
  participationService = inject(ParticipationService);
createdEvents = signal<EventResponse[]>([]);
joinedEvents = signal<EventResponse[]>([]);

  ngOnInit(): void {
    this.eventService.getEventsForUser().subscribe({
      next: (events) => {
        this.createdEvents.set(events);
        console.log(this.createdEvents)
      },
      error: (error) => {
        console.error('Failed to fetch user events', error);
      }
    });

    this.participationService.getParticipationsByUser().subscribe({
      next: (events) => {
        this.joinedEvents.set(events);
        console.log(this.joinedEvents)
      },
      error: (error) => {
        console.error('Failed to fetch user events', error);
      }
    })
  }
}
