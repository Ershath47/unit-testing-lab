package com.sddevops.junit_maven.eclipse;

public class Song {
    private String id;
    private String title;
    private String artiste;
    private double songLength;

    public Song(String id, String title, String artiste, double songLength) {
        this.id = id;
        this.title = title;
        this.artiste = artiste;
        this.songLength = songLength;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtiste() { return artiste; }
    public double getSongLength() { return songLength; }

    @Override
    public String toString() {
        return this.title + " by " + this.artiste;
    }
}
