import { Component, EventEmitter, inject, OnInit, Output, signal } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';
import { SportResponse } from '../../models/responses/sportResponse';
import { SportService } from '../../services/sport.service';
import { EventStatus } from '../../models/responses/eventResponse';
import { LocationService } from '../../services/location.service';
import { DistinctCityResponse } from '../../models/responses/DistinctCityResponse';

@Component({
  selector: 'app-event-filter',
  imports: [TranslatePipe],
  standalone: true,
  templateUrl: './event-filter.html',
  styleUrl: './event-filter.scss',
})
export class EventFilter implements OnInit {
@Output() sportChange = new EventEmitter<number | null>();
@Output() searchChange = new EventEmitter<string>();
@Output() statusChange = new EventEmitter<EventStatus | null>();
@Output() cityChange = new EventEmitter<string | null>();
@Output() dateChange = new EventEmitter<string | null>();

private locationService = inject(LocationService);


public eventStatuses: EventStatus[] = [
  "OPEN",
  "CANCELLED",
  "FINISHED"
];

private sportService = inject(SportService);

selectedSportId = signal<number | null>(null);
selectedCity = signal<string | null>(null);
sports = signal<SportResponse[]>([]);
selectedStatus = signal<EventStatus | null>(null);
cities = signal<DistinctCityResponse[]>([]);
selectedDate = signal<string | null>(null);



ngOnInit(): void {
  this.sportService.getSports().subscribe({
    next: (sports) => {
      this.sports.set(sports);
      console.log(this.sports())
    },
    error: (err) => {
      console.error(err);
    }
  });

  
     this.locationService.getDistinctCities().subscribe({
    next: (cities) => {
      this.cities.set(cities);
    },
    error: (err) => {
      console.error(err);
    }
  });

}

onSportChange(event: Event) {
  const value = (event.target as HTMLSelectElement).value;
  const sportId = value ? Number(value) : null;
  this.selectedSportId.set(sportId);
  this.sportChange.emit(sportId);
}

onSearchChange(event: Event) {
  const value = (event.target as HTMLInputElement).value;

  this.searchChange.emit(value);
}

onStatusChange(event: Event) {
  const value = (event.target as HTMLSelectElement).value;

  const status = value ? value as EventStatus : null;

  this.selectedStatus.set(status);
  this.statusChange.emit(status);
}

onCityChange(event: Event) {
  const value = (event.target as HTMLSelectElement).value;

  const city = value ? value : null;

  this.selectedCity.set(city);
  this.cityChange.emit(city);
}

onDateChange(event: Event) {
  const value = (event.target as HTMLInputElement).value;

  const date = value ? value : null;

  this.selectedDate.set(date);
  this.dateChange.emit(date);
}

}
