/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author arturo
 */
public class Operacioninmobiliaria {
    private final int numPropiedad;
    private final Gestionesinmobiliarias Gestion;
    
    public Operacioninmobiliaria(Gestionesinmobiliarias gest, int ip){
        this.numPropiedad=ip;
        this.Gestion=gest;
    }
    
    public int getNumPropiedad(){
        return numPropiedad;
    }
    
    public Gestionesinmobiliarias getGestion(){
        return Gestion;
    }
}