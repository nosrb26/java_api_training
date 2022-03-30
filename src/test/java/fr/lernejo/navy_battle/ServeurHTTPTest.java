package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

class ServeurHTTPTest {
    @Test
    void test_start_game() {
        ServeurHTTP serv1 = new ServeurHTTP(89);
        serv1.start_game("313djeijdiediz");
    }
    @Test
    void addEnnemy() {
    }
    @Test
    void test_port_get_test() {
        Assertions.assertThat(new ServeurHTTP(9860).getPort()).isEqualTo(9860);
    }
    @Test
    void send_fire() {
    }
    @Test
    void test_endGame() throws Exception {}
    @Test
    void addURL() {
    }
}
