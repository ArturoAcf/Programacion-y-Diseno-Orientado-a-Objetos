/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author arturo
 */
public class mazoSorpresas {
    private ArrayList<Sorpresa> sorpresas; 
    private boolean barajada; 
    private int usadas; 
    private boolean Debug; 
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa; 
    
    private void init(){
        barajada=false;
        usadas=0;
        sorpresas=new ArrayList<>();
        cartasEspeciales=new ArrayList<>();
    }
    
    mazoSorpresas(){
        Debug=false;
        this.init();
    }
    
    mazoSorpresas(boolean d){
          String event="Debug activado";
          Debug=d;
          this.init();
    }
    
    void alMazo(Sorpresa s){
        if(!barajada){
            sorpresas.add(s);
        }
    }
    
    int getTamMazo(){
        return sorpresas.size();
    }
    
    Sorpresa getSorpresas(int i){
        return sorpresas.get(i);
    }

    Sorpresa siguiente(){
        boolean aux=false;
        
        if(!barajada || usadas==sorpresas.size()){
            if(!Debug){
                usadas=0;
                Collections.shuffle(sorpresas);
                barajada=true;
                aux=true; 
            }
        }
        
        usadas++;
        ultimaSorpresa=sorpresas.get(0);
        sorpresas.add(ultimaSorpresa);
        sorpresas.remove(0);
        
        return ultimaSorpresa;
    }
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        boolean aux=false;
        
        for(int i=0; i<sorpresas.size(); i++){
            if(sorpresas.contains(sorpresa)){
                sorpresas.remove(i);
                cartasEspeciales.add(sorpresa);
                aux=true;
            }
        }
        
        aux=false;
    }
    
    void habilitarCartaEspecial(Sorpresa sorpresa){
        boolean aux=false;
        boolean aux2=false;
        
        for(int i=0; i<cartasEspeciales.size(); i++){
            if(cartasEspeciales.contains(sorpresa)){
                cartasEspeciales.remove(i);
                sorpresas.add(sorpresa);
                Diario.getInstance().ocurreEvento("Carta especial habilitada");
            }
        }
    }
}
