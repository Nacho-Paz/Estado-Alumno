/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.EditarAlumnoController;
import controller.EditarCalificacionController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ignac
 */
public class VistaEditarAlumno extends javax.swing.JFrame {

    private JMenuItem mnIVolver;
    private final String colorBase = "#480866";
    private final String colorHover = "#7f4c97";
    private final String colorClickPr = "#be34ff";
    private boolean mensajeMostrado1 = false; //boolean para evitar un bucle

    public VistaEditarAlumno() {
        initComponents();
        personalizarComponentes();
    }

    private void personalizarComponentes() {
        this.setSize(500, 400); // Establece el tamaño de la ventana
        this.setDefaultCloseOperation(VistaListaAlumno.EXIT_ON_CLOSE);
        this.setTitle("Edición de Alumno");
        this.setLocationRelativeTo(null);
        menuBarConfig();
        validaciones();
    }

    private void menuBarConfig() {
        mnIVolver = new JMenuItem("Volver");

        //AGREGO EVENTOS
        mnIVolver.addActionListener((ActionEvent e) -> {
            EditarAlumnoController.btnVolver();
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
        //Validacion de Nombre que permite letras
        txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                // Permitir teclas de control: SHIFT, BLOQ MAYÚS, BACKSPACE y DELETE
                if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }

                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume();
                    msgAdvertencia("Este campo solo permite letras.");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (txtNombre.getText().length() >= 49) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 50 + " caracteres.");
                }
            }

        });

        //Validacion de Apellido que permite letras
        txtApellido.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                // Permitir teclas de control: SHIFT, BLOQ MAYÚS, BACKSPACE y DELETE
                if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }

                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume();
                    msgAdvertencia("Este campo solo permite letras.");
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                if (txtApellido.getText().length() >= 49) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 50 + " caracteres.");
                }
            }

        });

        //Validacion de DNI que permite números
        txtDni.addKeyListener(new KeyAdapter() {
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
                if (txtDni.getText().length() >= 8) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 8 + " caracteres.");
                }
            }
        });

        //AVISOS AL HACER CLICK
        txtDni.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!mensajeMostrado1) {
                    mensajeMostrado1 = true;
                    // Se ejecuta cuando el usuario hace clic en el campo por primera vez
                    JOptionPane.showMessageDialog(null, "Ingrese solo números, no espacios ni guiones (solo números, máx. 8 caracteres).",
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
        //txtNombre validar
        String data = txtNombre.getText().trim(); //El.trim elemina espacios al final e inicio
        int validador = 0;
        if (data.isEmpty()) {
            //Ventana emergente
            JOptionPane.showMessageDialog(this, "El campo 'Legajo' no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
        } else {
            //txtApellido validar
            data = txtApellido.getText().trim();
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo 'Nombre' no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
            } else {
                //txtDNI validar
                data = txtDni.getText().trim();
                if (data.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El campo 'Apellido' no puede estar vacio", "Error de Validacion", JOptionPane.WARNING_MESSAGE);
                } else {
                    validador = 1;
                }

            }

        }
        return validador;
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Zilla Slab SemiBold", 1, 30)); // NOI18N
        jLabel1.setText("Editar Alumno");

        jLabel3.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel4.setText("Apellido:");

        jLabel5.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 12)); // NOI18N
        jLabel5.setText("DNI:");

        btnEditar.setText("Editar Alumno");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtApellido)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (validacionVacio() != 1) {
            JOptionPane.showMessageDialog(this, "No puede dejar espacios en vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            EditarAlumnoController.editarAlumno();
            JOptionPane.showMessageDialog(this, "Modificación del Alumno realizada con éxito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            EditarAlumnoController.ocultar();
            EditarCalificacionController.mostrar();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public void setTxtDni(JTextField txtDni) {
        this.txtDni = txtDni;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
