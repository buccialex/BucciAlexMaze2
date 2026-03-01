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
    private final Player player;
    private final Labirinto lab;
    private final DefaultTableModel model;
    private final GameGUI gui;
    private volatile boolean[] gameOver;

    /**
     * costruttore
     *
     * @param player player
     * @param lab labirinto
     * @param model tabella
     * @param gameOver variabile che è "in sintonia" con l'altro thread per
     * capire quando fermare i thread
     * @param gui finestra di gioco
     */
    public MuoviPlayer(Player player, Labirinto lab, DefaultTableModel model, boolean[] gameOver, GameGUI gui) {
        this.player = player;
        this.lab = lab;
        this.model = model;
        this.gameOver = gameOver;
        this.gui = gui;

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

                if (gameOver[0]) {
                    return;
                }
                SwingUtilities.invokeLater(() -> {
                    model.setValueAt(4, oldX, oldY);
                    model.setValueAt(3, player.getX(), player.getY());
                    gui.setLblPunti(String.valueOf(player.getPunti()));
                });

                try {
                    Thread.sleep(player.getVelocita());
                    if (gameOver[0]) {
                        return;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

        }

        if (!gameOver[0]) {
            gameOver[0] = true;
            int punteggio = player.getPunti();
            String nome = player.getNome();
            SwingUtilities.invokeLater(() -> {
                javax.swing.JOptionPane.showMessageDialog(
                        null,
                        "Complimenti " + player.getNome() + "! Hai completato il labirinto!\nPunteggio finale: " + punteggio,
                        "Hai vinto!",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
                gui.dispose();
                TitleGUI title = new TitleGUI();
                title.setVisible(true);
            });
        }
    }
}
