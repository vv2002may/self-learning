package com.example.demo.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.text.html.HTMLDocument.Iterator;
import com.example.demo.entities.Playlist;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;

public class AddSongToPlaylist implements ICommand {

    private final PlaylistService playlistService;

    public AddSongToPlaylist(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String playlistName = tokens.get(1);
        Long songId = Long.valueOf(tokens.get(2));
        Playlist playlist = playlistService.addSongToPlaylist(playlistName, songId);
        System.out.print("Playlist " + playlistName + " is revised with [");
        // Set<Long> songIds = playlist.getSongIds();
        List<Long> songList = playlist.getSongIds();
        for (int i = 0; i < songList.size(); i++) {
            Long song = songList.get(i);
            System.out.printf("Song [id=%d]", song);

            if (i < songList.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }

}
