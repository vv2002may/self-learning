package com.example.demo.entities;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Event {
    private Long id;
    private String name;
    private String prize;
    private LocalDate date;
    private Set<Member> members;

    public Event(String name, String prize, LocalDate date) {
        this.id = null;
        this.name = name;
        this.prize = prize;
        this.date = date;
        members=null;
    }
    public Event(Long id, Event event) {
        this.id = id;
        this.name = event.name;
        this.prize = event.prize;
        this.date = event.date;
        members=new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }
    public void setMembers(Member member) {
        this.members.add(member);
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
