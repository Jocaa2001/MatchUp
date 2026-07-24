import { Component, inject, OnInit, signal } from '@angular/core';
import { SportService } from '../../services/sport.service';
import { SportResponse } from '../../models/responses/sportResponse';

@Component({
  selector: 'app-create-event',
  imports: [],
  templateUrl: './create-event.html',
  styleUrl: './create-event.scss',
})
export class CreateEvent implements OnInit {
  public sports = signal<SportResponse[]>([]);
  private sportService = inject(SportService);

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


}
