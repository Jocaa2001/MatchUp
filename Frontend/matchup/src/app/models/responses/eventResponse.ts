import { SportResponse } from "./sportResponse";
import { LocationResponse } from "./locationRespons";
import { UserResponse } from "./userResponse";

export type EventStatus =
  | "CREATED"
  | "ACTIVE"
  | "CANCELLED"
  | "FINISHED";

export interface EventResponse {
  id: number;
  sport: SportResponse;
  location: LocationResponse;
  user: UserResponse;
  startTime: string;
  maxPlayers: number;
  status: EventStatus;
}