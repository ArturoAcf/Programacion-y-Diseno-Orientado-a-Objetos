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
public class CasillaSorpresa extends Casilla{
    private mazoSorpresas mazo=new mazoSorpresas();
    
    CasillaSorpresa(mazoSorpresas mazo, String nombre){
        super(nombre);
        this.mazo=mazo;
    }
    
    @Override
    public void recibeJugador(int actual, ArrayList<Jugador> todos){
         if(jugadorCorrecto(actual, todos)==true){
             Sorpresa s=mazo.siguiente();
             informe(actual, todos);
             s.aplicarAJugador(actual, todos);
         }
     }
}
