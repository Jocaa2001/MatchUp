import { Component, computed, inject, Input, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { EventResponse } from '../../models/responses/eventResponse';
import { EventsService } from '../../services/events.service';
import { DatePipe } from '@angular/common';
import { ParticipationResponse } from '../../models/responses/participationResponse';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-event-detail',
  imports: [RouterLink, DatePipe],
  templateUrl: './event-detail.html',
  styleUrl: './event-detail.scss',
})
export class EventDetail implements OnInit {
  private route = inject(ActivatedRoute);
  private eventService = inject(EventsService);
  private authService = inject(Auth);
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

  joinEvent(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
     this.eventService.joinEvent(id).subscribe({
    next: (response) => {
      console.log(response)
      console.log('Successfully joined event', response);

      window.location.reload();
    },
    error: (err) => {
      console.error('Error joining event', err);
    }
  });
  }

  isUserJoined = computed(() => {
  const userId = this.authService.user()?.id;
  const participants = this.participants();

  if (!userId || !participants) {
    return false;
  }

  return participants.some(
    participation => participation.user.id === userId
  );
});

isEventFull = computed(() => {
  const maxPlayers = this.event()?.maxPlayers;
  const joinedPlayers = this.participants()?.length ?? 0;

  if (!maxPlayers) {
    return false;
  }

  return joinedPlayers >= maxPlayers;
});

}
