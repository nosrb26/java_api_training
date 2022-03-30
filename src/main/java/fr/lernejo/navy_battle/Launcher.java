package fr.lernejo.navy_battle;

public class Launcher {
    public static void main(String[] args) {
        if (args.length == 1)
        {
            ServeurHTTP server =  new ServeurHTTP(Integer.parseInt(args[0]));
        }
        else if (args.length == 2)
        {
            ServeurHTTP server = new ServeurHTTP(Integer.parseInt(args[0]));
            server.start_game(args[1]);
        }
        else throw new IllegalArgumentException("Error with arguments :");
    }
}
