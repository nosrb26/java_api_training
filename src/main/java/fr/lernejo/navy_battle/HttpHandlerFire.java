package fr.lernejo.navy_battle;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class HttpHandlerFire implements HttpHandler {
    private final ServeurHTTP server;
    public HttpHandlerFire(ServeurHTTP server) {
        this.server = server;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equals("GET")) {
            String query = exchange.getRequestURI().getQuery().split("=")[1];
            if (checkQuery(query.toLowerCase())) {
                Object result_object[] = this.server.getPlayer().Strike(convertQuery(query));
                JsonObject js = new JsonObject();
                js.addProperty("consequence",(String)result_object[0]);
                js.addProperty("shipLeft",(boolean)result_object[1]);
                send_message(exchange,js.toString(),202);
                checkEnd((boolean)result_object[1]);
            }
            else
                send_message(exchange, "Bad Request", 400);
        }
        send_message(exchange,"Not Found",404);
    }

    private void checkEnd(boolean boolass) {
        if (!boolass) {
            this.server.endGame(false);
        }
        else{
            this.server.send_fire();
        }
    }

    private void send_message(HttpExchange exchange,String message,int server_code) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Accept", "application/json");
        exchange.sendResponseHeaders(server_code, message.length());
        try (OutputStream os = exchange.getResponseBody()) { // (1)
            os.write(message.getBytes());
        }
    }
    private boolean checkQuery(String query) {
        if (query.length() == 2 | query.length() == 3) {
            char check[] = query.toCharArray();
            if (check[0] >= 'a' && check[0] <= 'j') {
                if (query.length()==2)
                    return check[1] >= '1' && check[1] <= '9';
                else
                    return check[1] == '1' && check[2] == '0';
            }
        }
        return false;
    }
    public int[] convertQuery(String str) {
        int tab[] = new int[2];
        char check[] = str.toLowerCase().toCharArray();
        tab[0] = table_converter(check[0]);
        if (str.length()==2)
            tab[1] = Integer.parseInt(String.valueOf(check[1])) - 1;
        else
            tab[1] = 9;
        return tab;
    }
    public int table_converter(char str) {
        switch(str) {
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            case 'i': return 8;
            default: return 9;
        }
    }
}
