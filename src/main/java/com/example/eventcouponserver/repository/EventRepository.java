package com.example.eventcouponserver.repository;

import com.example.eventcouponserver.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.eventStatus = 'OPEN'")
    List<Event> findEventsByEventStatus();
}
