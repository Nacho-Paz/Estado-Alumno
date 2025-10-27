/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.PrincipalController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

/**
 *
 * @author ignac
 */
public class VistaPrincipal extends javax.swing.JFrame {

    private JMenuItem mnIAgregar;
    private JMenuItem mnIListar;
    private JMenuItem mnICali;
    private final String colorBase = "#480866";
    private final String colorHover = "#7f4c97";
    private final String colorClickPr = "#be34ff";

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        personalizarComponentes();
    }

    private void personalizarComponentes() {
        this.setSize(500, 400); // Establece el tamaño de la ventana
        this.setDefaultCloseOperation(VistaPrincipal.EXIT_ON_CLOSE);
        this.setTitle("Estado de Alumnos");
        this.setLocationRelativeTo(null);
        menuBarConfig();

    }

    private void menuBarConfig() {
        mnIAgregar = new JMenuItem("Registrar Alumno");
        mnIListar = new JMenuItem("Listar Alumnos");
        mnICali = new JMenuItem("Registrar Calificación");

        //AGREGO EVENTOS
        mnIAgregar.addActionListener((ActionEvent e) -> {
            PrincipalController.btnAgregar();
        });

        mnIListar.addActionListener((ActionEvent e) -> {
            PrincipalController.btnListar();
        });
        
        mnICali.addActionListener((ActionEvent e) -> {
            PrincipalController.btnCalificacion();
        });

        //CONFIGURO LA MENUBAR
        menuBar.setPreferredSize(new Dimension(this.getWidth(), 35));
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        menuBar.setBackground(Color.decode(colorBase));
        menuBar.setBorderPainted(false);

        //CONFIGURO LOS JMENUITEMS
        mnIAgregar.setBackground(Color.decode(colorBase));
        mnIAgregar.setPreferredSize(new Dimension(110, 35));
        mnIAgregar.setForeground(Color.WHITE);

        mnIListar.setBackground(Color.decode(colorBase));
        mnIListar.setPreferredSize(new Dimension(100, 35));
        mnIListar.setForeground(Color.WHITE);
        
        mnICali.setBackground(Color.decode(colorBase));
        mnICali.setPreferredSize(new Dimension(135, 35));
        mnICali.setForeground(Color.WHITE);

        menuBar.add(mnIAgregar);
        menuBar.add(mnIListar);
        menuBar.add(mnICali);

        animationJMI();
    }
    
    private void animationJMI() {
        // Efecto al pasar el mouse sobre los JMenuItems (cambio de color)
        mnIAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnIAgregar.setBackground(Color.decode(colorHover)); // Color al pasar el mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnIAgregar.setBackground(Color.decode(colorBase)); // Color por defecto
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnIAgregar.setBackground(Color.decode(colorClickPr)); // Color al click
            }
        });

        mnIListar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnIListar.setBackground(Color.decode(colorHover)); // Color al pasar el mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnIListar.setBackground(Color.decode(colorBase)); // Color por defecto
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnIListar.setBackground(Color.decode(colorClickPr)); // Color al click
            }
        });
        
        mnICali.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnICali.setBackground(Color.decode(colorHover)); // Color al pasar el mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnICali.setBackground(Color.decode(colorBase)); // Color por defecto
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnICali.setBackground(Color.decode(colorClickPr)); // Color al click
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Zilla Slab SemiBold", 2, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Estado de Alumnos de ISI");

        jLabel2.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CRUD + Consulta");

        jLabel3.setFont(new java.awt.Font("Zilla Slab SemiBold", 1, 48)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Programa:");

        jLabel4.setFont(new java.awt.Font("Zilla Slab SemiBold", 2, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("de Segundo Año");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}
