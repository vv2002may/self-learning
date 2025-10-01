package com.example.demo.services;

import java.util.Map;
import com.example.demo.entities.Song;
import com.example.demo.repositories.ISongRepository;

public class SongService {
    
    private ISongRepository songRepository;

    public SongService(ISongRepository songRepository){
        this.songRepository=songRepository;
    }

    public Song addSong(String songName, String artistName, String albumName, String genre){
        Song song =new Song(songName, artistName, albumName, genre);
        return songRepository.save(song);
    }

    public Song findById(Long songId){
        return songRepository.findById(songId);
    }
    public Map<Long,Song> listSongs(){
        Map<Long,Song> songs=songRepository.findAll();
        return songs;
    }
}
