/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.EditarCalificacionController;
import controller.PrincipalController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author ignac
 */
public class VistaEditarCalificacion extends javax.swing.JFrame {

    private JMenuItem mnIVolver;
    private final String colorBase = "#480866";
    private final String colorHover = "#7f4c97";
    private final String colorClickPr = "#be34ff";
    private boolean mensajeMostrado1 = false; //boolean para evitar un bucle
    private boolean mensajeMostrado2 = false; //boolean para evitar un bucle

    public VistaEditarCalificacion() {
        initComponents();
        personalizarComponentes();
        SwingUtilities.invokeLater(() -> jPanel1.requestFocusInWindow());
    }

    private void personalizarComponentes() {
        this.setSize(500, 400); // Establece el tamaño de la ventana
        this.setDefaultCloseOperation(VistaEditarCalificacion.EXIT_ON_CLOSE);
        this.setTitle("Edición de la Calificación");
        this.setLocationRelativeTo(null);
        menuBarConfig();
        validaciones();
    }

    private void menuBarConfig() {
        mnIVolver = new JMenuItem("Volver");

        //AGREGO EVENTOS
        mnIVolver.addActionListener((ActionEvent e) -> {
            EditarCalificacionController.btnVolver();
        });

        //CONFIGURO LA MENUBAR
        menuBar.setPreferredSize(new Dimension(this.getWidth(), 35));
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        menuBar.setBackground(Color.decode(colorBase));
        menuBar.setBorderPainted(false);

        //CONFIGURO LOS JMENUITEMS
        mnIVolver.setBackground(Color.decode(colorBase));
        mnIVolver.setPreferredSize(new Dimension(48, 35));
        mnIVolver.setForeground(Color.WHITE);

        menuBar.add(mnIVolver);
        animationJMI();
    }

