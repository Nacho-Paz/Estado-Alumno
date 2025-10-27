/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ignac
 */
public class Nota {

    private int idNota;
    private int nota;
    private int materiaId;

    public Nota() {
    }

    public Nota(int nota, int materiaId) {
        this.nota = nota;
        this.materiaId = materiaId;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    @Override
    public String toString() {
        return "Nota{" + "idNota=" + idNota + ", nota=" + nota + ", materiaId=" + materiaId + '}';
    }

}
