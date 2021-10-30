# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class CasillaImpuesto < Casilla
    def initialize(name, cantidad)
      super(name)
      @importe=cantidad
    end

    # Método sobrescrito
    def recibe_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        informe(actual, todos)
        todos[actual].paga_impuesto(@importe)
      end
    end
  
    def to_s
       "\n   DESCRIPCIÓN CASILLA: 
        \n\t Nombre: #{nombre}
        \n\t Tipo: IMPUESTO
        \n\t Precio a pagar: #{@importe}
        "
    end
    
  end
end