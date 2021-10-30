# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'operaciones_juego'
require_relative 'operacion_inmobiliaria'
require_relative 'gestiones_inmobiliarias'
require_relative 'jugador'
require_relative 'diario'
require_relative 'civitas_juego'
require_relative 'respuestas'
require_relative 'titulo_propiedad'
require 'io/console'

module Civitas
  class Vista_textual
    def mostrar_estado(estado)
      puts estado
    end
    
    def pausa
      print "Pulsa una tecla"
      STDIN.getch
      print "\n"
    end

    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end

    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-#{l}"
        index += 1
      }
      opcion = lee_entero(lista.length,
                          "\n"+tab+" Elige una opcion: ",
                          tab+" Valor erroneo")
      return opcion
    end
    
    def set_civitas_juego(civitas)
         @juegoModel=civitas
         actualizar_vista
    end

    def actualizar_vista
      puts "#{@juegoModel.info_jugador_texto}
            \nCasilla -> #{@juegoModel.get_casilla_actual.to_s}"
    end
    
    def comprar
      respuestas=[Respuestas::SI, Respuestas::NO]
      opcion=menu(" Desea comprar la casilla actual?",["SI", "NO"])
      
      return(respuestas[opcion])
    end
    
    def salir_carcel
      opcion=menu("\nElige la forma para intentar salir de la carcel. \n", SalidasCarcel.lista_salidascarcel)
      salir=SalidasCarcel.lista_salidascarcel[opcion]
      return salir
    end

    def gestionar
      gestiones=[GestionesInmobiliarias::VENDER, GestionesInmobiliarias::HIPOTECAR, GestionesInmobiliarias::CANCELAR_HIPOTECA,
                 GestionesInmobiliarias::CONSTRUIR_CASA, GestionesInmobiliarias::CONSTRUIR_HOTEL, GestionesInmobiliarias::TERMINAR]
               
      opcion=menu("\nSeleccione gestion", gestiones)
      @iGestion=gestiones[opcion]
      
      calles=Array.new
      
      for i in (0..@juegoModel.get_jugador_actual.get_propiedades.length-1)
        calles<<@juegoModel.get_jugador_actual.get_propiedades.at(i).get_nombre
      end
      
      if(@iGestion!=Civitas::GestionesInmobiliarias::TERMINAR)
        @iPropiedad=menu("\nSobre que casilla desea realizar la gestion?", calles)
      end
    end

    def get_gestion
      gest=@iGestion
      
      return gest
    end

    def get_propiedad
      prop=@iPropiedad
      
      return prop
    end

    def mostrar_siguiente_operacion(operacion)
      case operacion
      when Civitas::Operaciones_juego::AVANZAR
        puts "Proxima operacion: Avanzar\n"
      when Civitas::Operaciones_juego::COMPRAR 
        puts "Proxima operacion: Comprar\n"
      when Civitas::Operaciones_juego::GESTIONAR
        puts "Proxima operacion: Gestionar\n"
      when Civitas::Operaciones_juego::PASAR_TURNO
        puts "Proxima operacion: Pasar turno\n"
      when Civitas::Operaciones_juego::SALIR_CARCEL
        puts "Proxima operacion: Salir de la carcel\n"
      end
    end

    def mostrar_eventos
      puts "\nDIARIO:"
      
      while(Diario.instance.eventos_pendientes)
        puts "\n-#{Diario.instance.leer_evento}"
      end
    end
  end
end