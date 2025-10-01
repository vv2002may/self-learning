package com.crio.xlido.entities;

public class Event {
    private final Long id;
    private final String name;
    private final Long organizerId;

    public Event(String name, Long organizerId){
        this.id=null;
        this.name=name;
        this.organizerId=organizerId;
    }

    public Event(Long id, Event other){
        this.id=id;
        this.name=other.name;
        this.organizerId=other.organizerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", organizerId=" + organizerId + "]";
    }
    
    
}