    private void animationJMI() {
        // Efecto al pasar el mouse sobre los JMenuItems (cambio de color)
        mnIVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mnIVolver.setBackground(Color.decode(colorHover)); // Color al pasar el mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mnIVolver.setBackground(Color.decode(colorBase)); // Color por defecto
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mnIVolver.setBackground(Color.decode(colorClickPr)); // Color al click
            }
        });
    }

    private void validaciones() {
        //Validacion de Nota Parcial 1 que permite números
        txt1P.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }

                if (!Character.isDigit(c)) {
                    e.consume();
                    msgAdvertencia("Este campo solo permite números.");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (txt1P.getText().length() >= 2) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 2 + " caracteres.");
                }

                // Obtener el texto actual + el nuevo carácter
                String texto = txt1P.getText() + c;

                try {
                    int valor = Integer.parseInt(texto);
                    if (valor < 0 || valor > 10) {
                        e.consume();
                        msgAdvertencia("Ingrese un número entre 0 y 10.");
                    }
                } catch (NumberFormatException ex) {
                    e.consume(); // Bloquear cualquier entrada no numérica (seguridad extra)
                }
            }
        });

        //Validacion de Nota Parcial 1 que permite números
        txt2P.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }

                if (!Character.isDigit(c)) {
                    e.consume();
                    msgAdvertencia("Este campo solo permite números.");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (txt2P.getText().length() >= 2) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 2 + " caracteres.");
                }

                // Obtener el texto actual + el nuevo carácter
                String texto = txt2P.getText() + c;

                try {
                    int valor = Integer.parseInt(texto);
                    if (valor < 0 || valor > 10) {
                        e.consume();
                        msgAdvertencia("Ingrese un número entre 0 y 10.");
                    }
                } catch (NumberFormatException ex) {
                    e.consume(); // Bloquear cualquier entrada no numérica (seguridad extra)
                }
            }
        });

        //Validacion de Nota Recuperación 1 que permite números
        txt1R.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }

                if (!Character.isDigit(c)) {
                    e.consume();
                    msgAdvertencia("Este campo solo permite números.");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (txt1R.getText().length() >= 2) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 2 + " caracteres.");
                }

                // Obtener el texto actual + el nuevo carácter
                String texto = txt1R.getText() + c;

                try {
                    int valor = Integer.parseInt(texto);
                    if (valor < 0 || valor > 10) {
                        e.consume();
                        msgAdvertencia("Ingrese un número entre 0 y 10.");
                    }
                } catch (NumberFormatException ex) {
                    e.consume(); // Bloquear cualquier entrada no numérica (seguridad extra)
                }
            }
        });

        //Validacion de Nota Recuperación 2 que permite números
        txt2R.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }

                if (!Character.isDigit(c)) {
                    e.consume();
                    msgAdvertencia("Este campo solo permite números.");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (txt2R.getText().length() >= 2) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 2 + " caracteres.");
                }

                // Obtener el texto actual + el nuevo carácter
                String texto = txt2R.getText() + c;

                try {
                    int valor = Integer.parseInt(texto);
                    if (valor < 0 || valor > 10) {
                        e.consume();
                        msgAdvertencia("Ingrese un número entre 0 y 10.");
                    }
                } catch (NumberFormatException ex) {
                    e.consume(); // Bloquear cualquier entrada no numérica (seguridad extra)
                }
            }
        });

        //AVISOS AL HACER CLICK
        txt1P.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!mensajeMostrado1) {
                    mensajeMostrado1 = true;
                    // Se ejecuta cuando el usuario hace clic en el campo por primera vez
                    JOptionPane.showMessageDialog(null, "Ten en cuenta que al ingresar las notas correspondientes deben estar entre 0 y 10",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Se ejecuta cuando el usuario hace clic fuera del campo (opcional)
            }
        });

        txt1R.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!mensajeMostrado2) {
                    mensajeMostrado2 = true;
                    // Se ejecuta cuando el usuario hace clic en el campo por primera vez
                    JOptionPane.showMessageDialog(null, "Si el alumno logro la promoción en una primera instancia, las notas de recuperación no tendran validez, \nentonces si el alumno no recuperó puede ingresar cualquier número, \nde lo contrario, si el alumno recuperó: ingrese la nota correspondiente.",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Se ejecuta cuando el usuario hace clic fuera del campo (opcional)
            }
        });
    }

    private void msgAdvertencia(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Ingreso incorrecto", JOptionPane.WARNING_MESSAGE);
    }

    private int validacionVacio() {
        //txt1P validar
        String data = txt1P.getText().trim();
        int validador = 0;
        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo de la Nota del 1° Parcial no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
        } else {
            //txt2P validar
            data = txt2P.getText().trim();
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo de la Nota del 2° Parcial no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
            } else {
                //txt1R validar
                data = txt1R.getText().trim();
                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El campo de la Nota de la 1° Recuperación no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
                } else {
                    //txt2R validar
                    data = txt2R.getText().trim();
                    if (data.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "El campo de la Nota de la 2° Recuperación no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
                    } else {
                        validador = 1;
                    }
                }
            }
        }

        return validador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt1P = new javax.swing.JTextField();
        txt1R = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt2P = new javax.swing.JTextField();
        txt2R = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        txtMateria = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Zilla Slab SemiBold", 1, 30)); // NOI18N
        jLabel1.setText("Registro de Calificación");

        jLabel3.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel3.setText("1° Parcial:");

        jLabel4.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel4.setText("1° Recuperación:");

        jLabel5.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel5.setText("2° Parcial:");

        jLabel6.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel6.setText("2° Recuperación:");

        btnEditar.setText("Editar calificación");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        txtMateria.setFont(new java.awt.Font("Zilla Slab SemiBold", 3, 14)); // NOI18N
        txtMateria.setText("Notas de (MATERIA)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(btnEditar)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMateria)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt1P)
                                    .addComponent(txt1R, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt2R, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt2P, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtMateria)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt1P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt2P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt1R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt2R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnEditar)
                .addGap(47, 47, 47))
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

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (validacionVacio() != 1) {
            JOptionPane.showMessageDialog(this, "No puede dejar espacios en vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            EditarCalificacionController.editarCalificacion();
            JOptionPane.showMessageDialog(this, "Modificación de la Calificacion realizada con éxito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            EditarCalificacionController.ocultar();
            PrincipalController.mostrar();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    public JTextField getTxt1P() {
        return txt1P;
    }

    public void setTxt1P(JTextField txt1P) {
        this.txt1P = txt1P;
    }

    public JTextField getTxt1R() {
        return txt1R;
    }

    public void setTxt1R(JTextField txt1R) {
        this.txt1R = txt1R;
    }

    public JTextField getTxt2P() {
        return txt2P;
    }

    public void setTxt2P(JTextField txt2P) {
        this.txt2P = txt2P;
    }

    public JTextField getTxt2R() {
        return txt2R;
    }

    public void setTxt2R(JTextField txt2R) {
        this.txt2R = txt2R;
    }

    public JLabel getTxtMateria() {
        return txtMateria;
    }

    public void setTxtMateria(JLabel txtMateria) {
        this.txtMateria = txtMateria;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField txt1P;
    private javax.swing.JTextField txt1R;
    private javax.swing.JTextField txt2P;
    private javax.swing.JTextField txt2R;
    private javax.swing.JLabel txtMateria;
    // End of variables declaration//GEN-END:variables
}
