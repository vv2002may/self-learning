package com.example.demo.repositories;

import com.example.demo.entities.Playlist;

public interface IPlaylistRepository {
    Playlist save(Playlist other);
    Playlist findByName(String playlistName);
    void deletePlaylist(String playlistName);
}
