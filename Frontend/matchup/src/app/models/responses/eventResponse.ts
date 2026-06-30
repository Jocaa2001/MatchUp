import { EventType } from "@angular/router";

export interface EventResponse {
  id: number;
  sportId: number;
  locationId: number;
  userId: number;
  startTime: string;
  maxPlayers: number;
  status: EventType;
}