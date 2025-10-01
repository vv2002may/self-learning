package com.example.demo.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.example.demo.entities.Play;
import com.example.demo.entities.Playlist;
import com.example.demo.services.PlaylistService;

public class PlaySongCommand implements ICommand {
    private final PlaylistService playlistService;

    public PlaySongCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void invoke(List<String> tokens) {
        String command = tokens.get(0);

        String playlistName = Play.getPlayingPlaylist();
        Long playingSong = Play.getPlayingSong();

        Playlist playlist = playlistService.getPlaylist(playlistName);

        // Set<Long> songIds = playlist.getSongIds();
        List<Long> songList = playlist.getSongIds();

        if (command.equalsIgnoreCase("PLAY_SONG")) {
            if (playingSong == null) {
                Play.setPlayingSong(songList.get(0));
                System.out.println("Song [id=" + Play.getPlayingSong() + "] is playing!");
            } else {
                System.out.println("Song [id=" + playingSong + "] is paused!");
            }
        } else if (command.equalsIgnoreCase("STOP_SONG")) {
            System.out.println("Song [id=" + playingSong + "] is stopped!");
        } else {
            int n = songList.size();
            int currSongIndex = 0;
            for (int i = 0; i < n; i++) {
                Long songId = songList.get(i);
                if (playingSong == songId) {
                    currSongIndex = i;
                    break;
                }
            }
            if (command.equalsIgnoreCase("NEXT_SONG")) {
                Play.setPlayingSong(songList.get((currSongIndex + 1) % n));
                System.out.println("Song [id=" + Play.getPlayingSong() + "] is playing!");
            } else if (command.equalsIgnoreCase("PREVIOUS_SONG")) {
                if(currSongIndex==0){
                    Play.setPlayingSong(songList.get(n-1));
                }
                else{
                    Play.setPlayingSong(songList.get((currSongIndex - 1) % n));
                }
                System.out.println("Song [id=" + Play.getPlayingSong() + "] is playing!");
            }

        }
    }

}
