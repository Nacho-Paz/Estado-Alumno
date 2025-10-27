/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author ignac
 */
public class Materia {

    private int idMateria;
    private String nombre;
    private String estado;
    private int alumnoId;
    private List<Nota> notas;

    public Materia() {
    }

    public Materia(int idMateria, String nombre, String estado, int alumnoId, List<Nota> notas) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.estado = estado;
        this.alumnoId = alumnoId;
        this.notas = notas;
    }

    public Materia(String nombre, String estado, int alumnoId) {
        this.nombre = nombre;
        this.estado = estado;
        this.alumnoId = alumnoId;
    }
    
    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public String calcularEstado(List<Integer> notas) {
        int nota1 = -1, nota2 = -1, recu1 = -1, recu2 = -1;

        // Asignar valores según la posición de la nota
        for (int i = 0; i < notas.size(); i++) {
            switch (i) {
                case 0 ->
                    nota1 = notas.get(i);
                case 1 ->
                    nota2 = notas.get(i);
                case 2 ->
                    recu1 = notas.get(i);
                case 3 ->
                    recu2 = notas.get(i);
            }
        }

        // Si los parciales son >=7, promociona sin necesidad de recuperación
        if (nota1 >= 7 && nota2 >= 7) {
            return "Aprobacion Directa";
        }

        // Si ambos parciales son <=3, solo puede regularizar con recuperación
        if (nota1 <= 3 && nota2 <= 3) {
            if (recu1 <= 3 || recu2 <= 3) {
                return "Libre"; // Si en la recuperación sigue con <=3, queda libre
            }
            return "Regular"; // Si al menos una recuperación es >= 4, regulariza
        }

        // Si uno de los parciales está entre 4 y 6, puede recuperar para intentar promoción
        if ((nota1 >= 4 && nota1 < 7) || (nota2 >= 4 && nota2 < 7)) {
            // Si con recuperaciones logra >=7 en ambos, promociona
            if ((recu1 >= 7 || nota1 >= 7) && (recu2 >= 7 || nota2 >= 7)) {
                return "Aprobacion Directa";
            }
            // Si al menos logra >=4 en alguna nota (incluyendo recuperaciones), regulariza
            if ((recu1 >= 4 || nota1 >= 4) && (recu2 >= 4 || nota2 >= 4)) {
                return "Regular";
            }
        }

        // Si después de todas las instancias no logró ni promoción ni regularidad, queda libre
        return "Libre";
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", estado=" + estado + ", alumnoId=" + alumnoId + ", notas=" + notas + '}';
    }

}
