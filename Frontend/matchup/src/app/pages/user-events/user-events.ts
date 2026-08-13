import { Component, inject, OnInit } from '@angular/core';
import { EventsService } from '../../services/events.service';
import { EventResponse } from '../../models/responses/eventResponse';
import { ParticipationService } from '../../services/participation.service';
import { EventCard } from '../../components/event-card/event-card';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-events',
  imports: [EventCard, RouterLink],
  templateUrl: './user-events.html',
  styleUrl: './user-events.scss',
})
export class UserEvents implements OnInit {
  activeTab: 'created' | 'joined' = 'created';
  eventService = inject(EventsService)
  participationService = inject(ParticipationService);
  createdEvents: EventResponse[] = [];
  joinedEvents: EventResponse[] = [];


  ngOnInit(): void {
    this.eventService.getEventsForUser().subscribe({
      next: (events) => {
        this.createdEvents = events;
        console.log(this.createdEvents)
      },
      error: (error) => {
        console.error('Failed to fetch user events', error);
      }
    });

    this.participationService.getParticipationsByUser().subscribe({
      next: (events) => {
        this.joinedEvents = events;
        console.log(this.joinedEvents)
      },
      error: (error) => {
        console.error('Failed to fetch user events', error);
      }
    })
  }
}
