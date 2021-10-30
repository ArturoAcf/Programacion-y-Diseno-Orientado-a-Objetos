# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'mazo_sorpresas'
require_relative 'tablero'
require_relative 'jugador'

module Civitas 
  #clase abstracta, no se generan sorpresas de esta clase
  class Sorpresa
     
  def initialize
    @texto=""
    @valor=-1
    @mazo=nil
    @tablero=nil        
  end
    
  def jugador_correcto(actual, todos)
    aux=false
     
    if(actual>=0 && actual<=3 && !todos.empty?)
      todos[actual]
      aux=true
    end
  
    return aux
  end
  
   
  def informe(actual, todos)
    if(jugador_correcto(actual, todos))
      Diario.instance.ocurre_evento("Al jugador/a #{todos[actual].nombre} se le ha aplicado una sorpresa. \n")
    end
  end
  
  #Metodo abstracto
  def aplicar_a_jugador (actual, todos)
    
  end
 end
end

