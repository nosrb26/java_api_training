package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LauncherTest {
    @Test
    void test_main() {
        String temp1[] = new String[1];
        String temp2[] = new String[2];
        String temp3[] = new String[3];
        temp1[0] = "3310";
        temp2[0] = "3311";
        temp2[1] = "http://localhost:3310";
        new Launcher().main(temp1);
        new Launcher().main(temp2);
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Launcher().main(temp3));
        assertEquals("Error with arguments :", e.getMessage());
    }
}
