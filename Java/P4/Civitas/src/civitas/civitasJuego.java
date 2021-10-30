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
public class civitasJuego {
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int indiceJugadorActual;
    private gestorEstados estadoJuego;
    private mazoSorpresas mazo;
    private EstadosJuego est;
    
    public civitasJuego(ArrayList<String> nombres){
        jugadores=new ArrayList<>();
            
        for(int i=0; i<4; i++){
            Jugador jugador=new Jugador(nombres.get(i));
            jugadores.add(jugador);
        }
        
        estadoJuego = new gestorEstados();
        est=estadoJuego.estadoInicial();
        
        indiceJugadorActual=Dado.getInstance().quienEmpieza(jugadores.size());
        
        mazo=new mazoSorpresas();
        tablero=new Tablero(5);
       
        inicializaTablero(mazo);       
        inicializaMazoSorpresas(tablero);
    }
    
    public Jugador getJugadorActual(){
        return jugadores.get(indiceJugadorActual);
    }
    
    //__________________________________________________________________________
    // Las clases comentadas existen porque las utilicé para probar el funcionamiento antes de la P3.
    // No se utilizan, pero las conservo por si debo cambiar algo.
    /*
    public void mostrarTablero(){ 
        for(int k=0; k<20; k++){ 
            System.out.print(tablero.getCasilla(k).getNombre()); //Lo muestro desde aquí
        }
    }    
    
    public void mostrarMazo(){ 
        for(int i=0; i<mazo.getTamMazo(); i++){
            System.out.print(mazo.toString());
        }
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    */
    //__________________________________________________________________________
    
    public Casilla getCasillaActual(){
        return tablero.getCasilla(jugadores.get(indiceJugadorActual).getNumCasillaActual());
    }
    
    public String infoJugadorTexto(){
        return jugadores.get(indiceJugadorActual).toString()+" \n\nInformación sobre la casilla actual: \n"+getCasillaActual().toString()+"-----------------------------------------\n"+
                jugadores.get(indiceJugadorActual).getPropiedades().toString()+"\n";
    }
    
