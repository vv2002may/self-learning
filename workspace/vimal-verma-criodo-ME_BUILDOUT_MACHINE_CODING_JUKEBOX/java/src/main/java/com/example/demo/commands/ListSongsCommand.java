package com.example.demo.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

public class ListSongsCommand implements ICommand {
    private final SongService songService;

    public ListSongsCommand(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void invoke(List<String> tokens) {

        Map<Long, Song> songs = songService.listSongs();

        System.out.print("[");
        List<Long> songList = new ArrayList<>(songs.keySet());
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
