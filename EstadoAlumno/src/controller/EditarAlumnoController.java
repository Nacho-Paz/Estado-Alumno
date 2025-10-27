/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Alumno;
import java.sql.*;
import persistence.AlumnoDAO;
import persistence.ConnectionDB;
import view.VistaEditarAlumno;

/**
 *
 * @author ignac
 */
public class EditarAlumnoController {

    public static VistaEditarAlumno vista = new VistaEditarAlumno();
    public static int codigo;
    public static String materia;

    public static void ocultar() {
        vista.setVisible(false);
    }

    public static void mostrar(int legajo, String asignatura) {
        ListaAlumnoController.ocultar();
        codigo = legajo;
        materia = asignatura;
        vista.setVisible(true);

        try {
            Connection c = ConnectionDB.conect();
            AlumnoDAO alDAO = new AlumnoDAO(c);
            Alumno alumno = alDAO.findAlumno(legajo);

            vista.getTxtNombre().setText(alumno.getNombre());
            vista.getTxtApellido().setText(alumno.getApellido());
            vista.getTxtDni().setText(String.valueOf(alumno.getDni()));

        } catch (SQLException e) {
            System.out.println("Error al mostrar el alumno a editar: " + e.getMessage());
        }
    }

    public static void editarAlumno() {
        String nombre = vista.getTxtNombre().getText().toUpperCase().trim();
        String apellido = vista.getTxtApellido().getText().toUpperCase().trim();
        int dni = Integer.parseInt(vista.getTxtDni().getText().toUpperCase().trim());

        try {
            Connection c = ConnectionDB.conect();
            AlumnoDAO alDAO = new AlumnoDAO(c);

            if (alDAO.isExist(codigo)) {
                Alumno alumno = new Alumno(codigo, nombre, apellido, dni);
                alDAO.updateAlumno(alumno);
            } else {
                throw new Exception("Error al vincular el Legajo.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void btnVolver() {
        ocultar();
        ListaAlumnoController.mostrar();
    }

}
