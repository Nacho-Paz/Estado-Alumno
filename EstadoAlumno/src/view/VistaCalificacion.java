/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.CalificacionController;
import controller.PrincipalController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ignac
 */
public class VistaCalificacion extends javax.swing.JFrame {

    private JMenuItem mnIVolver;
    private final String colorBase = "#480866";
    private final String colorHover = "#7f4c97";
    private final String colorClickPr = "#be34ff";
    private boolean mensajeMostrado1 = false; //boolean para evitar un bucle
    private boolean mensajeMostrado2 = false; //boolean para evitar un bucle

    public VistaCalificacion() {
        initComponents();
        personalizarComponentes();
    }

    private void personalizarComponentes() {
        this.setSize(500, 400); // Establece el tamaño de la ventana
        this.setDefaultCloseOperation(VistaCalificacion.EXIT_ON_CLOSE);
        this.setTitle("Ingreso de Alumno");
        this.setLocationRelativeTo(null);
        cbxMateria.setEditable(false);
        menuBarConfig();
        validaciones();
        limpiarCampos();
    }

    private void menuBarConfig() {
        mnIVolver = new JMenuItem("Volver");

        //AGREGO EVENTOS
        mnIVolver.addActionListener((ActionEvent e) -> {
            CalificacionController.btnVolver();
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
        //Validacion de Legajo que permite números
        txtLegajo.addKeyListener(new KeyAdapter() {
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
                if (txtLegajo.getText().length() >= 5) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 5 + " caracteres.");
                }
            }
        });

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
                    JOptionPane.showMessageDialog(null, "Ten en cuenta al ingresar las notas correspondientes deben estar entre 0 y 10",
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
        //inputNombre validar
        String data = txtLegajo.getText().trim(); //El.trim elemina espacios al final e inicio
        int validador = 0;
        if (data.isEmpty()) {
            //Ventana emergente
            JOptionPane.showMessageDialog(this, "El campo 'Legajo' no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
        } else {
            //txt1P validar
            data = txt1P.getText().trim();
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

        }
        return validador;
    }

    public void limpiarCampos() {
        txtLegajo.setText("");
        txt1P.setText("");
        txt2P.setText("");
        txt1R.setText("");
        txt2R.setText("");
        cbxMateria.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLegajo = new javax.swing.JTextField();
        cbxMateria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt1P = new javax.swing.JTextField();
        txt1R = new javax.swing.JTextField();
        txt2P = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt2R = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Zilla Slab SemiBold", 1, 30)); // NOI18N
        jLabel1.setText("Registro de Calificación");

        jLabel7.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel7.setText("Legajo del Alumno:");

        jLabel2.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel2.setText("Materia:");

        cbxMateria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM II", "Física II", "AS", "Inglés I", "Paradigmas", "Sintáxis", "SO" }));

        jLabel3.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel3.setText("1° Parcial:");

        jLabel4.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel4.setText("1° Recuperación:");

        jLabel5.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel5.setText("2° Parcial:");

        jLabel6.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel6.setText("2° Recuperación:");

        btnAgregar.setText("Agregar Calificación");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxMateria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt1P)
                                    .addComponent(txt1R, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt2P, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(txt2R)))
                            .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txt1P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(txt1R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(btnAgregar)
                .addGap(21, 21, 21))
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (validacionVacio() != 1) {
            JOptionPane.showMessageDialog(this, "No puede dejar espacios en vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String msg = CalificacionController.addCalificacion();
            if (msg.equals("El legajo ingresado no existe.") || msg.contains("La asignatura: ") || msg.contains("Error al")) {
                JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, msg, "Exito", JOptionPane.INFORMATION_MESSAGE);
                CalificacionController.ocultar();
                PrincipalController.mostrar();
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    public JComboBox<String> getCbxMateria() {
        return cbxMateria;
    }

    public void setCbxMateria(JComboBox<String> cbxMateria) {
        this.cbxMateria = cbxMateria;
    }

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

    public JTextField getTxtLegajo() {
        return txtLegajo;
    }

    public void setTxtLegajo(JTextField txtLegajo) {
        this.txtLegajo = txtLegajo;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JComboBox<String> cbxMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField txt1P;
    private javax.swing.JTextField txt1R;
    private javax.swing.JTextField txt2P;
    private javax.swing.JTextField txt2R;
    private javax.swing.JTextField txtLegajo;
    // End of variables declaration//GEN-END:variables
}
