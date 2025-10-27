/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import persistence.AlumnoDAO;
import view.VistaAlumno;
import java.sql.*;
import java.util.ArrayList;
import model.Alumno;
import persistence.ConnectionDB;

/**
 *
 * @author ignac
 */
public class AlumnoController {

    public static VistaAlumno vista = new VistaAlumno();
    
    public static void ocultar() {
        vista.setVisible(false);
    }

    public static void mostrar() {
        vista.limpiarCampos();
        vista.setVisible(true);
    }

    public static void btnVolver() {
        ocultar();
        PrincipalController.mostrar();
    }

    public static String addAlumno() {
        int legajo = Integer.parseInt(vista.getTxtLegajo().getText().trim());
        String nombre = vista.getTxtNombre().getText().trim().toUpperCase();
        String apellido = vista.getTxtApellido().getText().trim().toUpperCase();
        int dni = Integer.parseInt(vista.getTxtDni().getText().trim());

        try {
            Connection c = ConnectionDB.conect();
            AlumnoDAO alDAO = new AlumnoDAO(c);

            Alumno alumno = new Alumno(legajo, nombre, apellido, dni);
            if (!isUnique(alDAO, legajo)) {
                return "El Legajo ingresado esta repetido.";
            } else if (!isUnique(alDAO, dni)) {
                return "El DNI ingresado esta repetido.";
            } else {
                alDAO.addAlumno(alumno);
            }

        } catch (Exception e) {
            System.out.println("Error al establecer AlumnoDAO: " + e.getMessage());
        }

        return "Alumno ingresado exitosamente.";
    }

    private static boolean isUnique(AlumnoDAO alDAO, int dato) throws Exception {
        try {
            ArrayList<Alumno> lista = alDAO.listAlumnos();

            if (lista.isEmpty()) {
                return true; //ya que es el primer dato no hay con que comparar
            }

            for (Alumno alumno : lista) {
                if (alumno.getLegajo() == dato || alumno.getDni() == dato) {
                    return false;
                }
            }
        } catch (Exception e) {
            throw new Exception("Error al comprar unicidad del dato.");
        }
        return true;
    }

}
