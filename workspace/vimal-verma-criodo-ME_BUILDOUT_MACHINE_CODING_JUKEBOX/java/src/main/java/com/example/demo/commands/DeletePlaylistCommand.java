package com.example.demo.commands;

import java.util.List;
import com.example.demo.services.PlaylistService;

public class DeletePlaylistCommand implements ICommand{
    private final PlaylistService playlistService;

    public DeletePlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String playlistName=tokens.get(1);
        playlistService.deletePlaylist(playlistName);
        System.out.println("Playlist "+playlistName+" is deleted!");
    }
    
}
