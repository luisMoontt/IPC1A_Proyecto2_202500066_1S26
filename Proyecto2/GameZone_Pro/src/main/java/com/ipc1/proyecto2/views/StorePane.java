package com.ipc1.proyecto2.views;

import com.ipc1.proyecto2.controlllers.ShopCartController;
import com.ipc1.proyecto2.controlllers.VideoGameController;
import com.ipc1.proyecto2.models.NodeShopCart;
import com.ipc1.proyecto2.models.ShopCart;
import com.ipc1.proyecto2.models.User;
import com.ipc1.proyecto2.models.VideoGames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

/**
 *
 * @author Admin
 */
public class StorePane extends javax.swing.JPanel {
    VideoGameController vg_controller;
    ShopCartController sc_controller;
    User loggedUser;
    VideoGames selectedGame;
    
    public StorePane(User loggedUser) {  // <- corrige loggerUser a loggedUser
        this.loggedUser = loggedUser;    // <- mueve esto ANTES de initComponents
        this.vg_controller = new VideoGameController();
        this.sc_controller = new ShopCartController(loggedUser, vg_controller);
        initComponents();                // <- solo una vez
        cargarJuegos();
        refreshCarrito();                // <- carga el carrito al inicio
    }
    
    // CARGAR GRID DE JUEGOS ///////////////////////////////////////////////////
    
