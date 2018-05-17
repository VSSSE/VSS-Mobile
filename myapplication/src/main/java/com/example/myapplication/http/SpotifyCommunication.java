package com.example.myapplication.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SpotifyCommunication {

    private static final String HEADER_AUTHORIZATION = "Basic M2ZkOWFjYmJjZmY4NDVlNThhZTAxOGUwYTYwZjliMzU6ZTllODJjNmZjMDI0NGFiYWI1NWRiMTRjNGEzNjFhNzc=";

    public static String fetchToken() {
        MediaType mediaType = MediaType.parse("x-www-form-urlencoded");


        RequestBody formBody = new FormBody.Builder().add("grant_type", "client_credentials").build();

        // RequestBody body = RequestBody.create( null, "grant_type:client_credentials");
        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .addHeader("Authorization", HEADER_AUTHORIZATION)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        String responseBody = null;

        Response response = HttpCommunication.executeRequest(request);
        try {
            responseBody = response.body().string();
        } catch (Exception e) {
            throw new RuntimeException("token req to spotify failed ", e);
        }

        JsonElement root = new JsonParser().parse(responseBody);
        String token = root.getAsJsonObject().get("access_token").getAsString();
        return token;

    }

}
