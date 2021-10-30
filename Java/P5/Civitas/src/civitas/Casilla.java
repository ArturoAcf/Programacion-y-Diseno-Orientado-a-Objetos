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
public class Casilla { 
    private String nombre;
            
    Casilla(String nombre) {  // Casilla genérica
        init();
        this.nombre = nombre;
    }
    
    public String getNombre(){  
        return nombre;
    }
    
    public void recibeJugador(int actual, ArrayList<Jugador> todos){
        informe(actual, todos);
    }
    
    private void init(){
        nombre="Casilla sin nombre\n";
    }
    
    void informe(int actual, ArrayList<Jugador> todos){   
        if(this.jugadorCorrecto(actual, todos)){
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre()+" ha caido en la casilla "+this.getNombre()+"\n");
        }
    }
   
    @Override
    public String toString(){
        return "";
    }
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        boolean aux=false;
        
        if(actual>=0 && actual<todos.size()){
            aux=true;
        }
        
        return aux;
    }
}