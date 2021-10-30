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
public class JugadorEspeculador extends Jugador{
    private static int FactorEspeculador=2;
    private float fianza;
    private boolean especulador;
    
    JugadorEspeculador(Jugador jugador, float fianza){
        super(jugador);
        this.fianza=fianza;
        especulador=true;
        
        for(int i=0; i<super.getPropiedades().size(); i++){
            super.getPropiedades().get(i).actualizaPropietarioPorConversion(this);
        }
    }
    
    @Override
    boolean encarcelar(int numCasillaCarcel){
        boolean aux=false;
        
        if(super.encarcelado==false){
            if(super.tieneSalvoconducto()==false){
                if(fianza>=super.getSaldo()){
                aux=true;
            }else{
                super.paga(fianza);
                Diario.getInstance().ocurreEvento("El jugador "+super.getNombre()+" Se ha librado de la cárcel pagando una fianza\n");
            }
            }else{
                aux=false;
                super.perderSalvoconducto();
                Diario.getInstance().ocurreEvento("El jugador "+super.getNombre()+" se libra de la cárcel con el salvoconducto\n");
            }
        }
    return aux;        
    }
    
    @Override
    int getCasasMax(){
        return super.getCasasMax()*FactorEspeculador;
    }
    
    @Override 
    int getHotelesMax(){
        return super.getHotelesMax()*FactorEspeculador;
    }
    
    @Override 
    boolean pagaImpuesto(float cantidad){
        boolean aux=false;
        
        if(encarcelado==false){
            float impuesto=cantidad/2;
            aux=paga(impuesto);
            Diario.getInstance().ocurreEvento("El Jugador "+super.getNombre()+" paga "+impuesto+" €\n");
        }
    return aux;
    }
    
    @Override
    public String toString(){
        return "\n"+super.getNombre()+"\nEncarcelado: "+super.encarcelado
                +"\nPuede comprar: "+super.getPuedeComprar()+"\nSaldo: "+super.getSaldo()+"\nNumero de propiedades: "+
                super.getPropiedades().size()+"\nPropiedades: "+super.getPropiedades()+" "+"\nCasilla actual: "+super.getNumCasillaActual()+
                "\nEspeculador: "+especulador+"\n-----------------------------------------";
        
    }
}
