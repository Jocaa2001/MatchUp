import { Component } from '@angular/core';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-event-filter',
  imports: [TranslatePipe],
  standalone: true,
  templateUrl: './event-filter.html',
  styleUrl: './event-filter.scss',
})
export class EventFilter {}
