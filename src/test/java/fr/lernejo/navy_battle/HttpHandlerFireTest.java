package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpHandlerFireTest {
    @Test
    void test_handlefire_bad_method() {
        ServeurHTTP s1 = new ServeurHTTP(4678);
        HttpClient tempClient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:4678/api/game/fire?cell=B2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{ url\":\"http://localhost:9876\" , \"message\":\"Hello\"}")).build();
        try {
            HttpResponse<String> temp = tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(temp.statusCode()).isEqualTo(404);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handlefire_bad_query()
    {
        ServeurHTTP s1 = new ServeurHTTP(1234);
        HttpClient tempClient = HttpClient.newHttpClient();
        HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:1234/api/game/fire?cell=B6121541032")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        try {
            HttpResponse<String> temp = tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(temp.statusCode()).isEqualTo(400);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handlefire_good_test_odin() {
        try {
            ServeurHTTP s1 = new ServeurHTTP(0001);
            HttpClient tempClient = HttpClient.newHttpClient();
            HttpResponse<String> post = null;
            HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=A10")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=B2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=C2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=D2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(post.statusCode()).isEqualTo(202);
        } catch (Exception e) { e.printStackTrace();}
    }
    @Test
    void test_handle_fire_good_test_dva() {
        try{
            HttpClient tempClient = HttpClient.newHttpClient();
            HttpResponse<String> post = null;
            HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=E2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=F2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=G2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=H2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(post.statusCode()).isEqualTo(202);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void test_handle_fire_good_test_tri() {
        try{
            HttpClient tempClient = HttpClient.newHttpClient();
            HttpResponse<String> post = null;
            HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=I2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            requetePost = HttpRequest.newBuilder().uri(URI.create("http://localhost:0001/api/game/fire?cell=J2")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").build();
            tempClient.send(requetePost, HttpResponse.BodyHandlers.ofString());
            Assertions.assertThat(post.statusCode()).isEqualTo(202);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
