package org.dhbw.se.movietunes.http;

import okhttp3.Request;
import okhttp3.Response;

public class HttpCommunication {
  public static Response executeRequest(Request request) {
    HttpAsyncTask httpTask = new HttpAsyncTask();
    httpTask.execute(request);
    Response response;

    try {
      response = httpTask.get();

    } catch (Exception e) {
      throw new HttpException("Sync request failed!", e);
    }

    return response;
  }

}