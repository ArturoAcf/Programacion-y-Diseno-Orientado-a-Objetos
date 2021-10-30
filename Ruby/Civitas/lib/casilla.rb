# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'titulo_propiedad'
require_relative 'mazo_sorpresas'
require_relative 'sorpresa'
require_relative 'jugador'

module Civitas
  class Casilla
    def initialize(name)
      @nombre=name                         
      @@carcel=0
      @importe=0
      @mazo=nil
      @sorpresa=nil
      @titulo_propiedad=nil
    end
    
    attr_accessor :nombre, :importe, :mazo, :sorpresa, :titulo_propiedad, :carcel        #consultor y modificador

    
  def informe(actual, todos)
    print "#{todos[actual].nombre} ha caído en la casilla #{self.nombre}. \n\n"
  end
  
  
  def recibe_jugador (actual, todos)   
   informe(actual, todos)
  end
  
  public
  
  def jugador_correcto (actual, todos)
    aux = false
    
    if (actual>=0 && actual<todos.length)
      aux = true
    end
    return aux
  end
  
  
  def to_s
     "\n    DESCRIPCIÓN CASILLA: \n
      \n\t Nombre: #{@nombre}
      \n\t Tipo: DESCANSO
      "
  end
  
 
end

end