package fr.lernejo.navy_battle;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class HttpHandlerPost implements HttpHandler {
    private final ServeurHTTP server;
    public HttpHandlerPost(ServeurHTTP srv)
    {
        this.server = srv;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("POST")){
            JSONObject response = null;
            try {
                response = (JSONObject) new JSONParser().parse(new InputStreamReader(exchange.getRequestBody(),"UTF-8"));
                JsonObject js = JsonParser.parseString(response.toJSONString()).getAsJsonObject();
                if (verify(js)) {
                    start_game_battle(js,exchange);
                }
                else { send_message(exchange, "Bad Request", 400); }
            } catch (ParseException e) { send_message(exchange, "Bad Request", 400); } }
        else { send_message(exchange, "Not found", 404); }
    }

    private void start_game_battle(JsonObject js, HttpExchange exchange) throws IOException {
        this.server.addURL(js.get("url").toString());
        this.server.addPlayerEnnemy(js.get("id").toString());
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Accept", "application/json");
        send_message(exchange,valid_message(),202);
        this.server.send_fire();
    }

    private String valid_message()
    {
        JsonObject result = new JsonObject();
        result.addProperty("id",this.server.getId());
        result.addProperty("url","http://localhost:"+this.server.getPort());
        result.addProperty("message","Let's fight");
        return result.toString();
    }
    private void send_message(HttpExchange exchange,String message,int server_code) throws IOException {
        exchange.sendResponseHeaders(server_code, message.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(message.getBytes());
        }
    }
    private boolean verify(JsonObject json)
    {
        return (json.has("id") && json.has("url") && json.has("message"));
    }
}
