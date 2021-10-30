/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import civitas.civitasJuego;
import civitas.Operacioninmobiliaria;
import civitas.OperacionesJuego;
import civitas.Gestionesinmobiliarias;
import civitas.Jugador;
import civitas.SalidasCarcel;
import java.util.ArrayList;

/**
 *
 * @author alonsoarturo
 */
public class Controlador{
    private final civitasJuego juego;
    private final CivitasView vista;
    
    Controlador(civitasJuego juego, CivitasView vista){
        this.juego=juego;
        this.vista=vista;
    }
    
    void juega(){
        vista.setCivitasJuego(juego);
        //System.out.print("\nBIENVENIDOS A CIVITAS:\n\n");
        
        boolean fin=juego.finalDelJuego();
        
        while(fin==false){
            vista.actualizarVista();
            
            OperacionesJuego siguiente=juego.siguientePaso();
            vista.mostrarSiguienteOperacion(siguiente);
            
            if(siguiente!=OperacionesJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            if(fin==false){
                if(siguiente==OperacionesJuego.COMPRAR){
                        Respuestas respuesta=vista.Comprar();
                    if(respuesta==Respuestas.SI){
                        juego.comprar();
                        juego.siguientePasoCompleto(OperacionesJuego.COMPRAR); 
                    }else{
                        juego.siguientePasoCompleto(OperacionesJuego.COMPRAR);
                    }
                }else{
                    if(siguiente==OperacionesJuego.GESTIONAR){
                        vista.gestionar();
                        
                        Operacioninmobiliaria op=new Operacioninmobiliaria(Gestionesinmobiliarias.values()[vista.getGestion()], vista.getPropiedad());                   
                        
                        if(op.getGestion()==Gestionesinmobiliarias.CANCELAR_HIPOTECA){
                            juego.cancelarHipoteca(vista.getPropiedad());
                        }else{
                            if(op.getGestion()==Gestionesinmobiliarias.CONSTRUIR_CASA){
                                juego.construirCasa(vista.getPropiedad());
                            }else{
                                if(op.getGestion()==Gestionesinmobiliarias.CONSTRUIR_HOTEL){
                                    juego.construirHotel(vista.getPropiedad());
                                }else{
                                    if(op.getGestion()==Gestionesinmobiliarias.HIPOTECAR){
                                        juego.hipotecar(vista.getPropiedad());
                                    }else{
                                        if(op.getGestion()==Gestionesinmobiliarias.VENDER){
                                            juego.vender(vista.getPropiedad());
                                        }else{
                                            if(op.getGestion()==Gestionesinmobiliarias.TERMINAR){
                                                juego.siguientePasoCompleto(OperacionesJuego.GESTIONAR);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }else{
                        if(siguiente==OperacionesJuego.SALIR_CARCEL){
                                SalidasCarcel salida=vista.salirCarcel();
                            if(salida==SalidasCarcel.PAGANDO){
                                juego.salirCarcelPagando();
                            }else{
                                juego.salirCarcelTirando();
                            }
                            juego.siguientePasoCompleto(OperacionesJuego.SALIR_CARCEL);
                        }
                    }
                }
            }
        }
    ArrayList<Jugador> ranking=juego.ranking();
    System.out.print("RANKING");
    
    for(int i=0; i<ranking.size(); i++){
        System.out.println(i+1);            
        System.out.println(ranking.get(i).toString());
    }
    
    }
}




