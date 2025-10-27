/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.*;
import model.Materia;
import model.Nota;
import persistence.AlumnoDAO;
import persistence.ConnectionDB;
import persistence.MateriaDAO;
import persistence.NotaDAO;
import view.VistaCalificacion;

/**
 *
 * @author ignac
 */
public class CalificacionController {

    public static VistaCalificacion vista = new VistaCalificacion();

    public static void mostrar() {
        vista.limpiarCampos();
        vista.setVisible(true);
    }

    public static void ocultar() {
        vista.setVisible(false);
    }

    public static void btnVolver() {
        ocultar();
        PrincipalController.mostrar();
    }

    public static String addCalificacion() {
        int legajo = Integer.parseInt(vista.getTxtLegajo().getText().trim());
        String asignatura = (String) vista.getCbxMateria().getSelectedItem();
        int p1 = Integer.parseInt(vista.getTxt1P().getText().trim());
        int p2 = Integer.parseInt(vista.getTxt2P().getText().trim());
        int r1 = Integer.parseInt(vista.getTxt1R().getText().trim());
        int r2 = Integer.parseInt(vista.getTxt2R().getText().trim());
        List<Integer> notas = new ArrayList<>(Arrays.asList(p1, p2, r1, r2));

        try {
            Connection c = ConnectionDB.conect();
            AlumnoDAO alDAO = new AlumnoDAO(c);
            MateriaDAO matDAO = new MateriaDAO(c);
            NotaDAO notDAO = new NotaDAO(c);

            if (alDAO.isExist(legajo)) {
                Materia materia = new Materia();
                String estado = materia.calcularEstado(notas);
                materia.setAlumnoId(legajo);
                materia.setNombre(asignatura);
                materia.setEstado(estado);
                if (matDAO.isUnique(asignatura)) {
                    return "La asignatura: " + asignatura + " se encuentra ya cargada";
                } else {
                    int idMateria = matDAO.addMateria(materia);
                    if (idMateria == 0) {
                        return "Error al generar la ID de Materia.";
                    } else {
                        Nota notaP1 = new Nota(p1, idMateria);
                        Nota notaP2 = new Nota(p2, idMateria);
                        Nota notaR1 = new Nota(r1, idMateria);
                        Nota notaR2 = new Nota(r2, idMateria);
                        notDAO.addNota(notaP1);
                        notDAO.addNota(notaP2);
                        notDAO.addNota(notaR1);
                        notDAO.addNota(notaR2);

                        System.out.println("Alumno completamente agregado.");
                    }
                }
            } else {
                return "El legajo ingresado no existe.";
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return "Calificación cargada con éxito.";
    }
}
