package com.example.demo.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import com.example.demo.entities.Song;

public class SongRepository implements ISongRepository {

    private Map<Long,Song> storage=new HashMap<>();
    private AtomicLong idCounter=new AtomicLong(0L);

    @Override
    public Song save(Song other) {
        Song song=new Song(idCounter.incrementAndGet(), other);
        storage.putIfAbsent(song.getId(), song);
        return song;
    }

    @Override
    public Song findById(Long songId) {
        return storage.get(songId);
    }

    @Override
    public Map<Long, Song> findAll() {
        return new HashMap<>(storage);
    }
    
    
}
