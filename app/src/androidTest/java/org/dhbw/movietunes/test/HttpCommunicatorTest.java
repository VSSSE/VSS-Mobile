package org.dhbw.movietunes.test;

import android.support.test.runner.AndroidJUnit4;
import java.io.IOException;
import okhttp3.Request;
import org.dhbw.movietunes.http.HttpCommunication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HttpCommunicatorTest {

  @Test
  public void testExecuteRequest() throws IOException {
    Request request = new Request.Builder()
            .url("http://www.google.de")
            .build();

    String response = HttpCommunication.executeRequest(request);

    Assert.assertTrue(response.contains("http://www.google.de/"));

  }

}
