# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class CasillaJuez < Casilla
    def initialize(name, num_casilla_carcel)
      super(name)
      @carcel=num_casilla_carcel
    end
  
    # Método sobrescrito
    def recibe_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        informe(actual, todos)
        todos[actual].encarcelar(@carcel)
      end
    end
  
    
    def to_s
      "\n\t DESCRIPCIÓN CASILLA: \n
        \n Nombre: #{nombre}
        \n Tipo: JUEZ
        \n Carcel en la casilla: #{@carcel}
        "
    end
  end
end