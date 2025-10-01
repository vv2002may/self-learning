package com.crio.xlido.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Question {
    private final Long id;
    private final String content;
    private final Long userId;
    private final Long eventId;
    private final Map<Long,String> reply;
    private final Set<Long> upvotedUsers;
    private final LocalDateTime createdAt;

    public Question(String content,Long userId, Long eventId){
        this.id=null;
        this.content=content;
        this.userId=userId;
        this.eventId=eventId;
        this.reply=new HashMap<>();
        this.createdAt=LocalDateTime.now();
        this.upvotedUsers = new HashSet<>();
    }

    public Question(Long id, Question other){
        this.id=id;
        this.content=other.content;
        this.userId=other.userId;
        this.eventId=other.eventId;
        this.upvotedUsers=other.upvotedUsers;
        this.reply=other.reply;
        this.createdAt=other.createdAt;
    }
    public Map<Long,String> getReply(){
        return this.reply;
    }

    public boolean hasUpvoted(Long userId) {
        return upvotedUsers.contains(userId);
    }
    public int getVoteCount() {
        return upvotedUsers.size();
    }
    public Set<Long> getUpvotedUsers() {
        return upvotedUsers;
    }

    public void addUpvote(Long userId) {
        upvotedUsers.add(userId);
    }

    public void setReply(String content, Long userId){
        reply.put(userId, content);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEventId() {
        return eventId;
    }

    @Override
    public String toString() {
        return "Question [content=" + content + ", id=" + id + "]";
    }
}
