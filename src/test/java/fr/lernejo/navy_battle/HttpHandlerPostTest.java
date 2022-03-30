package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpHandlerPostTest {
    @Test
    void test_handlepost_bad_json() {
        ServeurHTTP s1 = new ServeurHTTP(0002);
        HttpClient tempClient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{ }")).build();
        try {
            HttpResponse<String> post = tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(post.statusCode()).isEqualTo(400);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void test_handlepost_bad_methods() {
        ServeurHTTP s1 = new ServeurHTTP(9876);
        HttpClient tempClient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:9876/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        try {
            HttpResponse<String> post = tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(post.statusCode()).isEqualTo(404);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
