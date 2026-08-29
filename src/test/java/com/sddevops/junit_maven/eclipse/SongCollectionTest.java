package com.sddevops.junit_maven.eclipse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SongCollectionTest {

    @Test
    public void testFetchSongOfTheDay() {
        String mockJson = """
        {
            "id": "001",
            "title": "Mock Song",
            "artiste": "Mock Artist",
            "songLength": 4.25
        }
        """;

        SongCollection collection = spy(new SongCollection(3));
        doReturn(mockJson).when(collection).fetchSongJson();

        Song result = collection.fetchSongOfTheDay();

        assertNotNull(result);
        assertEquals("001", result.getId());
        assertEquals("Mock Song", result.getTitle());
        assertEquals("Mock Artist", result.getArtiste());
        assertEquals(4.25, result.getSongLength());
    }

    @Test
    public void testInvalidFetchSongOfTheDay() {
        SongCollection collection = spy(new SongCollection(3));
        doReturn(null).when(collection).fetchSongJson();

        Song result = collection.fetchSongOfTheDay();
        assertNull(result);
    }

    @Test
    public void testExceptionHandlingInFetchSongOfTheDay() {
        SongCollection collection = spy(new SongCollection(3));
        doThrow(new RuntimeException("API failed")).when(collection).fetchSongJson();

        Song result = collection.fetchSongOfTheDay();
        assertNull(result);
        assertEquals(0, collection.getSongs().size());
    }

    @Test
    public void testTaylorSwiftArtisteConversion() {
        String mockJson = """
        {
            "id": "002",
            "title": "Love Story",
            "artiste": "Taylor Swift",
            "songLength": 3.55
        }
        """;

        SongCollection collection = spy(new SongCollection(3));
        doReturn(mockJson).when(collection).fetchSongJson();

        Song result = collection.fetchSongOfTheDay();

        assertNotNull(result);
        assertEquals("TS", result.getArtiste());
        assertEquals(1, collection.getSongs().size());
    }

    @Test
    public void testBrunoMarsArtisteConversion() {
        String mockJson = """
        {
            "id": "003",
            "title": "Grenade",
            "artiste": "Bruno Mars",
            "songLength": 4.0
        }
        """;

        SongCollection collection = spy(new SongCollection(3));
        doReturn(mockJson).when(collection).fetchSongJson();

        Song result = collection.fetchSongOfTheDay();

        assertNotNull(result);
        assertEquals("BM", result.getArtiste());
        assertEquals(1, collection.getSongs().size());
    }
}
