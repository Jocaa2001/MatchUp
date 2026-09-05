import { Component, computed, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { EventResponse, EventStatus } from '../../models/responses/eventResponse';
import { EventsService } from '../../services/events.service';
import { DatePipe } from '@angular/common';
import { ParticipationResponse } from '../../models/responses/participationResponse';
import { Auth } from '../../services/auth';
import { ParticipationService } from '../../services/participation.service';
import { HttpErrorResponse } from '@angular/common/http';
import { TranslatePipe } from '@ngx-translate/core';
import { ProfileDetailsService } from '../../services/profileDetals.service';
import { forkJoin } from 'rxjs';
import { EventReview } from '../event-review/event-review';


@Component({
  selector: 'app-event-detail',
  imports: [RouterLink, DatePipe, TranslatePipe,EventReview],
  templateUrl: './event-detail.html',
  styleUrl: './event-detail.scss',
})
export class EventDetail implements OnInit {

  private route = inject(ActivatedRoute);
  private eventService = inject(EventsService);
  private profileDetails = inject(ProfileDetailsService);

  authService = inject(Auth);
  participationService = inject(ParticipationService);

  participants = signal<ParticipationResponse[] | null>(null);
  event = signal<EventResponse | null>(null);

  participantAvatars = signal<Record<number, string>>({});
  organizerAvatar = signal<string | null>(null);


  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.eventService.getEventById(id).subscribe({
      next: (event) => {
        this.event.set(event);

        console.log(event);

        const avatarUrl = event.user?.profile?.avatarUrl;

        if (avatarUrl) {
          this.profileDetails.getAvatarByAvatarUrl(avatarUrl).subscribe({
            next: (blob) => {
              const imageUrl = URL.createObjectURL(blob);
              this.organizerAvatar.set(imageUrl);
            },
            error: (err) => {
              console.error('Failed to load organizer avatar:', err);
            }
          });
        }
      },
      error: (err) => {
        console.error('Failed to load event:', err);
      }
    });


    this.eventService.getParticipantsByEventId(id).subscribe({
      next: (participants) => {

        this.participants.set(participants);

        console.log(this.participants());

        this.loadParticipantAvatars(participants);
      },

      error: (err) => {
        console.error('Failed to load participants:', err);
      }
    });
  }


  private loadParticipantAvatars(
    participants: ParticipationResponse[]
  ): void {

    const requests = participants
      .filter(participation => participation.user.profile?.avatarUrl)
      .map(participation => {

        const userId = participation.user.id;
        const avatarUrl = participation.user.profile.avatarUrl;

        return this.profileDetails
          .getAvatarByAvatarUrl(avatarUrl)
          .pipe();
      });

    if (requests.length === 0) {
      return;
    }

    forkJoin(requests).subscribe({
      next: (blobs) => {

        const avatars: Record<number, string> = {};

        participants
          .filter(participation => participation.user.profile?.avatarUrl)
          .forEach((participation, index) => {

            const userId = participation.user.id;
            const blob = blobs[index];

            avatars[userId] = URL.createObjectURL(blob);
          });

        this.participantAvatars.set(avatars);
      },

      error: (err) => {
        console.error('Failed to load participant avatars:', err);
      }
    });
  }


  getParticipantAvatar(userId: number): string | null {
    return this.participantAvatars()[userId] ?? null;
  }


  emptySlots = computed(() => {

    const maxPlayers = this.event()?.maxPlayers ?? 0;
    const joined = this.participants()?.length ?? 0;

    return Array(Math.max(maxPlayers - joined, 0));
  });


  joinEvent() {

    if (this.isEventCancelled()) {
      return;
    }

    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.eventService.joinEvent(id).subscribe({
      next: (response) => {
        console.log(response);
        console.log('Successfully joined event', response);

        window.location.reload();
      },

      error: (err) => {
        console.error('Error joining event:', err);
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


  isEventCreator = computed(() => {

    const currentUserId = this.authService.user()?.id;
    const eventUserId = this.event()?.user?.id;

    if (!currentUserId || !eventUserId) {
      return false;
    }

    return currentUserId === eventUserId;
  });


  leaveEvent() {

    this.participationService.leaveEvent(this.event()!.id).subscribe({

      next: () => {
        window.location.reload();
        console.log('Successfully left event');
      },

      error: (error: HttpErrorResponse) => {
        console.error('Error leaving event:', error);
      }

    });
  }


  cancelEvent() {

    const id = this.event()!.id;

    this.eventService.updateEvent(id, {
      status: 'CANCELLED'
    }).subscribe({

      next: (response) => {
        console.log('Successfully cancelled event', response);
        this.event.set(response);
      },

      error: (error: HttpErrorResponse) => {
        console.error('Error cancelling event:', error);
      }

    });
  }


  isEventCancelled = computed(() => {
    return this.event()?.status === 'CANCELLED';
  });


  reactivateEvent() {

    const id = this.event()!.id;

    this.eventService.updateEvent(id, {
      status: 'OPEN' as EventStatus
    }).subscribe({

      next: (response) => {
        console.log('Successfully reactivated event', response);
        this.event.set(response);
      },

      error: (error: HttpErrorResponse) => {
        console.error('Error reactivating event:', error);
      }

    });
  }
}