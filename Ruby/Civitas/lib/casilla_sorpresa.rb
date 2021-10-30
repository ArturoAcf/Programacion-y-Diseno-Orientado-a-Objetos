# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class CasillaSorpresa < Casilla
    def initialize(name, mazo)
      super(name)
      @mazo=mazo
    end

    # Método sobrescrito
    def recibe_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        sorp=@mazo.siguiente
        informe(actual, todos)
        print "#{sorp.to_s} -> Tipo de sorpresa. \n"
        sorp.aplicar_a_jugador(actual, todos)
      end
    end
  
    
    def to_s
      "\n\t DESCRIPCIÓN CASILLA: \n
        \n Nombre: #{nombre}
        \n Tipo: SORPRESA
        \n Sorpresa: #{@mazo.siguiente.to_s}
        "
    end
  end
end