package com.example.demo.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Member {
    private Long id;
    private String name;
    private Long superCoins;
    private Event event;
    // private Set<Long> bids;
    private Long bid;

    public Member(String name, Long superCoins) {
        this.id = null;
        this.name = name;
        this.superCoins = superCoins;
        event=null;
        // bids=null;
    }
    public Member(Long id, Member member) {
        this.id = id;
        this.name = member.name;
        this.superCoins = member.superCoins;
        event=null;
        // bids=new HashSet<>();
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

    public Long getSuperCoins() {
        return superCoins;
    }

    public void setSuperCoins(Long superCoins) {
        this.superCoins = superCoins;
    }
    
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    // public List<Long> getBids() {
    //     return new ArrayList<>(bids);
    // }
    // public void setBids(Long bid) {
    //     this.bids.add(bid);
    // }
    public Long getBid() {
        return bid;
    }
    public void setBid(Long bid) {
        this.bid = bid;
    }

    




}
