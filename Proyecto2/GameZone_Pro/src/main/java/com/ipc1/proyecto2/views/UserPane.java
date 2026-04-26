package com.ipc1.proyecto2.views;

import com.ipc1.proyecto2.controlllers.UserController;
import com.ipc1.proyecto2.models.Achievements;
import com.ipc1.proyecto2.controlllers.AchievementsController;
import com.ipc1.proyecto2.controlllers.ShoppingRecordController;
import com.ipc1.proyecto2.models.NodeShoppingRecord;
import com.ipc1.proyecto2.models.ShoppingRecord;
import com.ipc1.proyecto2.models.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Admin
 */
public class UserPane extends javax.swing.JPanel {
    int gasto;
    boolean f_time;
    User loggedUser;
    Module module_view;
    UserController u_controller;
    ShoppingRecordController sr_controller;
    AchievementsController a_controller;
    Login loginView = new Login();
    
    public UserPane(User loggedUser, UserController u_controller, Module module_view) {
        this.gasto = 0;
        this.f_time = true;
        this.loggedUser = loggedUser;
        this.u_controller = u_controller;
        this.sr_controller = new ShoppingRecordController();
        this.module_view = module_view;
        this.a_controller = new AchievementsController();
        
        initComponents();
        
        jProgressBar1.setForeground(new java.awt.Color(245, 176, 39));
        jProgressBar1.setBackground(new java.awt.Color(32, 32, 32)); 
        jProgressBar1.setBorderPainted(false);
        
        jLabel1.setText(loggedUser.getUsername());
        
        refresh();
    }
    
    
   
    public void refresh() {
        achivementsList(jScrollPane1, loggedUser.getUsername());
        recordList(jScrollPane3, loggedUser.getUsername());
        
        switch(loggedUser.getLevel()){
            case 1:
                updateProgress(loggedUser.getXp(), 499);
                jLabel4.setText("Nvl."+String.valueOf(loggedUser.getLevel())+" - Aprediz");
                jLabel5.setText("XP "+String.valueOf(loggedUser.getXp())+" / 499");
                break;
            case 2:
                updateProgress(loggedUser.getXp(), 1499);
                jLabel4.setText("Nvl."+String.valueOf(loggedUser.getLevel())+" - Jugador");
                jLabel5.setText("XP "+String.valueOf(loggedUser.getXp())+" / 1499");
                break;
            case 3:
                updateProgress(loggedUser.getXp(), 3499);
                jLabel4.setText("Nvl."+String.valueOf(loggedUser.getLevel())+" - Veterano");
                jLabel5.setText("XP "+String.valueOf(loggedUser.getXp())+" / 3499");
                break;
            case 4:
                updateProgress(loggedUser.getXp(), 6999);
                jLabel4.setText("Nvl."+String.valueOf(loggedUser.getLevel())+" - Mestro");
                jLabel5.setText("XP "+String.valueOf(loggedUser.getXp())+" / 6999");
                break;
            case 5:
                updateProgress(loggedUser.getXp(), 7000);
                jLabel4.setText("Nvl."+String.valueOf(loggedUser.getLevel())+" - Leyenda");
                jLabel5.setText("XP "+String.valueOf(loggedUser.getXp())+" / 7000");
                break;
        }
    }
    
    
    public void recordList(JScrollPane scroll, String id_user) {
        gasto=0;
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(new Color(32, 32, 32));

        NodeShoppingRecord actual = sr_controller.listByUser(id_user);

        if (actual == null) {
            JLabel vacio = new JLabel("Usted no ha hecho ninguna compra.");
            vacio.setForeground(Color.GRAY);
            vacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            contenedor.add(Box.createVerticalStrut(20));
            contenedor.add(vacio);
        }

        while (actual != null) {
            contenedor.add(createCard_R(actual.getRecord()));
            contenedor.add(Box.createVerticalStrut(6));
            actual = actual.getSiguiente();
        }
        
        contenedor.setBorder(null);
        scroll.setViewportView(contenedor);
        scroll.revalidate();
        scroll.repaint();
    }

    private JPanel createCard_R(ShoppingRecord record) {
        gasto = (int) (gasto+record.getTotal());
        
        if(gasto>2000 && f_time){
            a_controller.updateAchievement(loggedUser.getUsername(), 8);
            f_time = false;
        }      
                
        JPanel card = new JPanel(new BorderLayout(10, 4));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 60), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        card.setBackground(new Color(60, 60, 60));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Nombre del juego
        JLabel nombre = new JLabel(record.getName_videogame());
        nombre.setFont(new Font("Arial", Font.BOLD, 14));
        nombre.setForeground(new Color(100, 180, 255));

