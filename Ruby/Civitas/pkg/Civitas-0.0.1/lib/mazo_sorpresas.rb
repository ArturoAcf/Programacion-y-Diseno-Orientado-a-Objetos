# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'diario'
require_relative 'sorpresa'

module Civitas
  class MazoSorpresas
    def initialize
      @sorpresas=Array.new
      @barajda=false
      @usadas=0
      @debug=false
      @cartas_especiales=Array.new
      @ultima_sorpresa=nil
      
      if(@debug==true)
        mensaje="Debug = true"
        Diario.instance.ocurre_evento(mensaje)
      end
    end
    
    def self.nuevo_mazo(db)
      new()
      @debug=db
    end
    
    attr_accessor :sorpresas, :barajada, :usadas, :debug, :ultima_sorpresa, :cartas_especiales

    def al_mazo(carta)
      if(barajada==false)
        sorpresas.push(carta)
      end
    end
    
    def siguiente
      if(barajada==false || usadas==sorpresas.length)
        if(!debug)
          @usadas=0
          sorpresas.shuffle
          @barajada=true
        end
      end
      
      @usadas+=1
      siguiente=sorpresas.first
      sorpresas.delete_at(0)
      sorpresas.push(siguiente)
      @ultima_sorpresa=siguiente
      
      return siguiente
    end
    
    def inhabilitar_carta_especial(sorpresa)
      for i in (0..@sorpresas.length)
        if(@sorpresas[i]==sorpresa)
          sorpresas.delete(sorpresa)
          cartas_especiales.push(sorpresa)
          mensaje="Se ha retirado la sorpresa del mazo"
          Diario.instance.ocurre_evento(mensaje)
        end
      end
    end
    
    def habilitar_carta_especial(sorpresa)
      for i in (0..@cartas_especiales.length)
        if(@cartas_especiales[i]==sorpresa)
          cartas_especiales.delete(sorpresa)
          sorpresas.push(sorpresa)
          mensaje="Se ha retirado la sorpresa del mazo"
          Diario.instance.ocurre_evento(mensaje)
        end
      end
    end
    
    def get_ultima_sorpresa
      ultima=@ultima_sorpresa
      
      return ultima
    end
  end
end
