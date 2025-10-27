/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.*;
import java.util.ArrayList;
import model.Alumno;

/**
 *
 * @author ignac
 */
public class AlumnoDAO {

    private final Connection c;

    public AlumnoDAO(Connection c) {
        this.c = c;
    }

    public void addAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (legajo,nombre,apellido,dni) values (?,?,?,?)";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, alumno.getLegajo());
            st.setString(2, alumno.getNombre());
            st.setString(3, alumno.getApellido());
            st.setInt(4, alumno.getDni());
            st.executeUpdate();
            System.out.println("Alumno agregado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar Alumno: " + e.getMessage());
        }
    }

    public void deleteAlumno(int legajo) {
        String sql = "DELETE FROM alumno WHERE legajo = " + legajo;
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            System.out.println("Alumno eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar Alumno: " + e.getMessage());
        }
    }

    public void updateAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET nombre=?,apellido=?,dni=? WHERE legajo = ?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setString(1, alumno.getNombre());
            st.setString(2, alumno.getApellido());
            st.setInt(3, alumno.getDni());
            st.setInt(4, alumno.getLegajo());
            st.executeUpdate();
            System.out.println("Modificación de Alumno realizada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al realizar la modificación: " + e.getMessage());
        }
    }

    public ArrayList<Alumno> listAlumnos() {
        ArrayList<Alumno> lista = new ArrayList<>();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM alumno ORDER BY apellido");

            while (rs.next()) {
                Alumno alu = new Alumno();

                alu.setLegajo(rs.getInt("legajo"));
                alu.setNombre(rs.getString("nombre"));
                alu.setApellido(rs.getString("apellido"));
                alu.setDni(rs.getInt("dni"));
                alu.setMaterias(MateriaDAO.listMateriaByLegajo(rs.getInt("legajo"), c));

                lista.add(alu);
            }
        } catch (SQLException e) {
            System.out.println("Error al traer Alumnos: " + e.getMessage());
        }
        return lista;
    }

    public Alumno findAlumno(int legajo) {
        Alumno alumno = new Alumno();
        String sql = "SELECT * FROM alumno WHERE legajo = ?";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, legajo);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                alumno.setLegajo(legajo);
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setMaterias(MateriaDAO.listMateriaByLegajo(legajo, c));
            } else {
                throw new Exception("No se encontro Alumno con ese Legajo: " + legajo);
            }

        } catch (Exception e) {
            System.out.println("Error al encontrar el Alumno: " + e.getMessage());
        }

        return alumno;
    }

    public boolean isExist(int legajo){
        String sql = "SELECT * FROM alumno WHERE legajo = ?";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, legajo);
            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al buscar Alumno: " + e.getMessage());
        }
        
        return false;
    }

}
