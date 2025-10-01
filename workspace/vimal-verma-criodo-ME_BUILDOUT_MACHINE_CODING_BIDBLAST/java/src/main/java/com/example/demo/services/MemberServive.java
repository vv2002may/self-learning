package com.example.demo.services;

import com.example.demo.entities.Member;
import com.example.demo.repositories.IMemberRepo;

public class MemberServive {
    private final IMemberRepo memberRepo;

    public MemberServive(IMemberRepo memberRepo){
        this.memberRepo=memberRepo;
    }

    public Member addMember(String name, Long superCoins){
        Member member=new Member(name,superCoins);
        member=memberRepo.save(member);
        return member;
    }
    

}
