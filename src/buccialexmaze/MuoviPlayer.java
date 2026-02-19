/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccialexmaze;

import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bux
 */
public class MuoviPlayer implements Runnable {

    /**
     * attributi: player = player lab = labirinto model = tabella btnMuovi =
     * bottone che avvia il movimento
     */
    private Player player;
    private Labirinto lab;
    private DefaultTableModel model;
    private JButton btnMuovi;
    private volatile boolean[] gameOver;

    /**
     * costruttore
     *
     * @param player player
     * @param lab labirinto
     * @param model tabella
     * @param btnMuovi bottone che avvia l'esecuzione
     */
    public MuoviPlayer(Player player, Labirinto lab, DefaultTableModel model, JButton btnMuovi, boolean[] gameOver) {
        this.player = player;
        this.lab = lab;
        this.model = model;
        this.btnMuovi = btnMuovi;
        this.gameOver = gameOver;

    }

    /**
     * override del metodo run che consente di eseguire le istruzioni con il
     * thread
     */
    @Override
    public void run() {

        int n = lab.getMappa().length;
        while (player.getX() != n - 1 && !gameOver[0]) {
            while (player.getX() != n - 1) {
                int oldX = player.getX();
                int oldY = player.getY();

                player.muovi(lab);

                if (gameOver[0]) return;
                SwingUtilities.invokeLater(() -> {
                    model.setValueAt(4, oldX, oldY);
                    model.setValueAt(3, player.getX(), player.getY());
                });

                try {
                    Thread.sleep(player.getVelocita());
                    if (gameOver[0]) return;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            SwingUtilities.invokeLater(() -> btnMuovi.setEnabled(false));
        }
    }
}
