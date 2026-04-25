/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ipc1.proyecto2.views;

import java.awt.CardLayout;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class Module extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Module.class.getName());

    /**
     * Creates new form Module
     */
    public Module() {
        StorePane s_pane = new StorePane();
        UserPane u_pane = new UserPane();
        AlbumPane a_pane = new AlbumPane();
        EventsPane e_pane = new EventsPane();
        RankingPane r_pane = new RankingPane();
        
        initComponents();
        //jMenuBar1.setBorder(null);
        jPanel2.setBorder(null);
        jMenuBar1.setBorder(new EmptyBorder(2, 3, 3, 0));
        
        jPanel2.add(s_pane, "tienda");
        jPanel2.add(u_pane, "perfil");
        jPanel2.add(a_pane, "album");
        jPanel2.add(e_pane, "eventos");
        jPanel2.add(r_pane, "ranking");
        
        
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePane("tienda");
            }
        });
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePane("ranking");
            }
        });
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePane("perfil");
            }
        });
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePane("eventos");
            }
        });
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePane("album");
            }
        });
    }
    
    private void changePane(String name) {
        CardLayout layout = (CardLayout) jPanel2.getLayout();
        layout.show(jPanel2, name);
    }
    

    
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(32, 32, 32));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(32, 32, 32));
        jPanel2.setLayout(new java.awt.CardLayout());

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(51, 51, 51));

        jMenu1.setBackground(new java.awt.Color(51, 51, 51));
        jMenu1.setBorder(null);
        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Tienda de Juegos");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenu1.addActionListener(this::jMenu1ActionPerformed);
        jMenuBar1.add(jMenu1);

        jMenu5.setBackground(new java.awt.Color(51, 51, 51));
        jMenu5.setBorder(null);
        jMenu5.setForeground(new java.awt.Color(255, 255, 255));
        jMenu5.setText("Eventos");
        jMenu5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu5);

        jMenu6.setBackground(new java.awt.Color(51, 51, 51));
        jMenu6.setBorder(null);
        jMenu6.setForeground(new java.awt.Color(255, 255, 255));
        jMenu6.setText("Mi Album");
        jMenu6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu6);

        jMenu2.setBackground(new java.awt.Color(51, 51, 51));
        jMenu2.setBorder(null);
        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setText("Ranking");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu2);

        jMenu4.setBackground(new java.awt.Color(51, 51, 51));
        jMenu4.setBorder(null);
        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setText("                                                                                                                                                              ");
        jMenu4.setEnabled(false);
        jMenu4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu4);

        jMenu3.setBackground(new java.awt.Color(51, 51, 51));
        jMenu3.setBorder(null);
        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setText("USUARIO");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jMenu3.addActionListener(this::jMenu3ActionPerformed);
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1039, 537);
    }// </editor-fold>//GEN-END:initComponents

  
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Module().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
