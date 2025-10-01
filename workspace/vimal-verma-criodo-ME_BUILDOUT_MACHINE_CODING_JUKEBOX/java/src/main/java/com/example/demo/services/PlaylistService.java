package com.example.demo.services;

import java.util.List;
import java.util.Set;
import com.example.demo.entities.Playlist;
import com.example.demo.repositories.IPlaylistRepository;

public class PlaylistService {
    private IPlaylistRepository playlistRepository;

    public PlaylistService(IPlaylistRepository playlistRepository){
        this.playlistRepository=playlistRepository;
    }

    public Playlist createPlaylist(String playlistName, List<Long> songIds){
        Playlist playlist=new Playlist(playlistName, songIds);
        return playlistRepository.save(playlist);
    }
    public Playlist addSongToPlaylist(String playlistName, Long songId){
        Playlist playlist=playlistRepository.findByName(playlistName);
        playlist.addSong(songId);
        return playlist;
    }

    public Playlist deleteSong(String playlistName, Long songId){
        Playlist playlist= playlistRepository.findByName(playlistName);
        playlist.deleteSong(songId);
        return playlist;
    }
    public void deletePlaylist(String playlistName){
        playlistRepository.deletePlaylist(playlistName);
    }

    public Playlist getPlaylist(String playlistName){
        return playlistRepository.findByName(playlistName);
    }
}