    private void cargarJuegos() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 8, 8)); 
        grid.setBackground(new Color(32, 32, 32));
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        VideoGames[] juegos = vg_controller.getVideogames();

        for (VideoGames vg : juegos) {
            JPanel card = createGameCard(vg);
            grid.add(card);
        }

        jScrollPane2.setViewportView(grid);
        jScrollPane2.revalidate();
        jScrollPane2.repaint();
    }

    // CARD DE CADA JUEGO //////////////////////////////////////////////////////
    private JPanel createGameCard(VideoGames vg) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(new Color(50, 50, 50));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 80), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        card.setPreferredSize(new Dimension(160, 180));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Nombre
        JLabel nombre = new JLabel("<html><center>" + vg.getName() + "</center></html>");
        nombre.setFont(new Font("Arial", Font.BOLD, 13));
        nombre.setForeground(new Color(255, 255, 255));
        nombre.setHorizontalAlignment(SwingConstants.CENTER);

        // Género y plataforma
        JLabel genero = new JLabel(vg.getGenre() + " | " + vg.getPlataform());
        genero.setFont(new Font("Arial", Font.PLAIN, 11));
        genero.setForeground(new Color(150, 150, 150));
        genero.setHorizontalAlignment(SwingConstants.CENTER);

        // Precio
        JLabel precio = new JLabel("Q" + String.format("%.2f", vg.getPrice()));
        precio.setFont(new Font("Arial", Font.BOLD, 14));
        precio.setForeground(new Color(100, 220, 100));
        precio.setHorizontalAlignment(SwingConstants.CENTER);

        // Stock
        JLabel stock = new JLabel("Stock: " + vg.getStock());
        stock.setFont(new Font("Arial", Font.PLAIN, 11));
        stock.setForeground(vg.getStock() > 0 ? new Color(100, 200, 100) : new Color(220, 80, 80));
        stock.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel info = new JPanel(new GridLayout(3, 1, 0, 2));
        info.setOpaque(false);
        info.add(genero);
        info.add(precio);
        info.add(stock);

        card.add(nombre, BorderLayout.NORTH);
        card.add(info, BorderLayout.CENTER);

        // Click para mostrar menú
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarMenuJuego(vg, card);
            }
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(70, 70, 70));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(new Color(50, 50, 50));
            }
        });

        return card;
    }

    // MENU AL HACER CLICK /////////////////////////////////////////////////////
    private void mostrarMenuJuego(VideoGames vg, JPanel card) {
        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(new Color(40, 40, 40));

        // Info del juego
        JMenuItem info = new JMenuItem("<html><b>" + vg.getName() + "</b><br>" +
            vg.getDescription() + "</html>");
        info.setEnabled(false);
        info.setBackground(new Color(40, 40, 40));
        info.setForeground(Color.WHITE);

        menu.add(info);
        menu.addSeparator();

        // Cantidad
        JPanel panelCantidad = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelCantidad.setBackground(new Color(40, 40, 40));
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setForeground(Color.WHITE);
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, vg.getStock(), 1));
        spinner.setPreferredSize(new Dimension(60, 25));
        panelCantidad.add(lblCantidad);
        panelCantidad.add(spinner);

        JMenuItem itemCantidad = new JMenuItem();
        itemCantidad.setLayout(new BorderLayout());
        itemCantidad.add(panelCantidad);
        itemCantidad.setBackground(new Color(40, 40, 40));
        menu.add(itemCantidad);

        menu.addSeparator();

        // Agregar al carrito
        JMenuItem agregar = new JMenuItem("🛒 Agregar al carrito");
        agregar.setBackground(new Color(40, 40, 40));
        agregar.setForeground(new Color(100, 220, 100));
        
        agregar.addActionListener(e -> {
            int cantidad = (int) spinner.getValue();
            sc_controller.add(vg, cantidad);
            refreshCarrito();
            JOptionPane.showMessageDialog(this,
                cantidad + "x " + vg.getName() + " agregado al carrito.",
                "Carrito", JOptionPane.INFORMATION_MESSAGE);
            menu.setVisible(false);
        });

        menu.add(agregar);
        menu.show(card, card.getWidth() / 2, card.getHeight() / 2);
    }
    // CARRITO //////////////////////////////////////////////////////////////////
    private void refreshCarrito() {
        mostrarCarrito(jScrollPane1, sc_controller, this::refreshCarrito);
    }
    public void mostrarCarrito(JScrollPane scroll, ShopCartController sc_controller, Runnable onUpdate) {
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(new Color(60, 60, 60));

        NodeShopCart actual = sc_controller.getCarrito().getHead();

        if (actual == null) {
            JLabel vacio = new JLabel("El carrito está vacío.");
            vacio.setForeground(Color.GRAY);
            vacio.setAlignmentX(Component.CENTER_ALIGNMENT);
            contenedor.add(Box.createVerticalStrut(20));
            contenedor.add(vacio);
        }

        while (actual != null) {
            final NodeShopCart nodo = actual;
            JPanel card = createCartCard(nodo, sc_controller, onUpdate);
            contenedor.add(card);
            contenedor.add(Box.createVerticalStrut(6));
            actual = actual.getSiguiente();
        }

        scroll.setViewportView(contenedor);
        scroll.revalidate();
        scroll.repaint();
    }

    private JPanel createCartCard(NodeShopCart nodo, ShopCartController sc_controller, Runnable onUpdate) {
        VideoGames vg = nodo.getVideogame();

        JPanel card = new JPanel(new BorderLayout(10, 4));
        card.setBackground(new Color(32,32,32));
        card.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Nombre y precio
        JLabel nombre = new JLabel(vg.getName());
        nombre.setFont(new Font("Arial", Font.BOLD, 13));
        nombre.setForeground(Color.WHITE);

        JLabel precio = new JLabel("Q" + String.format("%.2f", vg.getPrice()));
        precio.setFont(new Font("Arial", Font.PLAIN, 12));
        precio.setForeground(new Color(150, 150, 150));

        JPanel info = new JPanel(new GridLayout(2, 1, 0, 2));
        info.setOpaque(false);
        info.add(nombre);
        info.add(precio);

        // Controles cantidad + eliminar
        JPanel controles = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        controles.setOpaque(false);

        JButton btnMenos = new JButton("-");
        JLabel lblCantidad = new JLabel(String.valueOf(nodo.getCantidad()));
        JButton btnMas = new JButton("+");
        JButton btnEliminar = new JButton("X");

        // Estilo botones
        for (JButton btn : new JButton[]{btnMenos, btnMas, btnEliminar}) {
            btn.setBackground(new Color(32,32,32));
            btn.setForeground(Color.WHITE);
            btn.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            btn.setFocusPainted(false);
        }
        
        btnEliminar.setBackground(new Color(255, 100 ,103));
        lblCantidad.setForeground(Color.WHITE);
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 13));

        // Total de este item
        JLabel totalItem = new JLabel("Q" + String.format("%.2f", vg.getPrice() * nodo.getCantidad()));
        totalItem.setForeground(new Color(100, 220, 100));
        totalItem.setFont(new Font("Arial", Font.BOLD, 13));

        btnMenos.addActionListener(e -> {
            if (nodo.getCantidad() > 1) {
                sc_controller.setCantidad(vg.getId_videogame(), nodo.getCantidad() - 1);
            }
            onUpdate.run();
        });

        btnMas.addActionListener(e -> {
            sc_controller.setCantidad(vg.getId_videogame(), nodo.getCantidad() + 1);
            onUpdate.run();
        });

        btnEliminar.addActionListener(e -> {
            sc_controller.remove(vg.getId_videogame());
            onUpdate.run();
        });

        controles.add(btnMenos);
        controles.add(lblCantidad);
        controles.add(btnMas);
        controles.add(Box.createHorizontalStrut(8));
        controles.add(totalItem);
        controles.add(Box.createHorizontalStrut(8));
        controles.add(btnEliminar);

        card.add(info, BorderLayout.CENTER);
        card.add(controles, BorderLayout.EAST);

        return card;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(32, 32, 32));

        jPanel2.setBackground(new java.awt.Color(60, 60, 60));

        jScrollPane1.setBorder(null);

        jButton1.setBackground(new java.awt.Color(203, 223, 144));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("PAGAR");
        jButton1.setBorder(null);
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Tienda de Videojuegos");
        jLabel1.setToolTipText("");

        jScrollPane2.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(24, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(63, 63, 63)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (sc_controller.getCarrito().getHead() == null) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (sc_controller.validarCarrito()) {
            sc_controller.payCart();
            JOptionPane.showMessageDialog(this, "Compra realizada con éxito!", "COMPRA", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Stock insuficiente en uno o más juegos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        cargarJuegos();
        refreshCarrito();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
