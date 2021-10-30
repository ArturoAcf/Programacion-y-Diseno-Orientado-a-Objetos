/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author arturo
 */
public class CasillaJuez extends Casilla{
    private static int numCasillaCarcel;
    
    CasillaJuez(int numCasillaCarcel, String nombre){
        super(nombre);
        this.numCasillaCarcel=numCasillaCarcel;
    }
    
    @Override
    public void recibeJugador(int actual, ArrayList<Jugador> todos){ 
        if(jugadorCorrecto(actual, todos)==true){
            this.informe(actual, todos);
            todos.get(actual).encarcelar(numCasillaCarcel); 
        }
    }
    
}