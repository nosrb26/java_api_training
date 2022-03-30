package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {
    @Test
    void test_ennemyId()
    {
        Game play = new Game();
        play.addEnnemy("TestPlayer");
        Assertions.assertThat(play.getEnnemyId()).isEqualTo("TestPlayer");
    }
    @Test
    void test_if_sink()
    {
        int temp[] = new int[2];
        for (int i = 0;i < 10; i++)
        {
            for (int y = 0; y< 10;y++)
            {
                temp[0] = i;
                temp[1] = i;
                Game play = new Game();
                play.Strike(temp);
            }
        }
    }
    @Test
    void test_shoot()
    {
        new Game().setShoot(0,0);
    }
}
