package lab3.ex2;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Lab 3 / Exercise 2 — Game Map Path Finder
 *
 * Finds a path between two points on a BMP image using DFS.
 * Black pixels (0xFF000000) are treated as walls; all other pixels are passable.
 *
 * Usage: java lab3.ex2.GamePathFinder [image-path] [startX] [startY] [endX] [endY]
 */
public class GamePathFinder {

    private static final int BLACK = 0xFF000000;
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private final BufferedImage image;
    private boolean[][] visited;

    public GamePathFinder(BufferedImage image) {
        this.image = image;
        this.visited = new boolean[image.getWidth()][image.getHeight()];
    }

    public GamePathFinder(String imagePath) throws IOException {
        this(ImageIO.read(new File(imagePath)));
    }

    /** Returns true if a passable path exists between the two points. */
    public boolean hasPath(int startX, int startY, int endX, int endY) {
        if (!inBounds(startX, startY) || !inBounds(endX, endY)) return false;
        if (isWall(startX, startY) || isWall(endX, endY)) return false;
        visited = new boolean[image.getWidth()][image.getHeight()];
        return dfs(startX, startY, endX, endY) != null;
    }

    /**
     * Returns the path from start to end, or null if no path exists.
     * Call {@link #hasPath} first to check feasibility.
     */
    public List<Point> getPath(int startX, int startY, int endX, int endY) {
        visited = new boolean[image.getWidth()][image.getHeight()];
        return dfs(startX, startY, endX, endY);
    }

    private List<Point> dfs(int x, int y, int endX, int endY) {
        if (x == endX && y == endY) {
            List<Point> path = new ArrayList<>();
            path.add(new Point(x, y));
            return path;
        }
        visited[x][y] = true;
        for (int[] dir : DIRECTIONS) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (inBounds(nx, ny) && !visited[nx][ny] && !isWall(nx, ny)) {
                List<Point> path = dfs(nx, ny, endX, endY);
                if (path != null) {
                    path.add(0, new Point(x, y));
                    return path;
                }
            }
        }
        return null;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight();
    }

    private boolean isWall(int x, int y) {
        return image.getRGB(x, y) == BLACK;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 5) {
            System.out.println("Usage: GamePathFinder <image> <startX> <startY> <endX> <endY>");
            return;
        }
        GamePathFinder finder = new GamePathFinder(args[0]);
        int sx = Integer.parseInt(args[1]), sy = Integer.parseInt(args[2]);
        int ex = Integer.parseInt(args[3]), ey = Integer.parseInt(args[4]);

        if (finder.hasPath(sx, sy, ex, ey)) {
            System.out.println("Path found:");
            for (Point p : finder.getPath(sx, sy, ex, ey)) {
                System.out.printf("(%d, %d)%n", p.x, p.y);
            }
        } else {
            System.out.println("No path exists between the given points.");
        }
    }
}
