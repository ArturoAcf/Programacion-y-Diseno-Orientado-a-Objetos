# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'casilla'
require_relative 'tipo_casilla'

module Civitas
  class Tablero
    def initialize(carcel)
      @num_casilla_carcel=carcel
      @casillas=Array.new()
      @por_salida=0
      @tiene_juez=false
      salida=Casilla.new_descanso("Salida", TipoCasilla::DESCANSO)
      @casillas << salida
    end

    attr_accessor :num_casilla_carcel, :tiene_juez, :casillas
    attr_writer :por_salida

    def por_salida
      salida=@por_salida

      if(@por_salida>0)
        salida=@por_salida-1
      end

      return salida
    end
    
    def get_carcel
      carcel=@num_casilla_carcel
      
      return carcel
    end

    def get_casilla(num_casilla)
      casilla=Casilla.new(" ", nil, 0, 0, nil, nil)
      
      if(correcto(num_casilla))
        casilla=@casillas[num_casilla]
      end
      
      return casilla
    end
    
    def get_casillas
      cas=@casillas
      
      return cas
    end
    
    def aniade_casilla(casilla)
      if(@casillas.length==@num_casilla_carcel)
        carcel=Casilla.new_descanso("Carcel", TipoCasilla::DESCANSO)
        @casillas[@num_casilla_carcel]=carcel
      end
      
        @casillas.push(casilla)
        
      if(@casillas.length==@num_casilla_carcel)
        carcel=Casilla.new_descanso("Carcel", TipoCasilla::DESCANSO)
        @casillas[@num_casilla_carcel]=carcel
      end
    end

    def aniade_juez
      if(tiene_juez==false)
        juez=Casilla.new_juez(@num_casilla_carcel, "juez", TipoCasilla::JUEZ)
        @casillas.push(juez)
        @tiene_juez=true
      end
    end

    def nueva_posicion(actual, tirada)
      nueva_pos=0

      if(!correcto(actual))
        nueva_pos=-1
      else
        nueva_pos=actual+tirada
      end

      if(nueva_pos>=casillas.length)
        @por_salida+=1
        nueva_pos=nueva_pos%@casillas.length
      end

      return nueva_pos
    end

    def calcular_tirada(origen, destino)
      pos_final=0

      if destino-origen>=0
        pos_final=destino-origen
      else
        pos_final=destino-origen+@casillas.length
      end

      return pos_final
    end
    
    private 
    
    def correcto
      casilla_por_defecto=-1
      valido=false

      if(casilla_por_defecto>=0 && casilla_por_defecto<=casillas.length && tiene_juez)
        valido=true
      end

      return valido
    end

    def correcto(num_casilla)
      valido=false

      if(num_casilla>=0 && num_casilla<=casillas.length && tiene_juez)
        valido=true
      end

      return valido
    end
  end
end
