package com.crio.xlido.services;

import java.lang.StackWalker.Option;
import java.util.Optional;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.*;;

public class EventService {
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;

    public EventService(IEventRepository eventRepository, IUserRepository userRepository){
        this.eventRepository=eventRepository;
        this.userRepository=userRepository;
    }
    
    public Event createEvent(String name, Long organizerId){
        Optional<User> user=userRepository.findById(organizerId);
        if(!user.isPresent()){
            throw new RuntimeException("User with an id " + organizerId + " does not exist");
        } 
        Event event= new Event(name,organizerId);
        return eventRepository.save(event);
    }

    public Boolean deleteEvent(Long eventId, Long userId){
        Optional<Event> event=eventRepository.findById(eventId);
        if(!event.isPresent()){
            throw new RuntimeException("Event with an id " + eventId + " does not exist");
        }
        Optional<User> user=userRepository.findById(userId);
        if(!user.isPresent()){
            throw new RuntimeException("User with an id "+userId+" does not exist");
        }
        if(event.get().getOrganizerId()!=userId){
            throw new RuntimeException("User with an id " + userId + " is not a organizer of Event with an id " + eventId);
        }
        eventRepository.delete(eventId, userId);
        return true;
    }

}
