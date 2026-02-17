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
public class MuoviMostro implements Runnable{
    private Mostro mostro;
    private Player player;  
    private Labirinto lab;
    private DefaultTableModel model;
    private int ritardoIniziale;
    
    public MuoviMostro(Mostro mostro, Player player, Labirinto lab, DefaultTableModel model, int ritardoIniziale) {
        this.mostro = mostro;
        this.player = player;  
        this.lab = lab;
        this.model = model;
        this.ritardoIniziale = ritardoIniziale;
    }
    
    @Override
        public void run() {
            try {
                Thread.sleep(ritardoIniziale);
             } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
             return;
            }

        int n = lab.getMappa().length;
        int[] prev = {-1, -1};  // ← stato locale al thread, non all'oggetto

        while (mostro.getX() != n - 1) {
            int oldX = mostro.getX();
            int oldY = mostro.getY();

            mostro.muovi(lab, player, prev);  // ← passa l'array

            SwingUtilities.invokeLater(() -> {
                model.setValueAt(0, oldX, oldY);
                model.setValueAt(5, mostro.getX(), mostro.getY());
            });

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
}
