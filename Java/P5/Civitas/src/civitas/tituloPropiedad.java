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
public class tituloPropiedad {
   private boolean hipotecado;
   private int numCasas;
   private int numHoteles;
   static private final float factorInteresesHipoteca=(float) 1.1;
   private float alquilerbase;
   private float factor_rev;
   private float precioBaseHipoteca;
   private float precio_compra;
   private float precio_edif;
   private String nombre;
   // sucede y nosucede se utilizan para comprobar 
   private String sucede="Operacion realizada \n"; 
   private String nosucede="Operacion no realizada \n";
   private float precioalquiler;
   private Jugador propietario;
   private int ip;
    
   public tituloPropiedad(String nombre, float alquilerbase, float factor_rev, float precioBaseHipoteca, float precio_compra, float precio_edif){       
      this.nombre=nombre;
      this.factor_rev=factor_rev;
      this.precioBaseHipoteca=precioBaseHipoteca;
      this.precio_compra=precio_compra;
      this.precio_edif=precio_edif;
      this.numCasas=0;
      this.numHoteles=0;
      this.hipotecado=false;
      this.alquilerbase=alquilerbase;
      propietario=null;
   }
   
   int getIp(){
       return ip;
   }
  
   @Override //toString es un método de la clase String, por lo que esto indica que no me estoy equivocando al tito netbeans 
   public String toString(){
       return "*TituloPropiedad*" + "\n\t Nombre: " + nombre + "\n\t PrecioCompra: " + precio_compra +
                "\n\t AlquilerBase: " + alquilerbase + "\n\t HipotecaBase: " + precioBaseHipoteca + 
                "\n\t PrecioEdificar: " + precio_edif + "\n\t FactorRevalorizacion: " + factor_rev +
                "\n\t NumHoteles: " + numHoteles + "\n\t NumCasas: " + numCasas + 
                "\n\t Hipotecada: " + getHipotecado() + "\n"; 
   }
   
   public float getPrecioAlquiler(){   
       if(!hipotecado && this.propietarioEncarcelado()==false){
           this.precioalquiler=(float) (alquilerbase*(1+(numCasas*0.5)+(numHoteles*2.5)));  
       }else{
           this.precioalquiler=0;
       }
       
       return precioalquiler;
   }
   
   public Jugador getPropietario(){
       return propietario;
   }
   
   float getImporteCancelarHipoteca(){
       float aux;      
       aux=precioBaseHipoteca*factorInteresesHipoteca;
       return aux;
   }
   
   public boolean getHipotecado(){
       return hipotecado;
   }
   
   public String getNombre(){
       return nombre;
   }
   
   public int getNumCasas(){
       return numCasas;
   }
   
   public int getNumHoteles(){
       return numHoteles;
   }
   
   float getPrecioCompra(){
       return precio_compra;
   }
   
   private boolean esEsteElPropietario(Jugador jugador){
       boolean aux=false;
       
       if(propietario==jugador){
           aux=true;
       }
       
       return aux;
   }
   
   boolean cancelarHipoteca(Jugador jugador){
       boolean aux=false;
       
       if(hipotecado==true && esEsteElPropietario(jugador)){
           propietario.paga(getImporteCancelarHipoteca());
           hipotecado=false;
           aux=true;
       }
       
       return aux;
   }
   
   boolean hipotecar(Jugador jugador){
       if(hipotecado==false && esEsteElPropietario(jugador)){
           propietario.recibe(precioBaseHipoteca);
           hipotecado=true;
       }
       
       return hipotecado;
   }
   
   boolean tienePropietario(){
       boolean aux=false;
       
       if(propietario!=null){
            aux=true; 
       }
       
       return aux;
    }
   
   void tramitarAlquiler(Jugador jugador){
       if(this.tienePropietario()==true && !esEsteElPropietario(jugador)){
           jugador.pagaAlquiler(getPrecioAlquiler());
           propietario.recibe(getPrecioAlquiler());
           System.out.print(sucede);
       }else{
           System.out.print(nosucede);
       }
   }
     
   private boolean propietarioEncarcelado(){
       boolean aux=false;
       
       if(!tienePropietario() || propietario.encarcelado){
           aux=true;
       }
       
       return aux;
   }
   
   int cantidadCasaHoteles(){
       return numCasas+numHoteles;
   }
   
   boolean derruirCasas(int n, Jugador jugador){
       boolean aux=false;
       
       if(esEsteElPropietario(jugador) && numCasas>=n){
            numCasas=numCasas-n;
            aux=true;
           if(aux){
               System.out.print(sucede);
           }else{
               aux=false;
               System.out.print(nosucede);  
           }
       }
       return aux;
   }
   
   boolean derruirHoteles(int n, Jugador jugador){
       boolean aux=false;
       
       if(esEsteElPropietario(jugador) && numHoteles>=n){
            numHoteles=numHoteles-n;
            aux=true;
           if(aux){
               System.out.print(sucede);
           }else{
               aux=false;
               System.out.print(nosucede);
           }
       }
       return aux;
   }
   
   float getPrecioVenta(){
       return precio_compra+precio_edif*cantidadCasaHoteles()*factor_rev;
   }
   
   public float getPrecioEdificar(){
       return precio_edif;
   }
   
   boolean construirCasa(Jugador jugador){
       boolean aux=false;
       
       if(this.esEsteElPropietario(jugador)){
           propietario.paga(precio_edif);
           numCasas++;
           aux=true;
       }
       
       return aux;
   }
   
   boolean construirHotel(Jugador jugador){
       boolean aux=false;
       
       if(esEsteElPropietario(jugador)==true && numCasas>=4){
           propietario.paga(precio_edif);
           numHoteles++;
           derruirCasas(4, jugador);
           aux=true;
       }
       
       return aux;
   }
   
   boolean Comprar(Jugador jugador){
       boolean aux=false;
       
       if(!tienePropietario()){
           aux=true;
           propietario=jugador;
           jugador.paga(getPrecioVenta());
       }
       
       return aux;
   }
   
   void actualizaPropietarioPorConversion(Jugador jugador){
       this.propietario=jugador;
   }
   
   boolean Vender(Jugador jugador){
       boolean aux=false;
       
       if(hipotecado==false && esEsteElPropietario(jugador)==true){
           System.out.print(getPrecioVenta()+" es lo que recibe por vender\n");
           jugador.recibe(getPrecioVenta());
           derruirCasas(numCasas, jugador);
           derruirHoteles(numHoteles, jugador);
           actualizaPropietarioPorConversion(null);
           aux=true;
       }
       
       return aux;
   }
}