    private void inicializaTablero(mazoSorpresas mazo){ 
        Casilla parking=new Casilla("Parking");
        Casilla impuesto=new CasillaImpuesto((float)300, "Impuesto");
        
        tituloPropiedad t1=new tituloPropiedad("Calle 1",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t2=new tituloPropiedad("Calle 2",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t3=new tituloPropiedad("Calle 3",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t4=new tituloPropiedad("Calle 4",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t5=new tituloPropiedad("Carcel \n",0, (float)0.0, 0, 0,0);
        tituloPropiedad t6=new tituloPropiedad("Calle 6",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t7=new tituloPropiedad("Calle 7",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t8=new tituloPropiedad("Calle 8",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t9=new tituloPropiedad("Calle 9",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t10=new tituloPropiedad("Calle 10",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t11=new tituloPropiedad("Calle 11",400, (float)1.5, 200, 2000,1500);
        tituloPropiedad t12=new tituloPropiedad("Calle 12",400, (float)1.5, 200, 2000,1500);
        
        Casilla calle1=new CasillaCalle(t1);
        Casilla calle2=new CasillaCalle(t2);
        Casilla calle3=new CasillaCalle(t3);
        Casilla calle4=new CasillaCalle(t4);
        Casilla calle5=new CasillaCalle(t5);
        Casilla calle6=new CasillaCalle(t6);
        Casilla calle7=new CasillaCalle(t7);
        Casilla calle8=new CasillaCalle(t8);
        Casilla calle9=new CasillaCalle(t9);
        Casilla calle10=new CasillaCalle(t10);
        Casilla calle11=new CasillaCalle(t11);
        Casilla calle12=new CasillaCalle(t12);
        
        Casilla sorpresa1=new CasillaSorpresa(mazo, "Sorpresa 1"); 
        Casilla sorpresa2=new CasillaSorpresa(mazo, "Sorpresa 2"); 
        Casilla sorpresa3=new CasillaSorpresa(mazo, "Sorpresa 3");
       
        tablero.añadeCasilla(sorpresa1);
        tablero.añadeCasilla(calle1); 
        tablero.añadeCasilla(impuesto);  
        tablero.añadeJuez(); 
        tablero.añadeCasilla(calle2);
        tablero.añadeCasilla(calle3);
        tablero.añadeCasilla(calle4);
        tablero.añadeCasilla(calle5);
        tablero.añadeCasilla(calle6);
        tablero.añadeCasilla(calle7);
        tablero.añadeCasilla(calle8);
        tablero.añadeCasilla(parking);
        tablero.añadeCasilla(sorpresa2);
        tablero.añadeCasilla(calle9);
        tablero.añadeCasilla(calle10);
        tablero.añadeCasilla(calle11);
        tablero.añadeCasilla(calle12);
        tablero.añadeCasilla(sorpresa3);
    }
    
    private void inicializaMazoSorpresas(Tablero tablero){
        Sorpresa pagarcobrar1=new SorpresaPagarCobrar(2000,"Cobra");
        Sorpresa pagarcobrar2=new SorpresaPagarCobrar(-2000,"Paga");
        Sorpresa ircasilla1=new SorpresaIrACasilla(tablero,10,"ir casilla");
        Sorpresa ircasilla2=new SorpresaIrACasilla(tablero,15,"ir casilla");
        Sorpresa ircarcel=new SorpresaIrCarcel(tablero);
        Sorpresa pasaporcasahotel1=new SorpresaPorCasaHotel(tablero, 200,"recibe");
        Sorpresa pasaporcasahotel2=new SorpresaPorCasaHotel(tablero, -200,"paga");
        Sorpresa pagajugador=new SorpresaPorJugador(-150,"paga al jugador");
        Sorpresa cobrajugador=new SorpresaPorJugador(150,"recibe del jugador");
        Sorpresa salircarcel=new SorpresaSalirCarcel(mazo, "Salir de la carcel");
               
        mazo.alMazo(pagarcobrar1);
        mazo.alMazo(pagarcobrar2);
        mazo.alMazo(ircasilla1);
        mazo.alMazo(ircasilla2);
        mazo.alMazo(ircarcel);
        mazo.alMazo(pasaporcasahotel1);
        mazo.alMazo(pasaporcasahotel2);
        mazo.alMazo(pagajugador);
        mazo.alMazo(cobrajugador);
        mazo.alMazo(salircarcel);
     }
    
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        while(tablero.getPorSalida()>0){
            jugadorActual.pasaPorSalida();
            jugadorActual.recibe(1000);
        }
    }
    
    private void pasarTurno(){
        indiceJugadorActual=(indiceJugadorActual+1)%jugadores.size();
    }
    
    private void avanzaJugador(){
        Jugador jugador=jugadores.get(indiceJugadorActual);
        int posAct=jugador.getNumCasillaActual();
        int tirada=Dado.getInstance().tirar();
        int Nuevapos=tablero.nuevaPosicion(posAct, tirada);
        Casilla casilla=tablero.getCasilla(Nuevapos);
        this.contabilizarPasosPorSalida(jugador);
        jugador.moverACasilla(Nuevapos);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
        this.contabilizarPasosPorSalida(jugador);
    }
    
    public OperacionesJuego siguientePaso(){
        Jugador jugador;
        jugador=jugadores.get(indiceJugadorActual);
        OperacionesJuego operacion;
        operacion = estadoJuego.operacionesPermitidas(jugador, est);
        
        if(operacion==OperacionesJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompleto(operacion);
        }else{
            if(operacion==OperacionesJuego.AVANZAR){
                avanzaJugador();
                siguientePasoCompleto(operacion);
            }
        }
        return operacion;
    }
    
    public void siguientePasoCompleto(OperacionesJuego operacion){
        est=estadoJuego.siguienteEstado(jugadores.get(indiceJugadorActual), est, operacion);
    }
    
    public boolean construirCasa(int ip){
        boolean aux=false;
        
        if(jugadores.get(indiceJugadorActual).construirCasa(ip)){
            aux=true;
        }
        
        return aux;
    }
    
    public boolean construirHotel(int ip){
        boolean aux=false;
        
        if(jugadores.get(indiceJugadorActual).construirHotel(ip)){
            aux=true;
        }
        
        return aux;
    }
    
    public boolean vender(int ip){
        jugadores.get(indiceJugadorActual).vender(ip);
        return true;
    }
    
    public boolean comprar(){
        boolean aux=false;
        
        Jugador jugador=jugadores.get(indiceJugadorActual);
        int casillaActual=jugador.getNumCasillaActual();
        Casilla casilla=tablero.getCasilla(casillaActual);
        CasillaCalle calle=(CasillaCalle)casilla;
        tituloPropiedad titulo=calle.getTituloPropiedad();
        aux=jugador.comprar(titulo);
        
        return aux;
    }
    
    public ArrayList<Jugador>Ranking(){ 
        Collections.sort(jugadores);
        
        for (int i=0;i<jugadores.size();i++){
            System.out.println("Ranking Judadores"+ jugadores.get(i).getNombre());
        }
      
        return jugadores;
    }
    
    public boolean hipotecar(int ip){
        Boolean aux=jugadores.get(indiceJugadorActual).hipotecar(ip);
        return aux;
    }
    
    public boolean cancelarHipoteca(int ip){
        Boolean aux=jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
        return aux;
    }
    
    public boolean salirCarcelPagando(){
        boolean aux=false;
        
        if(jugadores.get(indiceJugadorActual).salirCarcelPagando()){
            aux=true;
        }
        
        return aux;
    }
    
    public boolean salirCarcelTirando(){
         boolean aux=false;
        
        if(jugadores.get(indiceJugadorActual).salirCarcelTirando()){
            aux=true;
        }
        
        return aux;
    }
    
    public boolean finalDelJuego(){
        boolean aux=false;
        
        for(int i=0; i<jugadores.size(); i++){
            if(jugadores.get(i).enBancaRota()==true){
                aux=true;
            }
        }
    
        return aux;
    }
    
    public ArrayList<Jugador> ranking(){
      ArrayList<Jugador> ranking=jugadores;
        
      Collections.sort(ranking, Collections.reverseOrder());  
      
      return ranking;
    }
}