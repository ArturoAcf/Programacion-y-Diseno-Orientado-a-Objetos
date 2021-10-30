# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'civitas_juego'
require_relative 'vista_textual'
require_relative 'operaciones_juego'
require_relative 'respuestas'

module Civitas
  class Controlador
    def initialize(juego, vista)
      @juego=juego
      @vista=vista
    end
    
    attr_accessor :juego, :vista
    
    def juega
      # Para ahorrar cantidad de escritura
      comprar=Operaciones_juego::COMPRAR
      gestionar=Operaciones_juego::GESTIONAR
      salir_carcel=Operaciones_juego::SALIR_CARCEL
      pasar_turno=Operaciones_juego::PASAR_TURNO
      si=Respuestas::SI
      
      @vista.set_civitas_juego(juego)
      # print "\nBIENVENIDO A CIVITAS!\n"
      @vista.pausa
      fin=@juego.final_del_juego
      
      while(!fin)
        @vista.actualizar_vista
        
        siguiente=@juego.siguiente_paso
        @vista.mostrar_siguiente_operacion(siguiente)
        
        if(siguiente!=pasar_turno)
          @vista.mostrar_eventos
        end
        
        if(!fin)
          if(siguiente==comprar)
              respuestas=@vista.comprar
            if(respuestas==si)
              @juego.comprar
              @juego.siguiente_paso_completo(comprar)
            else
              @juego.siguiente_paso_completo(comprar)
            end
          elsif(siguiente==gestionar)
             @vista.gestionar
             op=OperacionInmobiliaria.new(@vista.get_gestion, @vista.get_propiedad)
             
             if(op.get_gestion==Civitas::GestionesInmobiliarias::CANCELAR_HIPOTECA)
               @juego.cancelar_hipoteca(@vista.get_propiedad)

             elsif(op.get_gestion==Civitas::GestionesInmobiliarias::CONSTRUIR_CASA)
               @juego.construir_casa(@vista.get_propiedad)

             elsif(op.get_gestion==Civitas::GestionesInmobiliarias::CONSTRUIR_HOTEL)
               @juego.construir_hotel(@vista.get_propiedad)

             elsif(op.get_gestion==Civitas::GestionesInmobiliarias::HIPOTECAR)
               @juego.hipotecar(@vista.get_propiedad)

             elsif(op.get_gestion==Civitas::GestionesInmobiliarias::VENDER)
               @juego.vender(@vista.get_propiedad)

             elsif(op.get_gestion==Civitas::GestionesInmobiliarias::TERMINAR)
               @juego.siguiente_paso_completo(gestionar)
             end
          elsif(siguiente==salir_carcel)
              salida=@vista.salir_carcel
            if(salida==Civitas::SalidasCarcel::PAGANDO) 
              @juego.salir_carcel_pagando
            else
              @juego.salir_carcel_tirando
            end
            
            @juego.siguiente_paso_completo(salir_carcel)
          end
        end
      end
      
      ranking=@juego.ranking
      puts "\n\t-CLASIFICACION FINAL-\n"
      
      for i in(0..rank.length)
        puts "#{i+1} rank. #{ranking.get(i).get_nombre}"
      end
    end
  end
end
