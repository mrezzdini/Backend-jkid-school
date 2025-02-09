package com.techno.events.model.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByClassesAndTitleAndDetailsAndStartDateAndDeadline(
            String classes,
            String title,
            String details,
            String startDate,
            String deadline);
}