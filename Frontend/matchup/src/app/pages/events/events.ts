import { Component, effect, inject, OnInit, signal } from '@angular/core';
import { EventCard } from "../../components/event-card/event-card";
import { EventFilter } from "../../components/event-filter/event-filter";
import { EventsService } from '../../services/events.service';
import { toSignal } from '@angular/core/rxjs-interop';
import { Router, RouterLink } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';
import { debounceTime, distinctUntilChanged, Subject, timeout } from 'rxjs';
import { EventResponse, EventStatus } from '../../models/responses/eventResponse';

@Component({
  selector: 'app-events',
  imports: [EventCard, EventFilter, RouterLink, TranslatePipe],
  standalone: true,
  templateUrl: './events.html',
  styleUrl: './events.scss',
})
export class Events {

  eventsService = inject(EventsService);
  selectedSportId = signal<number | null>(null);
  selectedStatus = signal<EventStatus | null>(null);
  searchQuery = signal('');
  private searchSubject = new Subject<string>();
  events = signal<EventResponse[]>([]);

  nextCursor = signal<number | null>(null);

  hasNext = signal(true);

 constructor() {
    this.loadEvents();

    this.searchSubject
    .pipe(debounceTime(400),distinctUntilChanged())
    .subscribe(search => {
        this.nextCursor.set(null);
        this.hasNext.set(true);
        this.loadEvents();
    });
  }

  loadEvents() {
    this.eventsService.getEventsCursor(undefined,6 ,this.selectedSportId() ?? undefined, this.searchQuery(), this.selectedStatus() ?? undefined)
    .subscribe({
      next: (response) => {
        this.events.set(response.events);
        this.nextCursor.set(response.nextCursor);
        this.hasNext.set(response.hasNext);
      },
      error: (error) => {
        console.error('Failed to load events:', error);
      }
    });
  }

  loadMoreEvents() {

    const cursor = this.nextCursor();

    if (cursor === null || !this.hasNext()) {
      return;
    }

    this.eventsService.getEventsCursor(cursor).subscribe({
      next: (response) => {

        this.events.update(events => [
          ...events,
          ...response.events
        ]);

        this.nextCursor.set(response.nextCursor);
        this.hasNext.set(response.hasNext);
      },
      error: (error) => {
        console.error('Failed to load more events:', error);
      }
    });
  }
  onSportChange(sportId: number | null) {
  this.selectedSportId.set(sportId);

  this.nextCursor.set(null);
  this.hasNext.set(true);

  this.loadEvents();
}

onSearchChange(search: string) {
  this.searchQuery.set(search);
  this.searchSubject.next(search);
}

onStatusChange(status: EventStatus | null) {
  this.selectedStatus.set(status);
  this.nextCursor.set(null);
  this.hasNext.set(true);
  console.log(this.selectedStatus())
  this.loadEvents();
}

}
