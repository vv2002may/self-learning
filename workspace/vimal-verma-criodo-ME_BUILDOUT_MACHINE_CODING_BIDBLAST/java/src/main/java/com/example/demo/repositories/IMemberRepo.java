package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Member;

public interface IMemberRepo {
    Member save(Member member);
    // boolean existsById(Long id);
    Optional<Member> findById(Long id);
    // List<Member> findAll();
    // void deleteById(Long id);
    // long count();
}
