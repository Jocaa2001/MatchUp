import { EventResponse } from './eventResponse';
import { UserResponse } from './userResponse';
import { ParticipationStatus } from '../enum/ParticipationStatus';

export interface ParticipationResponse {
  id: number;
  user: UserResponse;
  event: EventResponse;
  status: ParticipationStatus;
}