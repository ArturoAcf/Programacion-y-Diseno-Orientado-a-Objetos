# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class SorpresaIrACasilla < Sorpresa
    def initialize(tablero, valor, texto)
      super()
      @valor = valor
      @texto = texto
      @tablero = tablero
    end
  
    # Método sobrescrito
    def aplicar_a_jugador(actual, todos)
      if(jugador_correcto(actual, todos))  
        casilla=todos[actual].get_num_casilla_actual
        tirada=@tablero.calcular_tirada(casilla, @valor)
        nueva_pos=@tablero.nueva_posicion(casilla, tirada)
        todos[actual].mover_a_casilla(casilla)
        nueva_casilla=@tablero.get_casilla(nueva_pos)
        nueva_casilla.recibe_jugador(actual, todos)
        informe(actual, todos)
      end
    end
  
    def to_s
     "\n\t ¡SORPRESA! \n
       \n Has recibido: 
       \n #{@texto} \n"
    end
  end
end