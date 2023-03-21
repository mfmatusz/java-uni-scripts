package lab3.ex2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class GamePathFinder {

    private BufferedImage image;
    private boolean[][] visited;
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 4 kierunki przeszukiwania grafu

    public GamePathFinder(String imagePath) {
        try {
            File file = new File(imagePath);
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        visited = new boolean[image.getWidth()][image.getHeight()];
    }

    public boolean hasPath(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= image.getWidth() || startY < 0 || startY >= image.getHeight() ||
                endX < 0 || endX >= image.getWidth() || endY < 0 || endY >= image.getHeight()) {
            return false; // obsługa błędnych punktów początkowych i końcowych
        }
        if (image.getRGB(startX, startY) == 0xFF000000 || image.getRGB(endX, endY) == 0xFF000000) {
            return false; // obsługa czarnych pól - przeszkód
        }
        return dfs(startX, startY, endX, endY) != null;
    }

    public boolean wrongStart(int startX, int startY){
        if (image.getRGB(startX, startY) == 0xFF000000){
            return true;
        }
        return false;
    }
    public boolean wrongEnd(int endX, int endY){
        if (image.getRGB(endX, endY) == 0xFF000000){
            return true;
        }
        return false;
    }

    private ArrayList<Point> dfs(int x, int y, int endX, int endY) {
        if (x == endX && y == endY) {
            ArrayList<Point> path = new ArrayList<>();
            path.add(new Point(x, y));
            return path; // znaleziono ścieżkę
        }
        visited[x][y] = true;
        for (int[] dir : directions) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            if (nextX >= 0 && nextX < image.getWidth() && nextY >= 0 && nextY < image.getHeight() &&
                    !visited[nextX][nextY] && image.getRGB(nextX, nextY) != 0xFF000000) {
                ArrayList<Point> path = dfs(nextX, nextY, endX, endY);
                if (path != null) {
                    path.add(0, new Point(x, y)); // dodaj punkt do ścieżki
                    return path;
                }
            }
        }
        return null; // nie znaleziono ścieżki
    }

    public ArrayList<Point> getPath(int startX, int startY, int endX, int endY) {
        visited = new boolean[image.getWidth()][image.getHeight()];
        return dfs(startX, startY, endX, endY);
    }

    public static void main(String[] args) {
        GamePathFinder gamePathFinder = new GamePathFinder("C:\\Users\\Maciek\\IdeaProjects\\apro1_23l_matuszewski_maciej\\laboratories\\lab3\\ex2\\Map.bmp");
        int startX = 1;
        int startY = 1;
        int endX = 6;
        int endY = 6;
        if (gamePathFinder.hasPath(startX, startY, endX, endY)) {
            System.out.println("Ścieżka istnieje:");
            ArrayList<Point> path = gamePathFinder.getPath(startX, startY, endX, endY);
            for (Point p : path) {
                System.out.println("(" + p.x + ", " + p.y + ")");
            }
        } else if (gamePathFinder.wrongStart(startX, startY) == true) {
            System.out.println("Błędny punkt początkowy.");
        } else if (gamePathFinder.wrongEnd(endX, endY) == true) {
            System.out.println("Błędny punkt końcowy.");
        }
        else {
            System.out.println("Ścieżka nie istnieje.");
        }
    }

}

