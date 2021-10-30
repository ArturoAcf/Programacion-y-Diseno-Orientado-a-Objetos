# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class SorpresaPorJugador < Sorpresa
    def initialize(valor, texto)
      super()
      @valor=valor
      @texto=texto
      
     # puts "Sorpresa PorJugador creada. \n"    
    end
    
    # Método sobrescrito
    def aplicar_a_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        informe(actual, todos)
      
        sorp=SorpresaPagarCobrar.new(valor*(-1), "Cobrar")
      
        for i in (0..actual) do
          sorp.aplicar_a_jugador(i, todos)
        end
      
        for j in (actual+1..todos.length) do
          sorp.aplicar_a_jugador(j, todos)
        end
      
        sorp2=Sorpresa_PagarCobrar.new(valor*(todos.length - 1), "Recibir")
        sorp2.aplicar_a_jugador(actual, todos)
      end
    end
    
    def to_s
      "\n\t ¡SORPRESA! \n
       \n Has recibido: 
       \n #{@texto} \n"
    end
  end
end