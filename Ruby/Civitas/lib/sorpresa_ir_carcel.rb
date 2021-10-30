# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class SorpresaIrCarcel < Sorpresa
    def initialize(tablero)
      super()
      @texto="Vas a la cárcel. \n"
      @tablero=tablero
    end
  
    # Método sobrescrito
    def aplicar_a_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        todos[actual].encarcelar(@tablero.num_casilla_carcel)
      end
    end
    
    
    def to_s
       "\n\t ¡SORPRESA! \n
       \n Has recibido: 
       \n #{@texto} \n"
    end
  end
end