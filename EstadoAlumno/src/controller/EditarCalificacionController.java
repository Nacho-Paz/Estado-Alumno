/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.VistaEditarCalificacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Materia;
import model.Nota;
import persistence.ConnectionDB;
import persistence.MateriaDAO;
import persistence.NotaDAO;

/**
 *
 * @author ignac
 */
public class EditarCalificacionController {

    public static VistaEditarCalificacion vista = new VistaEditarCalificacion();
    private static int idMateria;

    public static void ocultar() {
        vista.setVisible(false);
    }

    public static void mostrar() {
        vista.setVisible(true);
        vista.getTxtMateria().setText("Edición de " + EditarAlumnoController.materia);

        try {
            Connection c = ConnectionDB.conect();
            MateriaDAO matDAO = new MateriaDAO(c);
            NotaDAO notDAO = new NotaDAO(c);

            Materia materia = matDAO.findMateriaByNombre(EditarAlumnoController.codigo, EditarAlumnoController.materia);
            idMateria = materia.getIdMateria();
            ArrayList<Nota> notas = NotaDAO.listNotasByMateria(materia.getIdMateria(), c);

            vista.getTxt1P().setText(String.valueOf(notas.get(0).getNota()));
            vista.getTxt2P().setText(String.valueOf(notas.get(1).getNota()));
            vista.getTxt1R().setText(String.valueOf(notas.get(2).getNota()));
            vista.getTxt2R().setText(String.valueOf(notas.get(3).getNota()));

        } catch (SQLException e) {
            System.out.println("Error al mostrar la Edición de Calificación: " + e.getMessage());
        }

    }

    public static void btnVolver() {
        ocultar();
        EditarAlumnoController.mostrar(EditarAlumnoController.codigo, EditarAlumnoController.materia);
    }

    public static void editarCalificacion() {
        int p1 = Integer.parseInt(vista.getTxt1P().getText().trim());
        int p2 = Integer.parseInt(vista.getTxt2P().getText().trim());
        int r1 = Integer.parseInt(vista.getTxt1R().getText().trim());
        int r2 = Integer.parseInt(vista.getTxt2R().getText().trim());
        List<Integer> notas = new ArrayList<>(Arrays.asList(p1, p2, r1, r2));

        try {
            Connection c = ConnectionDB.conect();
            MateriaDAO matDAO = new MateriaDAO(c);
            NotaDAO notDAO = new NotaDAO(c);

            Nota nota1 = new Nota(p1, idMateria);
            Nota nota2 = new Nota(p1, idMateria);
            Nota nota3 = new Nota(p1, idMateria);
            Nota nota4 = new Nota(p1, idMateria);

            notDAO.updateNota(nota1);
            notDAO.updateNota(nota2);
            notDAO.updateNota(nota3);
            notDAO.updateNota(nota4);

        } catch (SQLException e) {
            System.out.println("Error al vincular las Notas: " + e.getMessage());
        }
    }
}
