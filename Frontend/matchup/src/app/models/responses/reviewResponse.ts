import { EventResponse } from "./eventResponse";
import { UserResponse } from "./userResponse";

export interface ReviewResponse {
  id: number;
  user: UserResponse;
  event: EventResponse;
  rating: number;
  comment: string;
  createdAt: string;
}