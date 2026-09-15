package com.matchup.event.scheduler;


import com.matchup.event.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventScheduler {

    private final EventService eventService;

    @Scheduled(fixedRate = 1800000L)
    public void finishExpiredEvents() {
        eventService.findExpiredOpenEvents().forEach(event -> {
            try {
                System.out.println(event);
                eventService.finishEvent(event);
            } catch (Exception e) {
                System.out.println("Failed to finish event: " + event.getId());
            }
        });
    }

}
