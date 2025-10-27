/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.EditarAlumnoController;
import controller.ListaAlumnoController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author ignac
 */
public class VistaListaAlumno extends javax.swing.JFrame {
    
    private JMenuItem mnIVolver;
    private final String colorBase = "#480866";
    private final String colorHover = "#7f4c97";
    private final String colorClickPr = "#be34ff";
    private boolean mensajeMostrado = false;
    public static int cod = 0;
    public static String materia = "";
    
    public VistaListaAlumno() {
        initComponents();
        //Con este codigo logro que el focus no este en el txtBuscar sino en el jPanel al ingresar, así no me salte el jOption de mensaje
        SwingUtilities.invokeLater(() -> jPanel1.requestFocusInWindow());
        personalizarComponentes();
        validaciones();
    }
    
    private void personalizarComponentes() {
        this.setSize(950, 550); // Establece el tamaño de la ventana
        this.setDefaultCloseOperation(VistaListaAlumno.EXIT_ON_CLOSE);
        this.setTitle("Listado de Alumnos");
        this.setLocationRelativeTo(null);
        menuBarConfig();
        
        tablaAlumnos.addMouseListener(new MouseAdapter() {
            @Override //METODO PARA OBTENER ID DEL ALUMNO
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tablaAlumnos.getSelectedRow(); // Obtener la fila seleccionada
                if (filaSeleccionada != -1) { // Verificar que hay una fila seleccionada
                    cod = (int) tablaAlumnos.getValueAt(filaSeleccionada, 0); // Obtener el idLibro (columna 0)
                    materia = (String) tablaAlumnos.getValueAt(filaSeleccionada, 4);
                }
            }
        });
    }
    
    private void menuBarConfig() {
        mnIVolver = new JMenuItem("Volver");

        //AGREGO EVENTOS
        mnIVolver.addActionListener((ActionEvent e) -> {
            ListaAlumnoController.btnVolver();
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
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();

                // Permitir teclas de control: SHIFT, BLOQ MAYÚS, BACKSPACE y DELETE
                if (e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyCode() == KeyEvent.VK_CAPS_LOCK
                        || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
                    return;
                }
                
                if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                    e.consume();
                    msgAdvertencia("Este campo solo permite letras y números.");
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtBuscar.getText().length() >= 49) {
                    e.consume(); // Bloquear entrada si se supera la longitud máxima
                    msgAdvertencia("Este campo solo permite hasta " + 50 + " caracteres.");
                }
            }
            
        });
        
        txtBuscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!mensajeMostrado) {
                    mensajeMostrado = true;
                    // Se ejecuta cuando el usuario hace clic en el campo por primera vez
                    JOptionPane.showMessageDialog(null, "Lo ingresado se buscará en:\n- Legajo\n- Apellido\n- Nombre\n- DNI",
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
    
    private boolean isVacio() {
        String data = txtBuscar.getText().trim();
        return !data.isEmpty();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Zilla Slab SemiBold", 1, 30)); // NOI18N
        jLabel1.setText("Listado de Alumno");

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Legajo", "Apellido", "Nombre", "DNI", "Materia", "Estado", "1° P", "2° P", "1° R", "2° R"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);
        if (tablaAlumnos.getColumnModel().getColumnCount() > 0) {
            tablaAlumnos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tablaAlumnos.getColumnModel().getColumn(0).setMaxWidth(80);
            tablaAlumnos.getColumnModel().getColumn(3).setPreferredWidth(90);
            tablaAlumnos.getColumnModel().getColumn(3).setMaxWidth(90);
            tablaAlumnos.getColumnModel().getColumn(6).setPreferredWidth(50);
            tablaAlumnos.getColumnModel().getColumn(6).setMaxWidth(50);
            tablaAlumnos.getColumnModel().getColumn(7).setPreferredWidth(50);
            tablaAlumnos.getColumnModel().getColumn(7).setMaxWidth(50);
            tablaAlumnos.getColumnModel().getColumn(8).setPreferredWidth(50);
            tablaAlumnos.getColumnModel().getColumn(8).setMaxWidth(50);
            tablaAlumnos.getColumnModel().getColumn(9).setPreferredWidth(50);
            tablaAlumnos.getColumnModel().getColumn(9).setMaxWidth(50);
        }

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 18)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Zilla Slab SemiBold", 0, 18)); // NOI18N
        btnEliminar.setText("Eliminar Alumno");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(btnEditar)
                        .addGap(172, 172, 172)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnEliminar))
                .addContainerGap(47, Short.MAX_VALUE))
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
        if (cod == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Alumno para Modificar", "Error de Selección", JOptionPane.WARNING_MESSAGE);
        } else {
            EditarAlumnoController.mostrar(cod, materia);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (!isVacio()) {
            ListaAlumnoController.mostrar();
            msgAdvertencia("Debe ingresar correctamente el Alumno, no se permite vacíos.");
        } else if (!ListaAlumnoController.btnBuscar(txtBuscar.getText().toUpperCase().trim())) {
            ListaAlumnoController.mostrar();
            msgAdvertencia("El Alumno ingresado no se encontró.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (cod == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Alumno para Eliminar", "Error de Selección", JOptionPane.WARNING_MESSAGE);
        } else {
            ListaAlumnoController.btnEliminar(cod, materia);
            JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        if (!isVacio()) {
            ListaAlumnoController.mostrar();
            msgAdvertencia("Debe ingresar correctamente el Alumno, no se permite vacíos.");
        } else if (!ListaAlumnoController.btnBuscar(materia)) {
            ListaAlumnoController.mostrar();
            msgAdvertencia("El Alumno no se encuentra.");
        }
    }//GEN-LAST:event_txtBuscarActionPerformed
    
    public JTable getTablaAlumnos() {
        return tablaAlumnos;
    }
    
    public void setTablaAlumnos(JTable tablaAlumnos) {
        this.tablaAlumnos = tablaAlumnos;
    }
    
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }
    
    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
