package org.dhbw.movietunes.extract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.dhbw.movietunes.exception.ExtractorException;
import org.dhbw.movietunes.model.Song;

public class Extractor {

  private JsonElement getFirstPlaylistElement(String playlistSearchResult) {
    JsonElement root = new JsonParser().parse(playlistSearchResult);
    JsonArray playlists = root.getAsJsonObject()
            .getAsJsonObject("playlists").getAsJsonArray("items");

    if (playlists.size() == 0) {
      throw new ExtractorException("No playlist found!");
    }

    return playlists.get(0);
  }

  public String extractPlaylistIdFromSearchResult(String searchResult) {
    JsonElement firstPlaylist = getFirstPlaylistElement(searchResult);

    return firstPlaylist.getAsJsonObject().getAsJsonPrimitive("id").getAsString();
  }

  public String extractUserIdFromSearchResult(String searchResult) {
    JsonElement firstPlaylist = getFirstPlaylistElement(searchResult);

    return firstPlaylist.getAsJsonObject()
            .getAsJsonObject("owner").getAsJsonPrimitive("id").getAsString();
  }

  public String extractSpotifyUrl(String searchResult) {
    JsonElement firstPlaylist = getFirstPlaylistElement(searchResult);

    return firstPlaylist.getAsJsonObject().getAsJsonObject("external_urls")
            .getAsJsonPrimitive("spotify").getAsString();
  }

  private String convertToSeconds(String s) {
    int ml = Integer.parseInt(s);
    ml = ml / 1000;
    int min = ml / 60;
    int sec = ml % 60;

    return String.valueOf(min) + ":" + String.valueOf(sec);
  }

  public Song extractSingleSong(JsonElement trackElement) {

    JsonObject track = trackElement.getAsJsonObject();
    Song result = new Song();

    String songTitle = track.getAsJsonPrimitive("name").getAsString();
    String duration = convertToSeconds(track.getAsJsonPrimitive("duration_ms").getAsString());
    String trackId = track.getAsJsonPrimitive("id").getAsString();
    String url = track.getAsJsonPrimitive("uri").getAsString();
    String artist = extractArtistName(track);

    result.setSongTitle(songTitle);
    result.setDuration(duration);
    result.setSinger(artist);
    result.setTrackId(trackId);
    result.setUri(url);

    return result;
  }

  private String extractArtistName(JsonObject track) {
    JsonArray artists = track.getAsJsonArray("artists");

    if (artists.size() == 0) {
      throw new ExtractorException("No artists found!");
    }

    JsonElement singleArtist = artists.get(0);
    return singleArtist.getAsJsonObject().getAsJsonPrimitive("name").getAsString();
  }

  public List<Song> extractSongsFromTracklistDetails(String tracklistDetailsResponse) {
    JsonElement root = new JsonParser().parse(tracklistDetailsResponse);

    JsonArray items = root.getAsJsonObject().getAsJsonArray("items");
    if (items.size() < 0) {
      throw new ExtractorException("No items found!");
    }

    List<Song> songs = new ArrayList<>();
    for (JsonElement item : items) {
      JsonObject track = item.getAsJsonObject().getAsJsonObject("track");
      songs.add(extractSingleSong(track));
    }
    return songs;
  }

  public List<Song> extractSongsFromRecommendationsResponse(String recommendationsBody) {
    JsonElement root = new JsonParser().parse(recommendationsBody);

    JsonArray tracks = root.getAsJsonObject().getAsJsonArray("tracks");
    if (tracks.size() < 0) {
      throw new ExtractorException("No tracks found!");
    }

    List<Song> songs = new ArrayList<>();
    for (JsonElement track : tracks) {
      songs.add(extractSingleSong(track));
    }
    return songs;
  }

}
