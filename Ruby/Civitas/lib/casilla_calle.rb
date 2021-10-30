# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class CasillaCalle < Casilla
    def initialize(titulo)
      super (titulo.nombre)
      @titulo_propiedad=titulo
    end

    # Método sobrescrito
    def recibe_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        informe(actual, todos)
        jugador=todos[actual]
        
        unless(@titulo_propiedad.tiene_propietario)
          jugador.puede_comprar_casilla
          @titulo_propiedad.tramitar_alquiler(jugador)
        end
        
      end
    end
    
    def to_s
      "\n   **DESCRIPCIÓN CASILLA**
       \n\tTipo: CALLE
       \n\tNombre: #{nombre}
       \n\tPropiedad: #{@titulo_propiedad.to_s}"
    end
    
  end
end