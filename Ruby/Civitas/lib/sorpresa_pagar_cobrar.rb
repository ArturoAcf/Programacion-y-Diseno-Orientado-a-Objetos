# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class SorpresaPagarCobrar < Sorpresa
    def initialize(valor, texto)
      super()
      @valor=valor
      @texto=texto
      
      #puts "Sorpresa PagarCobrar creada.\n"
    end
    
    # Método sobrescrito
    def aplicar_a_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        todos[actual].modificar_saldo(self.valor)
      end
    end

    def to_s
       "\n\t ¡SORPRESA! \n
       \n Has recibido: 
       \n #{@texto} \n"
    end
  end
end