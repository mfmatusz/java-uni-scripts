package lab3.ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests use a programmatically-created 7×7 image.
 *
 * Legend:  W = white (passable),  B = black (wall)
 *
 *     0 1 2 3 4 5 6  (x)
 *  0  W W W W W W W
 *  1  W W W W W W W
 *  2  W W B B B W W
 *  3  W W B W B W W
 *  4  W W B B B W W
 *  5  W W W W W W W
 *  6  W W W W W W W
 * (y)
 *
 * The 3×3 black ring (x=2-4, y=2-4) encloses point (3,3) making it unreachable.
 */
class GamePathFinderTest {

    private static final int WHITE = Color.WHITE.getRGB();
    private static final int BLACK = Color.BLACK.getRGB();

    private GamePathFinder finder;

    @BeforeEach
    void setUp() {
        BufferedImage img = new BufferedImage(7, 7, BufferedImage.TYPE_INT_RGB);

        // Fill white
        for (int x = 0; x < 7; x++)
            for (int y = 0; y < 7; y++)
                img.setRGB(x, y, WHITE);

        // Draw black border ring at x=2-4, y=2-4
        for (int x = 2; x <= 4; x++) img.setRGB(x, 2, BLACK);
        for (int x = 2; x <= 4; x++) img.setRGB(x, 4, BLACK);
        img.setRGB(2, 3, BLACK);
        img.setRGB(4, 3, BLACK);
        // centre (3,3) is white but surrounded by walls

        finder = new GamePathFinder(img);
    }

    @Test
    void pathExistsBetweenTwoOpenPoints() {
        assertTrue(finder.hasPath(0, 0, 6, 6));
    }

    @Test
    void startAndEndSamePointAlwaysHasPath() {
        assertTrue(finder.hasPath(1, 1, 1, 1));
    }

    @Test
    void enclosedPointIsUnreachable() {
        assertFalse(finder.hasPath(0, 0, 3, 3));
    }

    @Test
    void startOnWallHasNoPath() {
        assertFalse(finder.hasPath(2, 2, 6, 6)); // (2,2) is a wall
    }

    @Test
    void outOfBoundsHasNoPath() {
        assertFalse(finder.hasPath(-1, 0, 6, 6));
        assertFalse(finder.hasPath(0, 0, 99, 99));
    }

    @Test
    void getPathReturnsNonNullAndStartsAtSource() {
        List<Point> path = finder.getPath(0, 0, 6, 6);
        assertNotNull(path);
        assertEquals(new Point(0, 0), path.get(0));
        assertEquals(new Point(6, 6), path.get(path.size() - 1));
    }

    @Test
    void singlePointPathHasLengthOne() {
        List<Point> path = finder.getPath(1, 1, 1, 1);
        assertNotNull(path);
        assertEquals(1, path.size());
    }
}
