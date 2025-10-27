/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.*;
import java.util.ArrayList;
import model.Materia;

/**
 *
 * @author ignac
 */
public class MateriaDAO {

    private final Connection c;

    public MateriaDAO(Connection c) {
        this.c = c;
    }

    public int addMateria(Materia materia) {
        String sql = "INSERT INTO materia (nombre,estado,alumnoId) values (?,?,?)";
        int idMateria = 0; //idGenerated

        try {
            PreparedStatement st = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, materia.getNombre());
            st.setString(2, materia.getEstado());
            st.setInt(3, materia.getAlumnoId());
            st.executeUpdate();
            System.out.println("Materia agregada correctamente.");

            ResultSet generateKey = st.getGeneratedKeys();
            if (generateKey.next()) {
                idMateria = generateKey.getInt(1);
                System.out.println("ID de la Materia almacenado.");
            } else {
                throw new Exception("No se genero ningun ID de Materia.");
            }

        } catch (Exception e) {
            System.out.println("Error al agregar la Materia: " + e.getMessage());
        }

        return idMateria;
    }

    public void updateMateria(Materia materia) {
        String sql = "UPDATE materia SET nombre=?,estado=? WHERE idMateria = ?";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, materia.getNombre());
            st.setString(2, materia.getEstado());
            st.setInt(3, materia.getIdMateria());
            st.executeUpdate();
            System.out.println("Modificación de Materia realizada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al modificar Materia: " + e.getMessage());
        }
    }

    public static ArrayList<Materia> listMateriaByLegajo(int legajo, Connection c) {
        ArrayList<Materia> lista = new ArrayList<>();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM materia WHERE alumnoId = " + legajo);

            while (rs.next()) {
                Materia mat = new Materia();

                mat.setIdMateria(rs.getInt("idMateria"));
                mat.setNombre(rs.getString("nombre"));
                mat.setEstado(rs.getString("estado"));
                mat.setAlumnoId(legajo);
                mat.setNotas(NotaDAO.listNotasByMateria(rs.getInt("idMateria"), c));

                lista.add(mat);
            }

        } catch (SQLException e) {
            System.out.println("Error al traer la Lista de Materias por Legajo: " + e.getMessage());
        }

        return lista;
    }

    public Materia findMateria(int idMateria) {
        Materia materia = new Materia();
        String sql = "SELECT * FROM materia WHERE idMateria = ?";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, idMateria);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                materia.setIdMateria(idMateria);
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getString("estado"));
                materia.setAlumnoId(rs.getInt("alumnoId"));
                materia.setNotas(NotaDAO.listNotasByMateria(idMateria, c));
            } else {
                throw new Exception("No se encontro Materia con ese Id: " + idMateria);
            }

        } catch (Exception e) {
            System.out.println("Error al encontrar Materia: " + e.getMessage());
        }

        return materia;
    }

    public Materia findMateriaByNombre(int legajo, String nombre) {
        Materia materia = new Materia();
        String sql = "SELECT * FROM materia WHERE alumnoId = ? AND nombre = ?";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, legajo);
            st.setString(2, nombre.trim());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(nombre);
                materia.setEstado(rs.getString("estado"));
                materia.setAlumnoId(legajo);
                return materia;
            } else {
                throw new Exception("No se encontro Materia con ese Legajo y Nombre");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return materia;
    }

    public boolean isUnique(String asignatura) {
        String sql = "SELECT * FROM materia WHERE nombre = ?";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, asignatura);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                throw new Exception("No se encontro materia con esa asignatura");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    public void deleteMateria(Materia materia) {
        String sql = "DELETE FROM materia WHERE idMateria = " + materia.getIdMateria();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            System.out.println("Materia eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eiminar Materia: " + e.getMessage());
        }
    }
}
