package com.example.demo.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Playlist {
    private final Long id;
    private final String playlistName;
    private final List<Long> songIds;

    public Playlist(String playlistName, List<Long> songIds) {
        this.id = null;
        this.playlistName = playlistName;
        this.songIds = songIds;
    }

    public Playlist(Long id, Playlist other) {
        this.id = id;
        this.playlistName = other.playlistName;
        this.songIds = other.songIds;
    }

    public Long getId() {
        return id;
    }

    public String getPlaylistName() {
        return playlistName;
    }
    
    public void addSong(Long songId){
        songIds.add(songId);
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public Boolean deleteSong(Long songId){
        return songIds.remove(songId);
    }

    @Override
    public String toString() {
        return "Playlist [id=" + id + "]";
    }
    
}
