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
public class SorpresaIrCarcel extends Sorpresa{
    private Tablero tablero;
    
    SorpresaIrCarcel(Tablero tablero){
        super("Vas a la carcel\n");
        this.tablero=tablero;
    }
    
    @Override 
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(actual, todos)){
            todos.get(actual).encarcelar(tablero.getCarcel()); 
        }
    }
    
    @Override
    public String toString(){
        return "Sorpresa: "+super.getTexto()+"\n";
    }
}
