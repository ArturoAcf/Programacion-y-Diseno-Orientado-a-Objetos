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
public class SorpresaPorJugador extends Sorpresa{
    private int valor;
    
    SorpresaPorJugador(int cantidad, String texto){
        super(texto);
        this.valor=cantidad;
    }
    
    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(this.jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            int valor_scobrar=this.valor*-1;
            int valor_srecibe=this.valor*3;
            Sorpresa s=new SorpresaPagarCobrar(valor_scobrar, "cobrar\n");
            
            for(int i=0; i<todos.size(); i++){
                if(i!=actual){
                  s.aplicarAJugador(i, todos);
                } 
            }
            
            Sorpresa s2=new SorpresaPagarCobrar(valor_srecibe, "recibir\n");
            s2.aplicarAJugador(actual, todos);
        }
    }
}

