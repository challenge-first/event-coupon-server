package com.example.eventcouponserver.service;

import com.example.eventcouponserver.entity.Event;
import com.example.eventcouponserver.repository.EventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//@Component
public class EventScheduler {

    private final EventRepository eventRepository;

    public EventScheduler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Scheduled(cron = "${event.schedule.cron}")
    @Transactional
    public void closingEventScheduled() {
        List<Event> openEvent = eventRepository.findEventsByEventStatus();
        LocalDateTime now = LocalDateTime.now();
        for (Event event : openEvent) {
            if (event.getClosingTime().isBefore(now)) {
                event.closeEventAtClosingTime();
            }
        }
    }
}
