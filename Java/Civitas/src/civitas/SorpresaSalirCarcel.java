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
public class SorpresaSalirCarcel extends Sorpresa{
    private mazoSorpresas mazo;
    
    SorpresaSalirCarcel(mazoSorpresas mazo, String texto){
        super(texto);
        this.mazo=mazo;
    }
    
    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)==true){
        informe(actual, todos);
             boolean salvoconducto=false;
            for(int i=0; i<3; i++){
                if(todos.get(i).tieneSalvoconducto()==true){
                    salvoconducto=true;
                }
            }
            if(salvoconducto==false){
                todos.get(actual).obtenerSalvoconducto(this);
                this.salirDelMazo();
            }
        }
    }
    
    void salirDelMazo(){
        mazo.inhabilitarCartaEspecial(this);
    }
    
    void Usada(){
        mazo.habilitarCartaEspecial(this);
    }
    
    @Override
    public String toString(){
        return "Sorpresa: "+super.getTexto()+"\nSales de la carcel\n";
    }
}
