package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Event;

public interface IEventRepo {
    Event save(Event event);
    // boolean existsById(Long id);
    Optional<Event> findById(Long id);
    // List<Event> findAll();
    // void deleteById(Long id);
    // long count();
}
