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
public class CasillaImpuesto extends Casilla{
    private float cantidad;
    
    CasillaImpuesto(float cantidad, String nombre){
        super(nombre);
        this.cantidad=cantidad;
    }
    
    @Override
    public void recibeJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).pagaImpuesto(cantidad);
        }
    }
}
