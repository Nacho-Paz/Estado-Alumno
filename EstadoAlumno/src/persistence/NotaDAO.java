/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Nota;

/**
 *
 * @author ignac
 */
public class NotaDAO {

    private Connection c;

    public NotaDAO(Connection c) {
        this.c = c;
    }

    public void addNota(Nota nota) {
        String sql = "INSERT INTO nota (nota,materiaId) values (?,?)";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, nota.getNota());
            st.setInt(2, nota.getMateriaId());
            st.executeUpdate();
            System.out.println("Nota agregada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al agregar la Nota: " + e.getMessage());
        }
    }

    public void updateNota(Nota nota) {
        String sql = "UPDATE nota SET nota=? WHERE materiaId=?";
        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, nota.getNota());
            st.setInt(2, nota.getMateriaId());
            st.executeUpdate();
            System.out.println("Modificación de Nota realizada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al modificar Nota: " + e.getMessage());
        }
    }

    public static ArrayList<Nota> listNotasByMateria(int materiaId, Connection c) {
        ArrayList<Nota> lista = new ArrayList<>();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM nota WHERE materiaId = " + materiaId);

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setIdNota(rs.getInt("idNota"));
                nota.setNota(rs.getInt("nota"));
                nota.setMateriaId(materiaId);

                lista.add(nota);
            }

        } catch (SQLException e) {
            System.out.println("Error al Listar Notas por Materia: " + e.getMessage());
        }

        return lista;
    }

    public Nota findNota(int idNota) {
        Nota nota = new Nota();
        String sql = "SELECT * FROM nota WHERE idNota = ?";

        try {
            PreparedStatement st = c.prepareStatement(sql);
            st.setInt(1, idNota);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                nota.setIdNota(idNota);
                nota.setNota(rs.getInt("nota"));
                nota.setMateriaId(rs.getInt("materiaId"));
            } else {
                throw new Exception("No se encontro Nota con ese Id: " + idNota);
            }
        } catch (Exception e) {
            System.out.println("Error al encontrar la Nota: " + e.getMessage());
        }

        return nota;
    }
}
