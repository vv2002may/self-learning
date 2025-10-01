package com.example.demo.entities;

public class Play {
    private static Play play;

    private static String playingPlaylist;
    private static Long playingSong;

    public Play(String playlistName) {
        Play.playingPlaylist = playlistName;
        Play.playingSong = null;
    }

    /**
     * Retrieves or creates a Play instance associated with the specified playlist.
     * If no Play instance exists, it creates a new one. If one already exists,
     * it updates the existing instance with the new playlist name.
     *
     * @param playlistName The name of the playlist to associate with the Play instance.
     * @return The Play instance, either newly created or existing and updated.
     */
    public static Play getPlay(String playlistName) {
        if (play == null) {
            play = new Play(playlistName);
        }
        else{
            Play.playingPlaylist=playlistName;
            Play.playingSong = null;
        }
        return play;
    }

    public static Play getPlay() {
        return play;
    }

    public static String getPlayingPlaylist() {
        return Play.playingPlaylist;
    }

    public static Long getPlayingSong() {
        return Play.playingSong;
    }

    public static void setPlayingSong(Long playingSong) {
        Play.playingSong = playingSong;
    }

}
