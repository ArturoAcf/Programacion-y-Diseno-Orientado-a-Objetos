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
public class SorpresaIrACasilla extends Sorpresa{
    private Tablero tablero;
    private int numCasilla;
    
    SorpresaIrACasilla(Tablero tablero, int numCasilla, String texto){
        super(texto);
        this.tablero=tablero;
        this.numCasilla=numCasilla;
    }
    
    @Override 
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            
            int casilla_actual=todos.get(actual).getNumCasillaActual();
            int casilla=(int)(Math.random()*20+1);
            int nueva_pos=tablero.nuevaPosicion(casilla_actual, casilla);
            
            todos.get(actual).moverACasilla(nueva_pos);
            tablero.getCasilla(nueva_pos).recibeJugador(actual, todos);
        }
    }
    
    @Override
    public String toString(){
        return "Sorpresa: "+super.getTexto()+"\nVas a la casilla: "+numCasilla+"\n";
    }
}
