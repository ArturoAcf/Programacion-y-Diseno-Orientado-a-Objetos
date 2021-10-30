# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class SorpresaJugadorEspeculador < Sorpresa
    def initialize (valor)
      
      @fianza=valor
      @texto="Ahora eres un jugador especulador\n"
    end
    
    # Método sobrescrito
    def aplicar_a_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        informe(actual, todos)
        jugador=todos[actual]
        especulador=Jugador_Especulador.new(jugador, @fianza)
        todos.insert(actual, especulador)
      end
    end 
  end
end

