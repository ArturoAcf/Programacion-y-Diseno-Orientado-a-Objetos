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
public class SorpresaPorCasaHotel extends Sorpresa{
    private Tablero tablero;
    private int cantidad;
    
    SorpresaPorCasaHotel(Tablero tablero, int cantidad, String texto){
        super(texto);
        this.tablero=tablero;
        this.cantidad=cantidad;
    }
    
    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).modificarSaldo(this.cantidad*todos.get(actual).cantidadCasasHoteles()); 
        }
    }
    
    @Override
    public String toString(){
        return "Sorpresa: "+super.getTexto()+" Cantidad: "+this.cantidad+"\n";
    }
}