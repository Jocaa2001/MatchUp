import { EventResponse } from './eventResponse';

export interface EventCursorResponse {
  events: EventResponse[];
  nextCursor: number | null;
  hasNext: boolean;
}