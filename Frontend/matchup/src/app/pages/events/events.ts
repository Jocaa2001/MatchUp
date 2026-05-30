import { Component } from '@angular/core';
import { EventCard } from "../../components/event-card/event-card";
import { EventFilter } from "../../components/event-filter/event-filter";

@Component({
  selector: 'app-events',
  imports: [EventCard, EventFilter],
  standalone: true,
  templateUrl: './events.html',
  styleUrl: './events.scss',
})
export class Events {}
