# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "titulo_propiedad"

module Civitas
  class JugadorEspeculador < Jugador
    def initialize(jugador, fianza)
      nuevo_especulador(jugador)
      @@FactorEspeculador=2
      @fianza=fianza
      @especulador=true
    end
    
    attr_reader :FactorEspeculador
    attr_accessor :fianza, :especulador
    
    def nuevo_especulador(jugador)
      super.copia(jugador)
      
      super.propiedades.each do |propiedad|
        propiedad.actualizar_propietario_por_conversion(self)
      end
      
    end
    
    # Método sobrescrito
    def encarcelar(num_casilla__carcel)
      aux=false
      
      if(super.encarcelado==false)
        unless(super.tiene_Salvoconducto)
          if(fianza>=super.saldo)
            aux=true
          else
            super.paga(fianza)
            Diario.instance.ocurre_evento("El jugador #{super.nombre} se ha librado de la cárcel pagando una fianza. \n")
          end
        else
          aux=false
          super.perder_salvoconducto
          Diario.instance.ocurre_evento("El jugador #{super.nombre} se ha librado de la cárcel usando el salvoconducto. \n")
        end
      end
      
      return aux
    end
    
    # Método sobrescrito
    def casas_max
      return super.Casas_max*FactorEspeculador
    end
    
    # Método sobrescrito
    def hoteles_max
      return super.Hoteles_max*FactorEspeculador
    end
    
    # Método sobrescrito
    def paga_impuesto(cantidad)
      aux=false
      
      unless(encarcelado)
        impuesto=cantidad/FactorEspeculador
        aux=paga(impuesto)
        Diario.instance.ocurre_evento("El jugador")
      end
      return aux
    end
    
    def to_s
      return "\nNombre: #{super.nombre}
              \nEspeculador: #{especulador}
              \nCasilla actual: #{super.num_casilla_actual}
              \nEncarcelado: #{super.encarcelado}
              \nPuede comprar: #{super.puede_comprar}
              \nSaldo: #{super.saldo}
              \nNumero de propiedades: #{super.get_propiedades}
              \nPropiedades: #{super.propiedades}"
    end
  end
end