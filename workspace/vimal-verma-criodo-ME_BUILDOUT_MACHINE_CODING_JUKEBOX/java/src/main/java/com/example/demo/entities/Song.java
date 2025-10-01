package com.example.demo.entities;

public class Song {
    private final Long id;
    private final String songName;
    private final String artistName;
    private final String albumName;
    private final String genre;

    public Song(String songName, String artistName, String albumName, String genre){
        this.id=null;
        this.songName=songName;
        this.artistName=artistName;
        this.albumName=albumName;
        this.genre=genre;
    }   

    public Song(Long id, Song other){
        this.id=id;
        this.songName=other.songName;
        this.artistName=other.artistName;
        this.albumName=other.albumName;
        this.genre=other.genre;
    }

    public Long getId() {
        return id;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Song [id=" + id + "]";
    }

}
