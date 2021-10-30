# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  class SorpresaSalirCarcel < Sorpresa
    def initialize(mazo)
      super()
      @texto="Carta para evitar la cárcel. \n"
      @mazo=mazo
  
      #puts "Sorpresa SalirCarcel creada."
    end
    
    # Método sobrescrito
    def aplicar_a_jugador(actual, todos)
      if(jugador_correcto(actual, todos))
        informe(actual, todos)
        
        no_salvoc=true
        
        for i in (0..todos.length)
          if(todos[i].tiene_salvoconducto)
            no_salvoc=false
          end
        end
        
        if(no_salvoc)
          todos[actual].obtener_salvoconducto(self)
          salir_del_mazo
        end
      end
    end
    
    def salir_del_mazo
        self.mazo.habilitar_carta_especial(self)
    end
  
    def usada
        self.mazo.habilitar_carta_especial(self)
    end
       
    def to_s
      "\n\t ¡SORPRESA! \n
       \n Has recibido: 
       \n #{@texto} \n"
    end
  end
end