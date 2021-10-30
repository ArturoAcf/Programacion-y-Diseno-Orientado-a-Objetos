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
public class Dado{
    private int random;
    private int ultimoResultado;
    private boolean debug;
    private final Diario event;
    static final private Dado instance=new Dado();
    private static final int SalidaCarcel=5;
    
    static public Dado getInstance() {
        return instance;
    }
    
    private Dado(){ 
        this.random=0;
        this.ultimoResultado=0;
        this.event=Diario.getInstance();
        this.debug=false;
    }
    
    int tirar(){
        if(!debug){
            this.random=(int)(Math.random()*6+1);
            this.ultimoResultado=this.random;
        }else{
            this.random=1;
            this.ultimoResultado=this.random;
        }
       return this.ultimoResultado;
    }
    
    int getUltimoResultado(){
        return this.ultimoResultado;
    }
    
    boolean SalgoDeLaCarcel(){
        boolean salir=false;
        
        if(getUltimoResultado()>=SalidaCarcel){
            salir=true;
        }
    
        return salir;
    }
    
    int quienEmpieza(int n){
        int aleatorio;
        
        aleatorio=(int)(Math.random()*(n-1)+1);
        
        return aleatorio;
    }
    
    public void setDebug(boolean d){ // Public para el main del otro paquete (JuegoTexto)
        this.debug=d;
        String estado;
       if(this.debug==false){
           estado=" Debug desactivado";
           System.out.print("Diario: "+estado+"\n");
           event.ocurreEvento(estado);
       }else{
           estado=" Debug activado";
           System.out.print("Diario: "+estado+"\n");
           event.ocurreEvento(estado);
       } 
    }
}
