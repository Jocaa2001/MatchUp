import { ReviewResponse } from "./reviewResponse";

export interface ReviewEventResponse {
  reviews: ReviewResponse[];
  averageRating: number;
  reviewCount: number;
  userJoined: boolean;
}