        // Fecha y hora
        JLabel fecha = new JLabel(record.getDate() + "  " + record.getTime());
        fecha.setFont(new Font("Arial", Font.PLAIN, 11));
        fecha.setForeground(new Color(150, 150, 150));

        
        JLabel total = new JLabel("x" + record.getQuantity() + "  |  Q" + String.format("%.2f", record.getTotal()));
        total.setFont(new Font("Arial", Font.BOLD, 13));
        total.setForeground(new Color(255, 255, 255));
        total.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 3));
        centro.setOpaque(false);
        centro.add(nombre);
        centro.add(fecha);

        card.add(centro, BorderLayout.CENTER);
        card.add(total, BorderLayout.EAST);

        return card;
    }
    
    public void updateProgress(int actual, int maximo) {
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(maximo);

        jProgressBar1.setValue(actual);

        jProgressBar1.setString(actual + " / " + maximo + " XP");
    }
    
    
    
    public void achivementsList(JScrollPane scroll, String id_user) {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(new Color(60,60,60));

        Achievements[] lista = a_controller.listForUser(id_user);

        for (Achievements a : lista) {
            contenedor.add(createCard_A(a));
            contenedor.add(Box.createVerticalStrut(6));
        }

        scroll.setViewportView(contenedor);
        scroll.revalidate();
        scroll.repaint();
    }
    private JPanel createCard_A(Achievements a) {
        boolean desbloqueado = a.isState();
        
        JPanel card = new JPanel(new BorderLayout(10, 4));
        JLabel name = new JLabel(a.getName());
        JLabel desc = new JLabel("<html>" + a.getDescription() + "</html>");
        
        JPanel centro = new JPanel(new GridLayout(2, 1, 0, 3));

        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                desbloqueado ? new Color(255, 255, 255) : new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        
        card.setBackground(desbloqueado ? new Color(245, 176, 39) : new Color(50, 50, 50));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        name.setFont(new Font("Arial", Font.BOLD, 14));
        name.setForeground(desbloqueado ? new Color(0, 0, 0) : new Color(150, 150, 150));

        desc.setFont(new Font("Arial", Font.PLAIN, 12));
        desc.setForeground(desbloqueado ? new Color(0, 0, 0) : new Color(110, 110, 110));

        centro.setOpaque(false);
        centro.add(name);  
        centro.add(desc);   

        card.add(centro, BorderLayout.CENTER);
        return card;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(32, 32, 32));
        setMaximumSize(new java.awt.Dimension(1025, 483));
        setMinimumSize(new java.awt.Dimension(1025, 483));

        jPanel1.setBackground(new java.awt.Color(60, 60, 60));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario");
        jLabel1.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nvl. 0");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("XP 000");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Logros");

        jScrollPane1.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jToggleButton1.setBackground(new java.awt.Color(255, 100, 103));
        jToggleButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton1.setText("CERRAR SESION");
        jToggleButton1.setBorder(null);
        jToggleButton1.addActionListener(this::jToggleButton1ActionPerformed);

        jScrollPane3.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Historial de compras");
        jLabel3.setToolTipText("");

        jToggleButton2.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(204, 204, 204));
        jToggleButton2.setText("Estudiante");
        jToggleButton2.setBorder(null);
        jToggleButton2.addActionListener(this::jToggleButton2ActionPerformed);

        jToggleButton3.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(204, 204, 204));
        jToggleButton3.setText("Inventario Tienda");
        jToggleButton3.setBorder(null);
        jToggleButton3.addActionListener(this::jToggleButton3ActionPerformed);

        jToggleButton4.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(204, 204, 204));
        jToggleButton4.setText("Ventas");
        jToggleButton4.setBorder(null);
        jToggleButton4.addActionListener(this::jToggleButton4ActionPerformed);

        jToggleButton5.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jToggleButton5.setForeground(new java.awt.Color(204, 204, 204));
        jToggleButton5.setText("Album");
        jToggleButton5.setBorder(null);
        jToggleButton5.addActionListener(this::jToggleButton5ActionPerformed);

        jToggleButton6.setBackground(new java.awt.Color(51, 51, 51));
        jToggleButton6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jToggleButton6.setForeground(new java.awt.Color(204, 204, 204));
        jToggleButton6.setText("Torneos");
        jToggleButton6.setBorder(null);
        jToggleButton6.addActionListener(this::jToggleButton6ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButton6)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton5)
                        .addComponent(jToggleButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        loggedUser = null;
        module_view.setVisible(false);
        loginView.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    // End of variables declaration//GEN-END:variables
}
