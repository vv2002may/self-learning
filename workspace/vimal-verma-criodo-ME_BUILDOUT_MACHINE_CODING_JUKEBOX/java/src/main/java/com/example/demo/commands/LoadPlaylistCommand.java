package com.example.demo.commands;

import java.util.List;
import com.example.demo.entities.Play;
import com.example.demo.services.PlaylistService;

public class LoadPlaylistCommand implements ICommand{

    private final PlaylistService playlistService;

    public LoadPlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String playlistName=tokens.get(1);
        Play play = Play.getPlay(playlistName);
        System.out.println("Playlist "+playlistName+" is loaded!");
    }
    
}
