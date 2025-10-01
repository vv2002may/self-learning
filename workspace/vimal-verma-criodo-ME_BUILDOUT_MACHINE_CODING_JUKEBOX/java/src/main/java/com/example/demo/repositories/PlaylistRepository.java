package com.example.demo.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import com.example.demo.entities.Playlist;

public class PlaylistRepository implements IPlaylistRepository{

    private Map<Long,Playlist> storage=new HashMap<>();
    private Map<String,Playlist> storageName=new HashMap<>();
    private AtomicLong idCounter=new AtomicLong(0);
    
    @Override
    public Playlist save(Playlist other) {
        Playlist playlist=new Playlist(idCounter.incrementAndGet(), other);
        storage.putIfAbsent(playlist.getId(), playlist);
        storageName.putIfAbsent(playlist.getPlaylistName(), playlist);
        return playlist;
    }

    @Override
    public Playlist findByName(String playlistName) {
        return storageName.get(playlistName);
    }

    @Override
    public void deletePlaylist(String playlistName) {
        storageName.remove(playlistName);
    }
    
}
