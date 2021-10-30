/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegoTexto;

import civitas.Dado;
import civitas.civitasJuego;
import java.util.ArrayList;

// Nombre -> Arturo Alonso Carbonero  DNI -> 75936665-A
// Grupo -> 2ºC - C2
//
// NOTA -> En principio funciona correctamente, pero es posible que haya errores que no haya detectado
//         ya que no he comprobado el total de los casos posibles.

/**
 *
 * @author arturo
 */
public class JuegoTexto{  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VistaTextual vista=new VistaTextual();
        ArrayList<String> nombres=new ArrayList<>();
        
        nombres.add("Arturo");
        nombres.add("Felipe");
        nombres.add("Javi");
        nombres.add("Dani");
        
        civitasJuego juego=new civitasJuego(nombres);
        Dado.getInstance().setDebug(true); 
        
        Controlador control=new Controlador(juego,vista);
        control.juega();
    }
}
