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
public class Tablero {  
    private int numCasillaCarcel;  
    private int porSalida;  
    private boolean tieneJuez;  
    private final ArrayList<Casilla> casillas;  
    
    Tablero(int numCasillaCarcel){  
        porSalida=0;
        tieneJuez=false;
        
        if(numCasillaCarcel >= 1){
            this.numCasillaCarcel=numCasillaCarcel;
        }else{
           this.numCasillaCarcel=1;  
        }
        
        casillas = new ArrayList<>();
        
        Casilla Salida = new Casilla("Salida \n");  
        casillas.add(Salida); 
    }
    
    public int getCasillaCarcel(){
        return numCasillaCarcel;
    }
    
    private boolean correcto(){
            boolean aux=false; 
            
            if(casillas.size() > numCasillaCarcel && tieneJuez == true){
                aux=true;
            }
            
            return aux;
        }
    
    private boolean correcto(int numCasilla){
        boolean aux=false;
        boolean valido=false;
        
        if((numCasilla >= 0 && numCasilla <= casillas.size()) && correcto()){
            valido=true;
        }
        if(correcto() && valido){
            aux=true;
        }
        
        return aux;
    }
    
    int getCarcel(){
        return numCasillaCarcel;
    }
    
    int getPorSalida(){
        int aux=0;
        
        if(porSalida > 0){
            aux=porSalida;
            this.porSalida=porSalida-1;
            return aux;
        }else{
            return porSalida;
        }
    }
    
    void añadeCasilla(Casilla casilla){  
        if(casillas.size() == numCasillaCarcel && numCasillaCarcel<20){
            Casilla Carcel=new Casilla("Carcel \n");  
            casillas.add(Carcel);  
        }else{
            casillas.add(casilla);
        }
    }
    
    void añadeJuez(){ 
        if(casillas.size()==numCasillaCarcel){
             Casilla juez=new Casilla("Carcel \n");
             casillas.add(juez);
             tieneJuez=true;
        }else{
             Casilla juez=new Casilla("juez \n");
             casillas.add(juez);
             tieneJuez=true;
        }    
    }
    
    Casilla getCasilla(int numCasilla){
        if(correcto(numCasilla)==true){
            return casillas.get(numCasilla);
        }else{
            return null;
        }
    }
    
    int nuevaPosicion(int actual, int tirada){
        int nuevaPos;
        
        if(correcto()){
            nuevaPos=actual+tirada;
            
            if(nuevaPos >= casillas.size()){
                porSalida+=1;
                nuevaPos=nuevaPos%casillas.size();
            }
        }else{
            nuevaPos=-1;
        }
       return nuevaPos;
    }
    
   int calcularTirada(int origen, int destino){
       int pos_final;
       
       if(destino-origen>=0){
           pos_final=destino-origen;
       }else{
           pos_final=destino-origen+casillas.size();
       }
      return  pos_final;
   }
}

