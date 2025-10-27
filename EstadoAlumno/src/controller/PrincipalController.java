/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.VistaPrincipal;

/**
 *
 * @author ignac
 */
public class PrincipalController {
    
    public static VistaPrincipal vista = new VistaPrincipal();
    public static void mostrar(){vista.setVisible(true);};
    public static void ocultar(){vista.setVisible(false);};
    
    public static void btnAgregar(){
        ocultar();
        AlumnoController.mostrar();
    }
    
    public static void btnListar(){
        ocultar();
        ListaAlumnoController.mostrar();
    }
    
    public static void btnCalificacion(){
        ocultar();
        CalificacionController.mostrar();
    }
}
