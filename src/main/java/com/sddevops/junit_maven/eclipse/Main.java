package com.sddevops.junit_maven.eclipse;

public class Main {
    public static void main(String[] args) {
        SongCollection sc = new SongCollection(3);
        Song song = sc.fetchSongOfTheDay();
        if (song != null) {
            System.out.println("Song of the Day: " + song);
        } else {
            System.out.println("No song fetched.");
        }
    }
}
