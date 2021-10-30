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
public class CasillaCalle extends Casilla{
    private tituloPropiedad titulo;
    private float importe;
    
    CasillaCalle(tituloPropiedad titulo){
        super(titulo.getNombre());
        this.titulo=titulo;
        importe=titulo.getPrecioCompra();
    }
    
    @Override
    public void recibeJugador(int actual, ArrayList<Jugador> todos){ 
        if(jugadorCorrecto(actual,todos)==true){
                informe(actual,todos);
                Jugador jugador=todos.get(actual);
            if(titulo.tienePropietario()==false){
                jugador.puedeComprarCasilla();
            }else{
                titulo.tramitarAlquiler(jugador);
            }
        }
    }
    
    tituloPropiedad getTituloPropiedad(){
        return titulo;
    }
    
    @Override
    public String toString(){
        return "Importe a pagar en esta casilla: "+this.importe+"\n";
    }
}