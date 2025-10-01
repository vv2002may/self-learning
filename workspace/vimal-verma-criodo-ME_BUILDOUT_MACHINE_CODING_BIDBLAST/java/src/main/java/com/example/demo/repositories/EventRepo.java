package com.example.demo.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.example.demo.entities.Event;

public class EventRepo implements IEventRepo {
    private final Map<Long, Event> eventMap = new HashMap<>();;
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Event save(Event event) {
        Event eventNew = new Event(idCounter.getAndIncrement(), event);
        eventMap.putIfAbsent(eventNew.getId(), eventNew);
        return eventNew;
    }


    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(eventMap.get(id));
    }

}
