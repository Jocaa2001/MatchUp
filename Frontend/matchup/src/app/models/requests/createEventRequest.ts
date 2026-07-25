import { LocationRequest } from "./createLocationRequest";

export interface CreateEventRequest {
  sportId: number;
  location: LocationRequest;
  startTime: string;
  endTime: string;
  maxPlayers: number;
}