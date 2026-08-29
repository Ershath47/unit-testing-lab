package com.sddevops.junit_maven.eclipse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class SongCollection {
    private List<Song> songs;

    public SongCollection(int capacity) {
        this.songs = new ArrayList<>(capacity);
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public List<Song> getSongs() {
        return this.songs;
    }

    // Fetch raw JSON from API
    protected String fetchSongJson() {
        String urlString = "https://mocki.io/v1/e1b14dea-d272-4b03-b102-252325168182";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Parse JSON and create Song object
    public Song fetchSongOfTheDay() {
        try {
            String jsonStr = fetchSongJson();
            if (jsonStr == null) return null;

            JSONObject json = new JSONObject(jsonStr);
            Song song = new Song(
                json.getString("id"),
                json.getString("title"),
                json.getString("artiste"),
                json.getDouble("songLength")
            );

            // Practice logic: only add certain artistes
            if (song.getArtiste().equals("Taylor Swift")) {
                song.setArtiste("TS");
                this.addSong(song);
            } else if (song.getArtiste().equals("Bruno Mars")) {
                song.setArtiste("BM");
                this.addSong(song);
            }
            return song;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
