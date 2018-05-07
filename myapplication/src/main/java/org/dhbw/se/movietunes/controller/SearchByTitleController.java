package org.dhbw.se.movietunes.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.dhbw.se.movietunes.model.Song;
import org.dhbw.se.movietunes.model.Soundtrack;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//import com.example.movietunes.R;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class SearchByTitleController {

    Context appContext;

    public SearchByTitleController(Context appContext) {
        this.appContext = appContext;
    }

    private Soundtrack soundtrack;
    private Song song;


    public Soundtrack getSoundtrack() {
        return soundtrack;
    }

    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    public List<Song> lookupSoundtrack(String input) {




        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try

                {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.google.de")
                            .build();
                    response = client.newCall(request).execute();
                    String s = response.body().string();

                    System.err.println(s);


                    // parse

                    // data

                    // new intent


                } catch (
                        IOException e)

                {
                    e.printStackTrace();
                }
            }
        });
        return new ArrayList<>();

    }

    public List<Song> lookupSoundtrack0234(String input) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://www.android.com/");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuilder result = new StringBuilder();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                        result.append("\n");
                    }

                    System.out.println(result.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //request = new HttpGetHC4("http://www.google.de");
                //   client.execute(request);
                //  client.close();
            }
        });

        return null;
    }

    public List<Song> lookupSoundtrack_OLD(String input) {


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    URL spotifyEndpoint = new URL("https://api.spotify.com/v1/search?q=%22input&type=playlist");

                    HttpsURLConnection myConnection =
                            (HttpsURLConnection) spotifyEndpoint.openConnection();
                    Map<String, List<String>> requestProperties = myConnection.getRequestProperties();


                    myConnection.getResponseCode();
                    if (myConnection.getResponseCode() == 200) {
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);


                        // Success
                        // Further processing here
                    } else {
                        System.out.println("Funktioniert nicht");
                        // Error handling code goes here
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // All your networking logic
                // should be here
            }
        });

        //InputStream inputStream = getResources().openRawResource();

        //InputStream in = appContext.getResources().openRawResource(R.raw.songs);
        //InputStreamReader reader = new InputStreamReader(in);


        Gson gson = new GsonBuilder().create();
        //  List<Song> songs = Arrays.asList(gson.fromJson(reader, Song[].class));

        System.out.println("AAAAAAAAAAAAAAAA song read successfully");


        return null;


    }

    public Song getSoundtrackAsText() {
        return song;
    }

    public void setSoundtrackAsText(Song song) {
        this.song = song;
    }


}
