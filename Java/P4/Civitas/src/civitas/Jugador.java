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
public class Jugador implements Comparable<Jugador>{
    protected static int CasasMax=4;
    protected static int PorHotel=4;
    protected boolean encarcelado;
    protected static int HotelesMax=4;
    private String nombre;
    private int numCasillaActual;
    protected static float PasoPorSalida=1000;
    protected static float PrecioLibertad=200;
    private boolean puedeComprar;
    private float saldo;
    static private float SaldoInicial=7500;
    private ArrayList<tituloPropiedad> propiedades;
    Dado nuevo=Dado.getInstance();
    private Sorpresa salvoconducto=null;
    
    Jugador(String nombre){
        saldo=SaldoInicial;
        this.nombre=nombre;
        puedeComprar=true;
        numCasillaActual=0;
        propiedades=new ArrayList<>();
    }
    
    protected Jugador(Jugador otro){
        encarcelado=otro.estaEncarcelado();
        nombre=otro.getNombre();
        numCasillaActual=otro.getNumCasillaActual();
        puedeComprar=otro.getPuedeComprar();
        saldo=otro.getSaldo();
        salvoconducto=otro.getSalvoconducto();
        propiedades=otro.getPropiedades();
    }
    
    public boolean estaEncarcelado(){
        return encarcelado;
    }
    
    public void setPuedeComprar(boolean x){
        this.puedeComprar=x;
    }
    
    boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    Sorpresa getSalvoconducto(){
        return salvoconducto;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getNumCasillaActual(){
        return this.numCasillaActual;
    }
    
    int getCasasMax(){
        return CasasMax;
    }
    
    int getHotelesMax(){
        return HotelesMax;
    }
    
    public ArrayList<tituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    boolean enBancaRota(){
        boolean aux=false;
        
        if(this.saldo<=0){
            aux=true;
        }
        
        return aux;
    }
    
    protected boolean debeSerEncarcelado(){
        boolean aux=false;
        
        if(tieneSalvoconducto()==false && encarcelado==false){
            aux=true;
        }else{
            Diario.getInstance().ocurreEvento("El jugador se libra de la cárcel y pierde el salvoconducto\n");
            perderSalvoconducto();
            aux=false;
        }
        
        return aux;
    }
    
    int cantidadCasasHoteles(){
        int total=0;
        
        for(int i=0; i<propiedades.size(); i++){
            total+=propiedades.get(i).getNumCasas();
            total+=propiedades.get(i).getNumHoteles();
        }
        
        return total;
    }
    
    boolean encarcelar(int numCasillaCarcel){
        if(debeSerEncarcelado()){
            Diario.getInstance().ocurreEvento("El jugador es encarcelado\n");
            this.moverACasilla(numCasillaCarcel);
            this.encarcelado=true;
        }
        
        return true;
    }
    
    boolean obtenerSalvoconducto(Sorpresa s){
        boolean aux=false;
        
        if(encarcelado==false){
            this.salvoconducto=s;
            aux=true;
        }
        
        return aux;
    }
    
    void perderSalvoconducto(){
        obtenerSalvoconducto(null);
    }
    
    public boolean esEspeculador(){
        return false;
    }
    
    public boolean tieneSalvoconducto(){
        boolean aux=false;
        
        if(!obtenerSalvoconducto(null)){
            aux= true;
        }
        
        return aux;
    }
    
    boolean puedeComprarCasilla(){
        if(encarcelado){
            puedeComprar=false;
        }else{
            puedeComprar=true;
        }
        
        return puedeComprar;
    }
    
    boolean paga(float cantidad){
        return this.modificarSaldo(cantidad*(-1));
    }
    
    boolean pagaImpuesto(float cantidad){
        boolean aux=false;
        
        if(!encarcelado){
            aux=this.paga(cantidad);
        }
        
        return aux;
    }
    
    boolean pagaAlquiler(float cantidad){
        boolean aux=false;
        
        if(encarcelado==false){
            aux=this.paga(cantidad);
        }
        
        return aux;
    }
    
    boolean recibe(float cantidad){
        boolean aux=false;
        
        if(encarcelado==false){
            aux=this.modificarSaldo(cantidad);
        }
        
        return aux;
    }
    
    boolean modificarSaldo(float cantidad){
        this.saldo+=(float)cantidad;
        Diario.getInstance().ocurreEvento("\nEl saldo del jugador "+this.nombre+" ha sido modificado\n");
        return true;
    }
    
    boolean moverACasilla(int numCasilla){
        boolean aux=false;
        
        if(encarcelado==false){
            this.numCasillaActual=numCasilla;
            this.puedeComprar=false;
            Diario.getInstance().ocurreEvento("\n"+this.nombre+" se ha movido a la casilla: "+this.numCasillaActual+"\n");
            aux=true;
        }
        
        return aux;
    }
    
    private boolean puedoGastar(float precio){
        boolean aux=false;
        
        if(!encarcelado && saldo>=precio){
            aux=true;
        }
        
        return aux;
    }
    
    boolean vender(int ip){
        boolean aux=false;
        
        if(!encarcelado && (existeLaPropiedad(ip)==true && propiedades.get(ip).getHipotecado()==false)){
            tituloPropiedad titulo=propiedades.get(ip);
            aux=titulo.Vender(this);
            
            Diario.getInstance().ocurreEvento(nombre+" ha vendido la casilla "+propiedades.get(ip));
            propiedades.remove(ip);
        }
        
        return aux;
    }
    
    boolean comprar(tituloPropiedad titulo){
        boolean aux=false;
        
        if(encarcelado){
            return aux;
        }
        
        if(puedeComprar==true){
                float precio=titulo.getPrecioVenta();
            if(puedoGastar(precio)==true){
                    aux=titulo.Comprar(this);
                if(aux==true){
                    propiedades.add(titulo);
                    Diario.getInstance().ocurreEvento("El jugador "+this+" compra la propiedad "+titulo.toString());
                    puedeComprar=false;
                }
            }
        }
        return aux;
    }
    
     boolean hipotecar(int ip){
        boolean aux=false;
        
        if(encarcelado==false){
            if(existeLaPropiedad(ip)){
                tituloPropiedad titulo=propiedades.get(ip);
                aux=titulo.hipotecar(this);
            }
        }
        
        if(aux==true){
            Diario.getInstance().ocurreEvento("El jugador "+nombre+" ha hipotecado la propiedad "+ip);
        }
            
        return aux;
    }
    
    boolean cancelarHipoteca(int ip){
        boolean aux=false;
        
        if(!encarcelado){
            if(existeLaPropiedad(ip)){
                tituloPropiedad titulo=propiedades.get(ip);
                float cantidad=titulo.getImporteCancelarHipoteca();
                Boolean puedoGastar=puedoGastar(cantidad);
                
                if(puedoGastar==true){
                        aux=titulo.cancelarHipoteca(this);
                    if(aux==true){
                        Diario.getInstance().ocurreEvento("El jugador "+nombre+" cancela la hipoteca de la propiedad "+ip);
                    }
                }
            }
        }
        
        return aux;
    }
    
    boolean tieneAlgoQueGestionar(){
        boolean aux=false;
        
        if(!propiedades.isEmpty()){
            aux=true;
        }
        
        return aux;
    }
    
    private boolean puedeSalirCarcelPagando(){
        boolean aux=false;
        
        if(encarcelado && saldo>=PrecioLibertad){
            aux=true;
        }
        
        return aux;
    }
    
    boolean salirCarcelPagando(){
        boolean aux=false;
        
        if(encarcelado && puedeSalirCarcelPagando()){
            this.paga(PrecioLibertad);
            encarcelado=false;
            Diario.getInstance().ocurreEvento(this.nombre+" sale de la cárcel pagando");
            aux=true;
        }
        
        return aux;
    }
    
    @Override
    public String toString(){
        String devolver=" ";
        String todas=" ";
        String aux;
        
        for(int i=0; i<propiedades.size(); i++){
            aux=propiedades.get(i).getNombre();
            todas=todas+"";
            todas=todas+aux;
        }
        
        devolver="\n"+this.getNombre()+"\nEncarcelado: "+this.encarcelado
                +"\nPuede comprar: "+this.puedeComprar+"\nSaldo: "+this.saldo+"\nNumero de propiedades: "+
                this.propiedades.size()+"\nPropiedades: "+todas+" "+"\nCasilla actual: "+this.numCasillaActual+"\n-----------------------------------------";
        
        return devolver;
    }
            
    boolean salirCarcelTirando(){
        boolean aux=false;
        
        if(encarcelado && nuevo.SalgoDeLaCarcel()){
            aux=true;
            encarcelado=false;
            Diario.getInstance().ocurreEvento(this.nombre+" sale de la cárcel tirando");
        }
        
        return aux;
    }  
    
    boolean construirCasa(int ip){
        boolean aux=false;
        boolean puedo=false;
        
        if(!encarcelado){
                boolean existe=existeLaPropiedad(ip);
            if(existe){
                    tituloPropiedad propiedad=propiedades.get(ip);
                    puedo=this.puedoEdificarCasa(propiedad);
                if(puedo==true){
                        aux=propiedad.construirCasa(this);
                    if(aux==true){
                        Diario.getInstance().ocurreEvento("El jugador "+nombre+" construye casa en "+propiedad.getNombre()+"\n");
                    }
                }
            }
        }
        return aux;
    }
    
    boolean construirHotel(int ip){
        boolean aux=false;
        boolean puedo=false;
        
        if(!encarcelado){
                boolean existe=existeLaPropiedad(ip);
            if(existe){
                    tituloPropiedad propiedad=propiedades.get(ip);
                    puedo=this.puedoEdificarCasa(propiedad);
                if(puedo==true){
                        aux=propiedad.construirHotel(this);
                    if(aux==true){
                        Diario.getInstance().ocurreEvento("El jugador "+nombre+" construye hotel en "+propiedad.getNombre()+"\n");
                    }else{
                        Diario.getInstance().ocurreEvento("\nEl numero de casas no es suficiente (debe ser mayor a 4)\n");
                    }
                }
            }
        }
        return aux;
    }
            
    boolean pasaPorSalida(){
        modificarSaldo(PasoPorSalida);  
        Diario.getInstance().ocurreEvento(this.nombre+" pasa por la casilla de salida y recibe el pago correspondiente");
        return true;
    }
    
    private boolean existeLaPropiedad(int ip){
        return true;
    }
    
    private boolean puedoEdificarCasa(tituloPropiedad casa){
        boolean aux=false;
        float precio=casa.getPrecioEdificar();
        
        if(this.puedoGastar(precio) && casa.getNumCasas()<this.getCasasMax()){
            aux=true;
        }
        
        return aux;
    }
            
    /**
     *
     * @param otro
     * @return
     */
    @Override
    public int compareTo(Jugador otro){
        int aux;
        
        if(this.saldo>=otro.saldo){
            aux=(int) this.saldo-(int) otro.saldo;
        }else{
            aux=(int) otro.saldo-(int) this.saldo;
        }
        
        return aux;
    }       
}