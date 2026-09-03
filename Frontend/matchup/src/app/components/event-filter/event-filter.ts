import { Component, EventEmitter, inject, OnInit, Output, signal } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';
import { SportResponse } from '../../models/responses/sportResponse';
import { SportService } from '../../services/sport.service';

@Component({
  selector: 'app-event-filter',
  imports: [TranslatePipe],
  standalone: true,
  templateUrl: './event-filter.html',
  styleUrl: './event-filter.scss',
})
export class EventFilter implements OnInit {
@Output() sportChange = new EventEmitter<number | null>();
private sportService = inject(SportService);
selectedSportId = signal<number | null>(null);

public sports = signal<SportResponse[]>([]);

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

}

onSportChange(event: Event) {
  const value = (event.target as HTMLSelectElement).value;
  const sportId = value ? Number(value) : null;
  this.selectedSportId.set(sportId);
  this.sportChange.emit(sportId);
}

}
