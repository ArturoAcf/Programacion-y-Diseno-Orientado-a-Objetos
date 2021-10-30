# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require 'singleton'
require_relative 'diario'

module Civitas
  class Dado
    include Singleton 
    @random
    @ultimoresultado
    @debug
    @@Salida_carcel=5
    
    def initialize
      @random=Random.new
      @ultimoresultado=0
      @debug=false
    end
    
    attr_reader :Salida_carcel, :debug
    attr_accessor :ultimo_resultado
    
    def tirar
      if(!debug)
        tirada=@random.rand(1..6)
        @ultimoresultado=tirada
      else
        tirada=1
        @ultimoresultado=1
      end
      
      return tirada
    end
    
    def salgo_de_la_carcel
      aux=false
      
      if(ultimo_resultado>=Salida_carcel)
        aux=true
      end
      
      return aux
    end
    
    def quien_empieza(n)
      jugador_aleatorio=@random.rand(n)
      return jugador_aleatorio
    end
    
    def set_debug(db)
      @debug=db
      estado=" "
      
      if(!debug)
        estado="Debug desactivado"
        Diario.instance.ocurre_evento(estado)
      else
        estado="Debug activado"
        Diario.instance.ocurre_evento(estado)
      end
    end
  end
end
