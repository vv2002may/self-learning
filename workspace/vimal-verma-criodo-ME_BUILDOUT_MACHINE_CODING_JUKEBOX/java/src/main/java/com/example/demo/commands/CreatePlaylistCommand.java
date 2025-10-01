package com.example.demo.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.example.demo.entities.Playlist;
import com.example.demo.services.PlaylistService;

public class CreatePlaylistCommand implements ICommand{

    private final PlaylistService playlistService;

    public CreatePlaylistCommand(PlaylistService playlistService){
        this.playlistService=playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String playlistName=tokens.get(1);
        List<Long> songIds=new ArrayList<>();
        for(int i=2;i<tokens.size();i++){
            songIds.add(Long.valueOf(tokens.get(i)));
        }
        Playlist playlist=playlistService.createPlaylist(playlistName, songIds);
        System.out.println(playlist.toString());
    }
    
}
