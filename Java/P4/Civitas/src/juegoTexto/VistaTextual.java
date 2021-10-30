package juegoTexto;

import civitas.civitasJuego;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.Jugador;
import civitas.tituloPropiedad;
import civitas.Respuestas;

class VistaTextual {
  
  civitasJuego juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () {
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
    System.out.print (" Pulsa una tecla ");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la cárcel",
      new ArrayList<> (Arrays.asList("Pagando", "Tirando")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar(){
    Respuestas compra;
    int op=menu ("¿Desea comprar?",
    new ArrayList<> (Arrays.asList("NO","SI")));
    compra=Respuestas.values()[op];
    return compra;
  }

  void gestionar(){
      int opcion=menu("¿Qué número de gestión inmobiliaria desea realizar?", new ArrayList<>(Arrays.asList("Vender", "Hipotecar", "Cancelatr Hipoteca", "Construir casa", "Construir hotel", "Terminar")));
      iGestion=opcion;
      
      ArrayList<String> calles=new ArrayList<String>();
      
      for(int i=0; i<juegoModel.getJugadorActual().getPropiedades().size(); i++){
          calles.add(juegoModel.getJugadorActual().getPropiedades().get(i).getNombre());
      }
      
      iPropiedad=menu("¿Sobre qué casilla desea realiza la operación?", calles);   
  }
  
  public int getGestion(){
      return iGestion;
  }
  
  public int getPropiedad(){
      return iPropiedad;
  }
    
  void mostrarSiguienteOperacion(OperacionesJuego operacion) {
  System.out.print("Operación siguiente: "+operacion);}


  void mostrarEventos() {
      if(Diario.getInstance().eventosPendientes()){
          System.out.print(Diario.getInstance().leerEvento());
      }
  }
  
  public void setCivitasJuego(civitasJuego civitas){ 
        juegoModel=civitas;
        this.actualizarVista();
  }
  
  void actualizarVista(){
      System.out.print(juegoModel.infoJugadorTexto());
  } 
}