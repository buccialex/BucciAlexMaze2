/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexmaze;
import java.util.*;
/**
 *
 * @author Bux
 */
public class Mostro extends OggettoMobile{
    
    private static final int deviazioneProb = 75;
    
    public Mostro(String nome){
        super(nome);
    }
    
    @Override
    public void muovi(Labirinto labirinto){
        
        
    }
    
   

    public void muovi(Labirinto labirinto, Player player, int[] prev) {
        Random rnd = new Random();
        // prev[0] = prevX, prev[1] = prevY  ← array passato per riferimento
        int[][] maze = labirinto.getMappa();
        int n = maze.length;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        if (rnd.nextInt(100) < deviazioneProb) {
            muoviCasuale(maze, n, dx, dy, prev);
        } else {
            if (!muoviVersoPlayer(maze, n, dx, dy, player, prev)) {
                muoviCasuale(maze, n, dx, dy, prev);
            }
        }
    }

    private boolean muoviVersoPlayer(int[][] maze, int n, int[] dx, int[] dy, Player player, int[] prev) {
        int bestX = -1;
        int bestY = -1;
        double bestDist = Double.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX == prev[0] && nextY == prev[1]) continue; // salta precedente

            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
                    && maze[nextX][nextY] != 1) {
                double dist = distanza(nextX, nextY, player.getX(), player.getY());
                if (dist < bestDist) {
                    bestDist = dist;
                    bestX = nextX;
                    bestY = nextY;
                }
            }
        }

        if (bestX != -1) {
            prev[0] = x;  // ← aggiorna l'array prima di spostarsi
            prev[1] = y;
            x = bestX;
            y = bestY;
            return true;
        }

        prev[0] = -1;  // vicolo cieco, resetta
        prev[1] = -1;
        return false;
    }

    private void muoviCasuale(int[][] maze, int n, int[] dx, int[] dy, int[] prev) {
        Random rnd = new Random();
        int tentativi = 0;
        while (tentativi < 10) {
            int dir = rnd.nextInt(4);
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            if (nextX == prev[0] && nextY == prev[1]) {
                tentativi++;
                continue;
            }

            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
                    && maze[nextX][nextY] != 1) {
                prev[0] = x;
                prev[1] = y;
                x = nextX;
                y = nextY;
                return;
            }
            tentativi++;
        }

        prev[0] = -1;
        prev[1] = -1;
    }

    private double distanza(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    
}
