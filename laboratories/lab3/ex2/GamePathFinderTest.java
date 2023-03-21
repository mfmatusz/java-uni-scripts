package lab3.ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GamePathFinderTest {

    private GamePathFinder gamePathFinder;

    @BeforeEach
    void setUp() {
        gamePathFinder = new GamePathFinder("C:\\\\Users\\\\Maciek\\\\IdeaProjects\\\\apro1_23l_matuszewski_maciej\\\\laboratories\\\\lab3\\\\ex2\\\\Map.bmp");
    }

    @Test
    void testHasPath() {
        assertFalse(gamePathFinder.hasPath(1, 1, 43, 63)); // błędna ścieżka
        assertTrue(gamePathFinder.hasPath(1, 1, 5, 5)); // istnieje
    }

    @Test
    void testGetPath() {
        ArrayList<Point> path = gamePathFinder.getPath(1, 1, 1, 1);
        assertNotNull(path); // ścieżka nie jest nullem
        assertEquals(new Point(1, 1), path.get(0)); // pierwszy punkt na ścieżce
        assertEquals(new Point(1, 1), path.get(path.size() - 1)); // ostatni punkt na ścieżce
    }

    @Test
    void testWrongStart() {
        assertTrue(gamePathFinder.wrongStart(43, 63)); // punkt początkowy na przeszkodzie
        assertFalse(gamePathFinder.wrongStart(1, 1)); // poprawny punkt początkowy
    }

    @Test
    void testWrongEnd() {
        assertFalse(gamePathFinder.wrongEnd(1, 1)); // punkt końcowy na przeszkodzie
        assertTrue(gamePathFinder.wrongEnd(43, 63)); // poprawny punkt końcowy
    }

}

