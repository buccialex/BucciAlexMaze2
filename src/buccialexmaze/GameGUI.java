/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buccialexmaze;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bucci.alex
 */
public class GameGUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameGUI.class.getName());

    /**
     * Creates new form GameGUI
     */
    public GameGUI(String nomePlayer, int difficolta, int nMele) {
        // impostazione della form
        this.setLocationRelativeTo(null);
        initComponents();
        this.setTitle("Gioco in corso");
        lblPunti.setText("0");

        // usa i dati 
        lblNomePlayer.setText(nomePlayer);
        lblMele.setText("Mele: " + nMele);
        this.difficolta = difficolta;
        this.nMele = nMele;

        // funzioni che avviano il gioco
        preparaTabella(nomePlayer, nMele);
        gioco();

    }

    /**
     * setter della lable che mostra il numero di mele
     *
     * @param nMele numero di mele
     */
    public void setLblMele(String nMele) {
        this.lblMele.setText(nMele);
    }

    /**
     * metodo che prepara la tabella
     *
     * @param nomePlayer nome del player
     * @param nMele numero delle mele
     */
    public void preparaTabella(String nomePlayer, int nMele) {
        lab = new Labirinto(nMele);
        int[][] matriceInteri = lab.getMappa();
        player = new Player(nomePlayer);
        mostro = new Mostro("Mostro");
        mostro.setX(lab.getxEntrata());
        mostro.setY(lab.getyEntrata());

        player.setX(lab.getxEntrata());
        player.setY(lab.getyEntrata());

        int righe = matriceInteri.length;
        int colonne = matriceInteri[0].length;

        Integer[][] dati = new Integer[righe][colonne];
        String[] intestazioni = new String[colonne];

        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                dati[i][j] = matriceInteri[i][j];
                // metti i titoli vuoti
                intestazioni[j] = "";
            }
        }

        DefaultTableModel model = new DefaultTableModel(dati, intestazioni) {
            @Override
            // disabilita la modifica delle celle
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabella.setModel(model);

        tabella.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                // mantieni invisibile il contenuto delle celle
                setText("");
                // impostazione dei colori in base al contenuto
                if (value instanceof Integer) {
                    int val = (Integer) value;
                    switch (val) {
                        case 1: // MURI
                            c.setBackground(Color.RED);
                            break;

                        case 3: // PLAYER
                            c.setBackground(Color.WHITE);
                            break;

                        case 5: // MOSTRO
                            c.setBackground(Color.ORANGE);
                            break;
                        case 8: // MELE
                            c.setBackground(Color.GREEN);
                            break;
                        default: // pavimento
                            c.setBackground(Color.BLACK);
                            break;

                    }
                }
                return c;
            }
        });

        // squadra le celle
        int size = 20;
        tabella.setRowHeight(size);

        for (int i = 0; i < colonne; i++) {
            tabella.getColumnModel().getColumn(i).setPreferredWidth(size);
            tabella.getColumnModel().getColumn(i).setMinWidth(size);
            tabella.getColumnModel().getColumn(i).setMaxWidth(size);
        }

        // nascondi la griglia
        tabella.setShowGrid(false);
        tabella.setIntercellSpacing(new Dimension(0, 0));
    }

    /**
     * metodo che esegue il gioco
     */
    public void gioco() {
        if (lab != null && player != null) {
            DefaultTableModel model = (DefaultTableModel) tabella.getModel();

            // regola la velocità del mostro in base alla difficoltà
            int ritardoMostro = switch (difficolta) {
                case 1 ->
                    150;
                case 2 ->
                    80;
                case 3 ->
                    30;
                default ->
                    100;
            };

            // assegnazione del runner al thread per il player
            MuoviPlayer runner = new MuoviPlayer(player, lab, model, gameOver, this);
            Thread thread = new Thread(runner);
            thread.start();

            // assegnazione del runner al thread per il mostro
            MuoviMostro runnerMostro = new MuoviMostro(mostro, player, lab, model, 2000, gameOver, ritardoMostro);
            Thread threadMostro = new Thread(runnerMostro);
            threadMostro.start();
        }
    }

    /**
     * setter lable punti
     *
     * @param testo valore dei punti
     */
    public void setLblPunti(String testo) {
        this.lblPunti.setText(testo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabella = new javax.swing.JTable();
        lblMele = new javax.swing.JLabel();
        lblNomePlayer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPunti = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(897, 685));
        setPreferredSize(new java.awt.Dimension(897, 685));
        setResizable(false);
        setSize(new java.awt.Dimension(897, 685));

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        tabella.setBackground(new java.awt.Color(0, 0, 0));
        tabella.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabella.setForeground(new java.awt.Color(0, 0, 0));
        tabella.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabella.setEnabled(false);
        jScrollPane1.setViewportView(tabella);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);
        jPanel3.setBounds(0, 0, 640, 650);

        lblMele.setBackground(new java.awt.Color(255, 255, 255));
        lblMele.setFont(new java.awt.Font("Siemens Slab SC", 1, 18)); // NOI18N
        lblMele.setForeground(new java.awt.Color(255, 255, 255));
        lblMele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMele.setText("placeholder messaggio mele");
        jPanel2.add(lblMele);
        lblMele.setBounds(630, 0, 260, 50);

        lblNomePlayer.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblNomePlayer.setForeground(new java.awt.Color(255, 255, 255));
        lblNomePlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomePlayer.setText("PLACEHOLDERNOME");
        jPanel2.add(lblNomePlayer);
        lblNomePlayer.setBounds(650, 270, 240, 70);

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Punteggio:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(660, 240, 90, 40);

        lblPunti.setFont(new java.awt.Font("Segoe UI Light", 2, 18)); // NOI18N
        lblPunti.setForeground(new java.awt.Color(255, 255, 255));
        lblPunti.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPunti.setText("PUNTI");
        jPanel2.add(lblPunti);
        lblPunti.setBounds(750, 240, 120, 40);

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/PlayerGIF.gif"))); // NOI18N
        jLabel1.setToolTipText("");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(650, 330, 230, 250);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    /**
     * attributi: gameover = se il mostro ha preso il player lab = labirinto
     * player = player mostro = mostro difficoltà = difficoltà nMele = numero
     * delle mele
     */
    private boolean[] gameOver = {false};
    private Labirinto lab;
    private Player player;
    private Mostro mostro;
    private int difficolta;
    private int nMele;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMele;
    private javax.swing.JLabel lblNomePlayer;
    private javax.swing.JLabel lblPunti;
    private javax.swing.JTable tabella;
    // End of variables declaration//GEN-END:variables
}
