package fr.lernejo.navy_battle;

import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import static java.lang.System.exit;

public class ServeurHTTP {
    private final int port;
    private final Game player = new Game();
    private final ArrayList<String> url = new ArrayList<>(1);

    public ServeurHTTP(int port) {
        this.port = port;
        try {
            HttpServer currentServer = HttpServer.create(new InetSocketAddress("localhost", this.port), 0);
            currentServer.setExecutor(Executors.newSingleThreadExecutor());
            currentServer.createContext("/ping", new HttpPingHandler());
            currentServer.createContext("/api/game/start", new HttpHandlerPost(this));
            currentServer.createContext("/api/game/fire", new HttpHandlerFire(this));
            currentServer.start();
        } catch (IOException e) {e.printStackTrace();}
    }

    public void start_game(String url) {
        HttpClient current = HttpClient.newHttpClient();
        try {
            HttpRequest requetePost = HttpRequest.newBuilder().uri(URI.create(url + "/api/game/start")).setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"" + this.player.getId() + "\", \"url\":\"http://localhost:" + port + "\", \"message\":\"Hello\"}")).build();
            HttpResponse<String> post = current.send(requetePost, HttpResponse.BodyHandlers.ofString());
            if (post.statusCode() == 202) {
                this.addURL(url);
                this.addPlayerEnnemy(JsonParser.parseString(post.body()).getAsJsonObject().get("id").toString());
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void send_fire() {
        Object[] shoot = this.player.chooseCase();
        HttpClient tempClient = HttpClient.newHttpClient();
        HttpRequest requeteFire = HttpRequest.newBuilder().uri(URI.create(this.url.get(0).replace("\"", "") + "/api/game/fire?cell=" + shoot[2])).header("Accept", "application/json").build();
        try {
            HttpResponse<String> post = tempClient.send(requeteFire, HttpResponse.BodyHandlers.ofString());
            if (post.statusCode() == 202) {
                if (JsonParser.parseString(post.body()).getAsJsonObject().get("consequence").toString().equals("hit") || JsonParser.parseString(post.body()).getAsJsonObject().get("consequence").toString().equals("sunk")) {
                    this.player.setShoot((int) shoot[0], (int) shoot[1]);
                    if (JsonParser.parseString(post.body()).getAsJsonObject().get("shipLeft").toString().equals("false")) {
                        endGame(true);
                    }
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void endGame(boolean status) {
        if (status)
            System.out.println("I won against \"" + this.player.getEnnemyId() + "\". GG !");
        else
            System.out.println("I lost against \"" + this.player.getEnnemyId() + "\". Good fight");
        exit(0);
    }

    public void addURL(String url) {
        this.url.add(url);
    }
    public void addPlayerEnnemy(String id) {
        this.player.addEnnemy(id);
    }
    public int getPort() {
        return this.port;
    }
    public String getId() {
        return this.player.getId();
    }
    public Game getPlayer() {
        return this.player;
    }
}
