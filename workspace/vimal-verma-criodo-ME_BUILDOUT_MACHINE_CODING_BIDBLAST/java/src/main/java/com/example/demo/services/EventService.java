package com.example.demo.services;

import java.time.LocalDate;
import com.example.demo.entities.Event;
import com.example.demo.repositories.IEventRepo;

public class EventService {
    private final IEventRepo eventRepo;

    public EventService(IEventRepo eventRepo){
        this.eventRepo=eventRepo;
    }

    public Event addEvent(String name, String prize, LocalDate date){
        Event event=new Event(name, prize, date);
        event=eventRepo.save(event);
        return event;
    }
}
