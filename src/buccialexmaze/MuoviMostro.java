/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexmaze;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bux
 */
public class MuoviMostro implements Runnable {

    /**
     * attributi: mostro = oggetto Mostro player = oggetto Player lab =
     * labirinto model = tabella (neccessaria come attributo per far si che
     * possa vedere l'aggiornamento della tabella) ritardoIniziale = valore di
     * ritardo di partenza rispetto al player
     */
    private final Mostro mostro;
    private final Player player;
    private final Labirinto lab;
    private final DefaultTableModel model;
    private final int ritardoIniziale;
    private volatile boolean[] gameOver;
    private final int ritardo;

    /**
     * costruttore
     *
     * @param mostro mostro
     * @param player player
     * @param lab labirinto
     * @param model tabella
     * @param ritardoIniziale ritardo di partenza
     * @param gameOver se il mostro ha preso il player
     * @param ritardo valore di ritardo per la partenza
     */
    public MuoviMostro(Mostro mostro, Player player, Labirinto lab, DefaultTableModel model, int ritardoIniziale, boolean[] gameOver, int ritardo) {
        this.mostro = mostro;
        this.player = player;
        this.lab = lab;
        this.model = model;
        this.ritardoIniziale = ritardoIniziale;
        this.gameOver = gameOver;
        this.ritardo = ritardo;
    }

    /**
     * override del metodo run del thread
     */
    @Override
    public void run() {
        try {
            Thread.sleep(ritardoIniziale);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        int n = lab.getMappa().length;
        int[] prev = {-1, -1};

        while (mostro.getX() != n - 1 && !gameOver[0]) {
            int oldX = mostro.getX();
            int oldY = mostro.getY();

            mostro.muovi(lab, player, prev);  // ← passa l'array

            // controlla se il mostro ha raggiunto il player
            if (mostro.getX() == player.getX() && mostro.getY() == player.getY()) {
                gameOver[0] = true;
                SwingUtilities.invokeLater(() -> {
                    model.setValueAt(5, mostro.getX(), mostro.getY());
                    javax.swing.JOptionPane.showMessageDialog(
                            null,
                            "Sei stato catturato dal mostro!\nPunteggio finale: " + player.getPunti(),
                            "Game Over",
                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );
                });
                return;
            }

            SwingUtilities.invokeLater(() -> {
                model.setValueAt(0, oldX, oldY);
                model.setValueAt(5, mostro.getX(), mostro.getY());
            });

            try {
                Thread.sleep(ritardo);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
