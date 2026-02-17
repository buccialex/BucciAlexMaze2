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
public class Labirinto {
    /**
     * attributi:
     * misure del labirinto (uso solo una variabile dato che sarà quadrato)
     * coordinata x dell'entrata
     * coordinata y dell'entrata
     * mappa = matrice del labirinto
     */
    private int misure;
    private int xEntrata;
    private int yEntrata;
    private int[][] mappa;
    /**
     * costruttore di labirinto
     */
    public Labirinto(){
        this.misure = 30;
        // costruisco il labirinto nel costruttore
        this.mappa = this.popolaLabirinto();
    }
    
    /**
     * metodo interno per creare il labirinto
     * @return il labirinto
     */
    
    private int[][] popolaLabirinto(){
        int n = misure;
        if (n % 2 == 0) n++;
        
        this.mappa = new int[n][n];
        
        // Inizializza tutto con muri (1)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.mappa[i][j] = 1;
            }
        }
        
        // Inizializza l'interno con spazi aperti (0)
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                this.mappa[i][j] = 0;
            }
        }
        
        // Aggiungi muri con divisione ricorsiva solo all'interno
        dividiSpazio(this.mappa, 1, 1, n - 2, n - 2);
        
        // Crea solo 2 aperture nel contorno: ingresso (alto) e uscita (basso)
        int ingresso_col = n / 2;
        int uscita_col = n / 2;
        this.mappa[0][ingresso_col] = 0; // Ingresso al centro superiore
        this.mappa[n - 1][uscita_col] = 0; // Uscita al centro inferiore
        
        // Trova il percorso soluzione con ricorsione
        boolean[][] visitato = new boolean[n][n];
        int[][] parent_x = new int[n][n];
        int[][] parent_y = new int[n][n];
        
        int start_x = 1, start_y = ingresso_col;
        this.xEntrata = start_x;
        this.yEntrata = start_y;
        int end_x = n - 2, end_y = uscita_col;
        
        // Ricerca ricorsiva
        cercaPercorso(this.mappa, start_x, start_y, end_x, end_y, visitato, parent_x, parent_y);
        
        // Ricostruisci e marca il percorso con 2
        if (visitato[end_x][end_y]) {
            int x = end_x, y = end_y;
            while (!(x == start_x && y == start_y)) {
                this.mappa[x][y] = 2;
                int temp_x = x;
                x = parent_x[temp_x][y];
                y = parent_y[temp_x][y];
            }
            this.mappa[start_x][start_y] = 2; // Marca anche il punto di partenza
        }
        

        return this.mappa;
    }
    /**
     * metodo per trovare il percorso corretto
     * @param  labirinto generato
     * @param x coordinata x
     * @param y coordinata y
     * @param endX coordinata che segna la fine delle x
     * @param endY coordinata che segna la fine delle y
     * @param visitato variabile che controlla se sono gia passato nella cella
     * @param parent_x x di provenienza
     * @param parent_y y di provenienza
     * @return se la strada presa è quella corretta
     */
    
    private boolean cercaPercorso(int[][] maze, int x, int y, int endX, int endY, 
                                   boolean[][] visitato, int[][] parent_x, int[][] parent_y) {
        int n = maze.length;
        
        // Caso base: raggiunto l'obiettivo
        if (x == endX && y == endY) {
            visitato[x][y] = true;
            return true;
        }
        
        // Marca come visitato
        visitato[x][y] = true;
        
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        // Prova tutti i vicini ricorsivamente
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && 
                !visitato[nx][ny] && maze[nx][ny] != 1) {
                parent_x[nx][ny] = x;
                parent_y[nx][ny] = y;
                
                if (cercaPercorso(maze, nx, ny, endX, endY, visitato, parent_x, parent_y)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * metodo interno per dividere il labirinto
     * @param maze labirinto di base (con entrata/uscita e bordo)
     * @param top posizione del punto più alto
     * @param left posizione del punto più a sinistra
     * @param bottom posizione del punto più basso
     * @param right posizione del punto più a destra
     */
    
    private void dividiSpazio(int[][] maze, int top, int left, int bottom, int right) {
        Random rnd = new Random();
        
        if (bottom - top < 2 || right - left < 2) return;
        
        boolean tagliaOrizz = rnd.nextBoolean();
        
        if (tagliaOrizz) {
            // Taglia orizzontalmente
            int riga = top + 1 + rnd.nextInt((bottom - top) / 2) * 2;
            for (int j = left; j <= right; j++) {
                maze[riga][j] = 1;
            }
            // Crea apertura casuale
            int col = left + rnd.nextInt((right - left) / 2 + 1) * 2;
            maze[riga][col] = 0;
            
            dividiSpazio(maze, top, left, riga - 1, right);
            dividiSpazio(maze, riga + 1, left, bottom, right);
        } else {
            // Taglia verticalmente
            int col = left + 1 + rnd.nextInt((right - left) / 2) * 2;
            for (int i = top; i <= bottom; i++) {
                maze[i][col] = 1;
            }
            // Crea apertura casuale
            int riga = top + rnd.nextInt((bottom - top) / 2 + 1) * 2;
            maze[riga][col] = 0;
            
            dividiSpazio(maze, top, left, bottom, col - 1);
            dividiSpazio(maze, top, col + 1, bottom, right);
        }
    }

    /**
     * getter di misure
     * @return la misura del labirinto
     */
    public int getMisure() {
        return misure;
    }

    /**
     * setter delle misure
     * @param misure misure aggiornate
     */
    public void setMisure(int misure) {
        this.misure = misure;
    }

    /**
     * getter x entrata
     * @return la x dell'entrata
     */
    public int getxEntrata() {
        return xEntrata;
    }

    /**
     * getter y entrata
     * @return y dell'entrata
     */
    public int getyEntrata() {
        return yEntrata;
    }

    /**
     * getter della mappa
     * @return la mappa
     */
    public int[][] getMappa() {
        return mappa;
    }
    
    
  

    
    
    
}

