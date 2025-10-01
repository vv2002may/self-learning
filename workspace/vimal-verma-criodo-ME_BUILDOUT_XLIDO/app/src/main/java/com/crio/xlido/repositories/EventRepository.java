package com.crio.xlido.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Event;

public class EventRepository implements IEventRepository{

    private final Map<Long, Event> storageId = new HashMap<>();
    private AtomicLong idCounter = new AtomicLong(0);

    @Override
    public Event save(Event entity) {
        Event event = new Event(idCounter.incrementAndGet(),entity);
        storageId.putIfAbsent(event.getId(),event);
        return event;
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(storageId.values());
    }

    @Override
    public Optional<Event> findById(Long id) {
        return Optional.ofNullable(storageId.get(id));
    }

    @Override
    public void delete(Long eventId, Long userId) {
        Event event=storageId.get(eventId);

        if(event!=null && event.getOrganizerId().equals(userId)){
            storageId.remove(eventId);
        }
        
    }

}
