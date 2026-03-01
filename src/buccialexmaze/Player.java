/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexmaze;

/**
 *
 * @author Bux
 */
public class Player extends OggettoMobile {

    /**
     * attributi: velocita = velocità del player punti = punteggio
     */
    private int velocita = 120;
    private int punti;

    /**
     * costruttore di player
     *
     * @param nome nome del giocatore
     */
    public Player(String nome) {
        super(nome);

    }

    /**
     * Muove il player di una casella verso il numero 2 adiacente
     *
     * @param labirinto l'oggetto Labirinto su cui muoversi
     */
    public void muovi(Labirinto labirinto) {
        if (this.x == 0 && this.y == 0) {
            this.x = labirinto.getxEntrata();
            this.y = labirinto.getyEntrata();
        }

        int[][] maze = labirinto.getMappa();
        maze[x][y] = 4;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int n = maze.length;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX == n - 1 && maze[nextX][nextY] == 0) {
                this.aggiungiPunti(100);
                x = nextX;
                y = nextY;
                return;
            }

            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n
                    && (maze[nextX][nextY] == 2 || maze[nextX][nextY] == 8)) {

                if (maze[nextX][nextY] == 8) {
                    Mela m = new Mela();
                    int bonus = m.getBonus();
                    this.velocita = Math.max(20, this.velocita + bonus);
                    this.aggiungiPunti(250); // ← mela = +250
                } else {
                    this.aggiungiPunti(100); // ← movimento normale = +100
                }

                x = nextX;
                y = nextY;
                return;
            }
        }

    }

    /**
     * metodo per aggiungere i punti
     *
     * @param quantita numero dei punti
     */
    public void aggiungiPunti(int quantita) {
        this.punti += quantita;
    }

    /**
     * getter di velocita
     *
     * @return valore della velocita
     */
    public int getVelocita() {
        return velocita;
    }

    /**
     * setter di velocita
     *
     * @param velocita velocita da impostare
     */
    public void setVelocita(int velocita) {
        this.velocita = velocita;
    }

    /**
     * getter di punti
     *
     * @return punteggio
     */
    public int getPunti() {
        return punti;
    }

}
