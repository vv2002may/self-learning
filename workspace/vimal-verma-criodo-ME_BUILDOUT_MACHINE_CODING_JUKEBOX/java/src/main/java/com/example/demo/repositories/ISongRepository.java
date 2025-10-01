package com.example.demo.repositories;

import java.util.Map;
import com.example.demo.entities.Song;

public interface ISongRepository {
    Song save(Song other);
    Song findById(Long songId);
    Map<Long, Song> findAll();
}
