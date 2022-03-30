package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpPingHandlerTest {
    @Test
    void Test_pinghandler_good() {
        ServeurHTTP s1 = new ServeurHTTP(9876);
        HttpClient tempClient = HttpClient.newHttpClient();
        HttpRequest requetePing = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/ping")).header("Accept","application/json").build();
        try {
            HttpResponse<String> post = tempClient.send(requetePing, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(post.statusCode()).isEqualTo(200);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
