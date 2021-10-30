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

public abstract class Sorpresa {
    private String texto;
    
    Sorpresa(String nombre){ 
        this.texto=nombre;
    }
    
    String getTexto(){
        return texto;
    }
    
    public void informe(int actual, ArrayList<Jugador> todos){
      if(this.jugadorCorrecto(actual, todos)){
            Diario.getInstance().ocurreEvento("\nAl jugador/a "+todos.get(actual).getNombre()+" se le ha aplicado la sorpresa\n");
        }
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    @Override
    public String toString(){
        return texto;
    }
    
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        boolean aux=false;
        
        if(actual>=0 && actual<todos.size()){
            aux=true;
        }
        
        return aux;
    }
}
/*
public class Sorpresa {
    private String texto;
    private int valor;
    private TipoSorpresa tipo;
    private mazoSorpresas mazo=new mazoSorpresas();
    private Tablero tablero=new Tablero(5);
    
    private void init(){
        this.valor=-1;
        this.tablero=null;
        this.mazo=null;
    }
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero){
        this.init();
        this.texto="Vas a la carcel ";
        this.valor=tablero.getCarcel();
        this.tablero=tablero;
        this.tipo=TipoSorpresa.IRCARCEL;
    }
    
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
        this.init();
        this.texto="Ir a la casilla"+tablero.getCasilla(valor);
        this.valor=valor;
        this.tablero=tablero;
        this.tipo=TipoSorpresa.IRCASILLA;
    }
    
    Sorpresa(TipoSorpresa tipo, int valor, String texto){
        this.init();
        this.tipo=tipo;
        this.valor=valor;
        this.texto=texto;   
    }
    
    Sorpresa(TipoSorpresa tipo, mazoSorpresas mazo){
        this.init();
        this.texto="Evita carcel ";
        this.tipo=TipoSorpresa.SALIRCARCEL;
        this.mazo=mazo; 
    }
    
    public TipoSorpresa getTipo() { 
        return tipo;
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(this.tipo==TipoSorpresa.IRCARCEL){
            aplicarAJugador_irCarcel(actual,todos);
        }else{
            if(this.tipo==TipoSorpresa.IRCASILLA){
                aplicarAJugador_irACasilla(actual, todos);
            }else{
                if(this.tipo==TipoSorpresa.PAGARCOBRAR){
                    aplicarAJugador_pagarCobrar(actual,todos);
                }else{
                    if(this.tipo==TipoSorpresa.PORCASAHOTEL){
                         aplicarAJugador_porCasaHotel(actual,todos);
                    }else{
                        if(this.tipo==TipoSorpresa.PORJUGADOR){
                            aplicarAJugador_Porjugador(actual,todos);
                        }else{
                             aplicarAJugador_salirCarcel(actual,todos);
                        }
                    }
                }
            }
        }
    }
    
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){
       if(jugadorCorrecto(actual, todos)==true){
        informe(actual, todos);
        int casilla_act=todos.get(actual).getNumCasillaActual();
        int tirada=tablero.calcularTirada(casilla_act, Dado.getInstance().tirar());
        int nueva_pos=tablero.nuevaPosicion(casilla_act, tirada);
        todos.get(actual).moverACasilla(nueva_pos);
        tablero.getCasilla(valor).recibeJugador(actual, todos);    
       }
    }
    
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
         if(jugadorCorrecto(actual, todos)==true){
            informe(actual, todos);
            todos.get(actual).encarcelar(5);
        }
    }
    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)==true){
            informe(actual, todos);
            todos.get(actual).modificarSaldo(valor);
        }
    }
    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)==true){
           informe(actual, todos);
           todos.get(actual).modificarSaldo(valor*todos.get(actual).cantidadCasasHoteles());
        }   
    }
    
    private void aplicarAJugador_Porjugador(int actual, ArrayList<Jugador> todos){
     if(jugadorCorrecto(actual, todos)==true){
          informe(actual, todos);
          int valor_scobrar=this.valor*-1;
          int valor_srecibe=this.valor*3;
          Sorpresa scobrar=new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor_scobrar, "cobrar");
          Sorpresa srecibe=new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor_srecibe, "recibe");
        for(int i=0; i<4; i++){
            if(i!=actual){
             scobrar.aplicarAJugador(i, todos);
            }
        }
        srecibe.aplicarAJugador(actual, todos);   
     }
    }
    
    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){
        if(jugadorCorrecto(actual, todos)==true){
        informe(actual, todos);
             boolean salvoconducto=false;
            for(int i=0; i<3; i++){
                if(todos.get(i).tieneSalvoconducto()==true){
                    salvoconducto=true;
                }
            }
            if(salvoconducto==false){
                Sorpresa salirCarcel=new Sorpresa(this.tipo, this.tablero);
                todos.get(actual).obtenerSalvoconducto(salirCarcel);
                salirDelMazo();
            }
        }
    }
    
    private void informe(int actual, ArrayList<Jugador> todos){
      Diario.getInstance().ocurreEvento(" Se esta aplicando una sorpresa a "+
                                        todos.get(actual).getNombre());  
    }
    
    public boolean jugadorCorrecto(int actual,ArrayList<Jugador> todos){
        boolean correcto=false;
        
        if(actual>0 && actual<todos.size()){
            correcto=true;
        }
        
        return correcto;
    }
    
    void salirDelMazo(){
        if(this.tipo==TipoSorpresa.SALIRCARCEL){
            mazo.inhabilitarCartaEspecial(this);
        }
    }
  
    @Override
    public String toString(){
        return texto;
    }
    
    void usada(){
        if(this.tipo==TipoSorpresa.SALIRCARCEL){
            mazo.habilitarCartaEspecial(this);
        }
    }
}*/