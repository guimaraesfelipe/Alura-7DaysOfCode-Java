package br.com.daysofcode.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectionApi {

    public static String request(URI path) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(path).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

}
