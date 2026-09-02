package com.matchup.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EventCursorResponse {

    List<EventDTO> events;
    Long nextCursor;
    boolean hasNext;

}
