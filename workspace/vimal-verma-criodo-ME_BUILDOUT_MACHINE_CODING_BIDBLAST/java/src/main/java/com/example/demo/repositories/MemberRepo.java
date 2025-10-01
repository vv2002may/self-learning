package com.example.demo.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.example.demo.entities.Member;

public class MemberRepo implements IMemberRepo {

    private final Map<Long,Member> memberMap = new HashMap<>();;
    private AtomicLong idCounter = new AtomicLong(1);

    @Override
    public Member save(Member member) {
        Member memberNew =new Member(idCounter.getAndIncrement(),member);
        memberMap.putIfAbsent(memberNew.getId(),memberNew);
        return memberNew;
    }


    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberMap.get(id));
    }
    
}
