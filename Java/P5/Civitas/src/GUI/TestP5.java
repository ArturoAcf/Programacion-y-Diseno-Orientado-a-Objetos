/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import civitas.civitasJuego;

/**
 *
 * @author arturo
 */
public class TestP5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CivitasView civitas=new CivitasView();
        Dado.createInstance(civitas);
        Dado.getInstance().setDebug(false); // Si debug = true -> Valor = 1
        
        CapturaNombres capturaNombres=new CapturaNombres(civitas, true);
        
        ArrayList<String> nombres=new ArrayList<>();
        nombres=capturaNombres.getNombres();
        
        civitasJuego juego=new civitasJuego(nombres);
        Controlador controlador=new Controlador(juego, civitas);
        
        civitas.setCivitasJuego(juego);
        
        controlador.juega();
    }
    
}
