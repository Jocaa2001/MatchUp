import { Component, computed, inject, Input, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { EventResponse } from '../../models/responses/eventResponse';
import { EventsService } from '../../services/events.service';
import { DatePipe } from '@angular/common';
import { ParticipationResponse } from '../../models/responses/participationResponse';

@Component({
  selector: 'app-event-detail',
  imports: [RouterLink, DatePipe],
  templateUrl: './event-detail.html',
  styleUrl: './event-detail.scss',
})
export class EventDetail implements OnInit {
  private route = inject(ActivatedRoute);
  private eventService = inject(EventsService);
  participants = signal<ParticipationResponse[] | null>(null);
  event = signal<EventResponse | null>(null);


  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.eventService.getEventById(id).subscribe({
      next: (event) => {
        this.event.set(event);
        console.log(event);
      },
      error: (err) => {
        console.error('Failed to load event:', err);
      }
    });

     this.eventService.getParticipantsByEventId(id).subscribe({
    next: (participants) => {
      this.participants.set(participants);
      console.log(this.participants())
    },
    error: (err) => {
      console.error('Failed to load participants:', err);
    }
  });


  }

  emptySlots = computed(() => {
  const maxPlayers = this.event()?.maxPlayers ?? 0;
  const joined = this.participants()?.length ?? 0;

  return Array(Math.max(maxPlayers - joined, 0));
});

}
