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
    
    /**
     * attributi:
     * deviazioneProb: la probabilità che il mostro si inizi a muovere randomicamente
     */
    private final int deviazioneProb = 30;
    
    /**
     * costruttore di mostro
     * @param nome nome del mostro
     */
    public Mostro(String nome){
        super(nome);
    }
    
    /**
     * metodo muovi da implementare per rendere possibile l'ereditarietà(lasciato vuoto dato che il mostro usa un algoritmo di movimento che richiede più parametri)
     * @param labirinto labirinto
     */
    @Override
    public void muovi(Labirinto labirinto){
        
        
    }
    
   
    /**
     * metodo muovi del mostro
     * @param labirinto labirinto
     * @param player giocatore (neccessario per far si che il mostro segua il player)
     * @param prev coordinate di provenienza del mostro
     */
    public void muovi(Labirinto labirinto, Player player, int[] prev) {
        Random rnd = new Random();
        int[][] maze = labirinto.getMappa();
        int n = maze.length;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        if (rnd.nextInt(100) < deviazioneProb) {
            muoviCasuale(maze, n, dx, dy, prev);
        } else {
            boolean[][] visitato = new boolean[n][n];
            List<int[]> percorso = new ArrayList<>();

            // cerca ricorsivamente il percorso verso il player
            cercaPercorso(maze, x, y, player.getX(), player.getY(), visitato, percorso);

            if (percorso.size() > 1) {
                prev[0] = x;
                prev[1] = y;
                // il primo elemento è la posizione attuale, il secondo è il prossimo passo
                x = percorso.get(1)[0];
                y = percorso.get(1)[1];
            } else {
                muoviCasuale(maze, n, dx, dy, prev);
            }
        }
    }

    /**
     * metodo per muoversi verso il player
     * @param maze labirinto
     * @param n misura del labirinto
     * @param dx possibili x dove muoversi
     * @param dy possibili y dove muoversi
     * @param player giocatore
     * @param prev coordinate della cella di provenienza
     * @return  se il mostro si può muovere verso il player
     */
    
    private boolean cercaPercorso(int[][] maze, int cx, int cy, int endX, int endY,
                                   boolean[][] visitato, List<int[]> percorso) {
        int n = maze.length;

        // caso base: obiettivo raggiunto
        if (cx == endX && cy == endY) {
            percorso.add(new int[]{cx, cy});
            return true;
        }

        visitato[cx][cy] = true;
        percorso.add(new int[]{cx, cy});

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n
                    && maze[nx][ny] != 1 && !visitato[nx][ny]) {
                if (cercaPercorso(maze, nx, ny, endX, endY, visitato, percorso)) {
                    return true;
                }
            }
        }

        // vicolo cieco: rimuovi questa cella dal percorso (backtracking)
        percorso.remove(percorso.size() - 1);
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
    
    
}
