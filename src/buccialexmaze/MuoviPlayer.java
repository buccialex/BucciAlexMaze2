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
public class MuoviPlayer implements Runnable{
    private Player player;
    private Labirinto lab;
    private DefaultTableModel model;
    private JButton btnMuovi;
    
    public MuoviPlayer(Player player, Labirinto lab, DefaultTableModel model, JButton btnMuovi) {
        this.player = player;
        this.lab = lab;
        this.model = model;
        this.btnMuovi = btnMuovi;
    }
    
    @Override
    public void run() {
        int n = lab.getMappa().length;

        while (player.getX() != n - 1) {
            int oldX = player.getX();
            int oldY = player.getY();

            player.muovi(lab);

            SwingUtilities.invokeLater(() -> {
                model.setValueAt(4, oldX, oldY);
                model.setValueAt(3, player.getX(), player.getY());
            });

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        SwingUtilities.invokeLater(() -> btnMuovi.setEnabled(false));
    }
}
