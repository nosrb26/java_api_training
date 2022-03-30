package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameBoatTest {
    @Test
    void test_bad_preset()
    {
        Assertions.assertThat(new GameBoat(6).getBoats()).isEqualTo(new GameBoat(6).getBoats());
    }
    @Test
    void test_first_preset()
    {
        Assertions.assertThat(new GameBoat(1).getBoats()).isEqualTo(new GameBoat(1).getBoats());
    }
    @Test
    void test_second_preset()
    {
        Assertions.assertThat(new GameBoat(2).getBoats()).isEqualTo(new GameBoat(2).getBoats());
    }
    @Test
    void test_third_preset()
    {
        Assertions.assertThat(new GameBoat(3).getBoats()).isEqualTo(new GameBoat(3).getBoats());
    }
}
