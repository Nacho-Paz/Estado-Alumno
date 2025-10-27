/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Alumno;
import model.Materia;
import model.Nota;
import persistence.AlumnoDAO;
import persistence.ConnectionDB;
import persistence.MateriaDAO;
import persistence.NotaDAO;
import view.VistaListaAlumno;

/**
 *
 * @author ignac
 */
public class ListaAlumnoController {

    public static VistaListaAlumno vista = new VistaListaAlumno();

    public static void ocultar() {
        vista.setVisible(false);
    }

    public static void mostrar() {
        VistaListaAlumno.cod = 0;
        vista.setVisible(true);

        DefaultTableModel model = (DefaultTableModel) vista.getTablaAlumnos().getModel();
        model.setNumRows(0);

        try {
            Connection c = ConnectionDB.conect();
            AlumnoDAO alDAO = new AlumnoDAO(c);
            MateriaDAO matDAO = new MateriaDAO(c);
            NotaDAO notDAO = new NotaDAO(c);

            ArrayList<Alumno> listaAlumnos = alDAO.listAlumnos();

            int legajoAnt = -1; //Para evitar que se repita el ALUMNO
            for (Alumno alumno : listaAlumnos) {
                Object[] fila = new Object[10];

                ArrayList<Materia> listaMaterias = MateriaDAO.listMateriaByLegajo(alumno.getLegajo(), c);

                //Compruebo que no se repita comparando legajos
                if (legajoAnt != alumno.getLegajo()) {
                    //Como la materia no puede estar repetida, no es necesario la anterior validación
                    for (Materia materia : listaMaterias) {
                        ArrayList<Nota> listaNotas = NotaDAO.listNotasByMateria(materia.getIdMateria(), c);

                        fila[0] = alumno.getLegajo();
                        fila[1] = alumno.getNombre();
                        fila[2] = alumno.getApellido();
                        fila[3] = alumno.getDni();
                        fila[4] = materia.getNombre();
                        fila[5] = materia.getEstado();
                        fila[6] = listaNotas.get(0).getNota();
                        fila[7] = listaNotas.get(1).getNota();
                        fila[8] = listaNotas.get(2).getNota();
                        fila[9] = listaNotas.get(3).getNota();

                        model.addRow(fila);
                    }

                    legajoAnt = alumno.getLegajo();
                }

            }

        } catch (SQLException e) {
            System.out.println("Error :" + e.getMessage());
        }
    }

    public static void btnVolver() {
        ocultar();
        PrincipalController.mostrar();
    }

    public static boolean btnBuscar(String busqueda) {
        DefaultTableModel model = (DefaultTableModel) vista.getTablaAlumnos().getModel();
        model.setNumRows(0);
        int validadorHasLibro = 0;

        try {
            Connection c = ConnectionDB.conect();
            AlumnoDAO alDAO = new AlumnoDAO(c);
            MateriaDAO matDAO = new MateriaDAO(c);

            ArrayList<Alumno> alumnos = alDAO.listAlumnos();

            int legajoAnt = -1;
            for (Alumno alumno : alumnos) {
                Object[] fila = new Object[10];
                ArrayList<Materia> listaMaterias = MateriaDAO.listMateriaByLegajo(alumno.getLegajo(), c);

                if (alumno.getNombre().trim().toUpperCase().contains(busqueda)
                        || alumno.getApellido().trim().toUpperCase().contains(busqueda)
                        || String.valueOf(alumno.getDni()).contains(busqueda)
                        || String.valueOf(alumno.getLegajo()).contains(busqueda)) {

                    if (legajoAnt != alumno.getLegajo()) {
                        //Como la materia no puede estar repetida, no es necesario la anterior validación
                        for (Materia materia : listaMaterias) {
                            ArrayList<Nota> listaNotas = NotaDAO.listNotasByMateria(materia.getIdMateria(), c);

                            fila[0] = alumno.getLegajo();
                            fila[1] = alumno.getNombre();
                            fila[2] = alumno.getApellido();
                            fila[3] = alumno.getDni();
                            fila[4] = materia.getNombre();
                            fila[5] = materia.getEstado();
                            fila[6] = listaNotas.get(0).getNota();
                            fila[7] = listaNotas.get(1).getNota();
                            fila[8] = listaNotas.get(2).getNota();
                            fila[9] = listaNotas.get(3).getNota();

                            model.addRow(fila);
                            validadorHasLibro = 1;
                        }

                        legajoAnt = alumno.getLegajo();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return validadorHasLibro != 0;
    }

    public static void btnEliminar(int legajo, String materia) {
        try {
            Connection c = ConnectionDB.conect();
            MateriaDAO matDAO = new MateriaDAO(c);
            Materia mat = matDAO.findMateriaByNombre(legajo, materia);
            matDAO.deleteMateria(mat);
            System.out.println("Materia eliminada correctamente.");
            mostrar();
        } catch (SQLException ex) {
            System.out.println("Error al Eliminar Alumno: " + ex.getMessage());
        }
    }

}
