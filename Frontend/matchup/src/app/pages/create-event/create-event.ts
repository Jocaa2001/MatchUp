import { Component, inject, OnInit, signal } from '@angular/core';
import { SportService } from '../../services/sport.service';
import { SportResponse } from '../../models/responses/sportResponse';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EventsService } from '../../services/events.service';
import { CreateEventRequest } from '../../models/requests/createEventRequest';
import { Router } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-create-event',
  imports: [ReactiveFormsModule, TranslatePipe],
  templateUrl: './create-event.html',
  styleUrl: './create-event.scss',
})
export class CreateEvent implements OnInit {
  public sports = signal<SportResponse[]>([]);
  private sportService = inject(SportService);
  private eventService = inject(EventsService);
  private router = inject(Router)

 createEventForm = new FormGroup({
  sportId: new FormControl<number | null>(null, Validators.required),

  startDate: new FormControl('', Validators.required),
  startTime: new FormControl('', Validators.required),
  endDate: new FormControl('', Validators.required),
  endTime: new FormControl('', Validators.required),
  locationName: new FormControl('', Validators.required),
  city: new FormControl('', Validators.required),
  address: new FormControl('', Validators.required),
  description: new FormControl(''),
  indoor: new FormControl(false),
  maxPlayers: new FormControl<number>(2, [
  Validators.required,
  Validators.min(2)
])
});
  

ngOnInit(): void {
  this.sportService.getSports().subscribe({
    next: (sports) => {
      this.sports.set(sports);
    },
    error: (err) => {
      console.error(err);
    }
  });

}

increasePlayers() {
  const current = Number(this.createEventForm.controls.maxPlayers.value ?? 2);
  this.createEventForm.controls.maxPlayers.setValue(current + 1);
}


decreasePlayers() {
  const current = this.createEventForm.controls.maxPlayers.value ?? 2;

  if (current > 2) {
    this.createEventForm.controls.maxPlayers.setValue(current - 1);
  }
}

createEvent(){
  const request: CreateEventRequest = {
   sportId: this.createEventForm.value.sportId!,

  startTime: `${this.createEventForm.value.startDate!}T${this.createEventForm.value.startTime!}`,
  endTime: `${this.createEventForm.value.endDate!}T${this.createEventForm.value.endTime!}`,

  maxPlayers: this.createEventForm.value.maxPlayers!,

  location: {
    name: this.createEventForm.value.locationName!,
    city: this.createEventForm.value.city!,
    address: this.createEventForm.value.address!,
    indoor: this.createEventForm.value.indoor!,
    description: this.createEventForm.value.description!
  }
  
};

this.eventService.createEvent(request).subscribe({
  next: (event) => {
    console.log('Event created successfully', event);
    this.router.navigate(['/events']);
  },
  error: (error) => {
    console.error('Error creating event', error);
  }
});
}


}
