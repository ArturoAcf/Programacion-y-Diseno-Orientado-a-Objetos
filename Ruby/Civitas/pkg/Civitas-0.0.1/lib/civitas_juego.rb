# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'jugador'
require_relative 'mazo_sorpresas'
require_relative 'tablero'
require_relative 'estados_juego'
require_relative 'gestor_estados'
require_relative 'casilla'
require_relative 'casilla_calle'
require_relative 'casilla_impuesto'
require_relative 'casilla_juez'
require_relative 'casilla_sorpresa'

module Civitas
  class CivitasJuego
    private 
    def initialize(nombres)
      @mazo=MazoSorpresas.new()
      @tablero=Tablero.new(5)
      @gestor_estados=Gestor_estados.new()
      @estado=@gestor_estados.estado_inicial
      
      @jugadores=Array.new()
      i=0
      while(i<nombres.length)
        jugador=Jugador.new(nombres[i])
        @jugadores.push(jugador)
        i+=1
      end
      
      @indice_jugador_actual=Dado.instance.quien_empieza(@jugadores.length)
    
      inicializar_tablero(@mazo)
      inicializar_mazo_sorpresas(@tablero)
    end
    
    attr_accessor :indice_jugador_actual, :mazo, :tablero, :estado, :gestor_estados, :jugadores
  
    def inicializar_tablero(mazo)
      parking=Casilla.new("Parking")
      impuesto=CasillaImpuesto.new("Impuesto", 100)
      
      t1=TituloPropiedad.new("Calle 1", 400, 1.5, 200, 2000, 1500)
      t2=TituloPropiedad.new("Calle 2", 400, 1.5, 200, 2000, 1500)
      t3=TituloPropiedad.new("Calle 3", 400, 1.5, 200, 2000, 1500)
      t4=TituloPropiedad.new("Calle 4", 400, 1.5, 200, 2000, 1500)
      t5=TituloPropiedad.new("Calle 5", 400, 1.5, 200, 2000, 1500)
      t6=TituloPropiedad.new("Calle 6", 400, 1.5, 200, 2000, 1500)
      t7=TituloPropiedad.new("Calle 7", 400, 1.5, 200, 2000, 1500)
      t8=TituloPropiedad.new("Calle 8", 400, 1.5, 200, 2000, 1500)
      t9=TituloPropiedad.new("Calle 9", 400, 1.5, 200, 2000, 1500)
      t10=TituloPropiedad.new("Calle 10", 400, 1.5, 200, 2000, 1500)
      t11=TituloPropiedad.new("Calle 11", 400, 1.5, 200, 2000, 1500)
      t12=TituloPropiedad.new("Calle 12", 400, 1.5, 200, 2000, 1500)
      
      calle1=CasillaCalle.new(t1)
      calle2=CasillaCalle.new(t2)
      calle3=CasillaCalle.new(t3)
      calle4=CasillaCalle.new(t4)
      calle5=CasillaCalle.new(t5)
      calle6=CasillaCalle.new(t6)
      calle7=CasillaCalle.new(t7)
      calle8=CasillaCalle.new(t8)
      calle9=CasillaCalle.new(t9)
      calle10=CasillaCalle.new(t10)
      calle11=CasillaCalle.new(t11)
      calle12=CasillaCalle.new(t12)
      
      sorpresa1=CasillaSorpresa.new(mazo, "Sorpresa 1")
      sorpresa2=CasillaSorpresa.new(mazo, "Sorpresa 2")
      sorpresa3=CasillaSorpresa.new(mazo, "Sorpresa 3")
      
      @tablero.aniade_casilla(calle1)
      @tablero.aniade_casilla(calle2)
      @tablero.aniade_casilla(calle3)
      @tablero.aniade_casilla(calle4)
      @tablero.aniade_casilla(calle5)
      @tablero.aniade_casilla(impuesto)
      @tablero.aniade_casilla(calle6)
      @tablero.aniade_casilla(sorpresa1)
      @tablero.aniade_casilla(calle7)
      @tablero.aniade_casilla(calle8)
      @tablero.aniade_casilla(parking)
      @tablero.aniade_casilla(sorpresa2)
      @tablero.aniade_casilla(calle9)
      @tablero.aniade_casilla(calle10)
      @tablero.aniade_juez
      @tablero.aniade_casilla(calle11)
      @tablero.aniade_casilla(calle12)
      @tablero.aniade_casilla(sorpresa3)
    end
    
    def inicializar_mazo_sorpresas(tablero)
      pagar_cobrar1=SorpresaPagarCobrar.new(800, "Recibes 800 coronas.")
      pagar_cobrar2=SorpresaPagarCobrar.new(-800, "Tienes que pagar 800 yenes.")
      ir_a_casilla1=SorpresaIrACasilla.new(tablero, 0, "Vas a la salida.")
      ir_a_casilla2=SorpresaIrACasilla.new(tablero, 9, "Ve a la casilla sorpresa mas cercana a la salida." )
      ir_carcel=SorpresaIrCarcel.new(tablero)
      por_casa_hotel1=SorpresaPorCasaHotel.new(-100, "Por cada casa/hotel pagas 100 dolares")
      por_casa_hotel2=SorpresaPorCasaHotel.new(150, "Por cada casa/hotel recibes 150 monedas")
      cobrar_de_jugador=SorpresaPorJugador.new(200, "recibeas 200 rupias")
      pagar_jugador=SorpresaPorJugador.new(-200, "Pagas 200 doblones de oro")
      salir_carcel=SorpresaSalirCarcel.new(mazo)
      jug_espc=SorpresaJugadorEspeculador.new(1000)
      
      @mazo.al_mazo(pagar_cobrar1)
      @mazo.al_mazo(pagar_cobrar2)
      @mazo.al_mazo(ir_a_casilla1)
      @mazo.al_mazo(ir_a_casilla2)
      @mazo.al_mazo(ir_carcel)
      @mazo.al_mazo(por_casa_hotel1)
      @mazo.al_mazo(por_casa_hotel2)
      @mazo.al_mazo(pagar_jugador)
      @mazo.al_mazo(cobrar_de_jugador)
      @mazo.al_mazo(salir_carcel)
      @mazo.al_mazo(jug_espc)
    end
    
    public
    
    def get_jugador_actual
      jug=@jugadores[@indice_jugador_actual]
      
      return jug
    end
    
    def construir_casa(ip)
      @jugadores[@indice_jugador_actual].construir_casa(ip)
    end
    
    def construir_hotel(ip)
      @jugadores[@indice_jugador_actual].construir_hotel(ip)
    end
    
    def info_jugador_texto
      return get_jugador_actual.to_string
    end
    
    def pasar_turno
      @indice_jugador_actual=(@indice_jugador_actual+1)%@jugadores.length
    end
    
    def cosntruir_casa(ip)
      construir=false
      
      if(get_jugador_actual.construir_casa(ip))
        construir=true
      end
      
      return construir
    end
    
    def get_casilla_actual
      casilla_actual=Casilla.new(" ", nil, 0, 0, nil, nil) # Esto ha solucionado aparentemente el error 
                                                           # que le indico al inicio de juego_texto.rb
      casilla_actual=@tablero.get_casilla(get_jugador_actual.get_num_casilla_actual)
      
      return casilla_actual
    end
    
    def cosntruir_hotel(ip)
      construir=false
      
      if(get_jugador_actual.construir_hotel(ip))
        construir=true
      end
      
      return construir
    end
    
    def comprar
      comprar=false
      
      jugador_actual=get_jugador_actual
      pos=jugador_actual.get_num_casilla_actual
      
      casilla=@tablero.get_casilla(pos)
      titulo=casilla.get_titulo_propiedad
      comprar=jugador_actual.comprar(titulo)
      
      return comprar
    end
    
    def vender(ip)
      get_jugador_actual.vender(ip)
      return true
    end
    
    def hipotecar(ip)
      get_jugador_actual.hipotecar(ip)
      return true
    end
    
    def cancelar_hipoteca(ip)
      get_jugador_actual.cancelar_hipoteca(ip)
      return true
    end
    
    def salir_carcel_pagando
      salir_pagando=false
      
      if(get_jugador_actual.salir_carcel_pagando)
        salir_pagando=true
      end
      
      return salir_pagando
    end
    
    def salir_carcel_tirando
      salir_tirando=false
      
      if(get_jugador_actual.salir_carcel_tirando)
        salir_tirando=true
      end
      
      return salir_tirando
    end
    
    def pasar_turno
      if(indice_jugador_actual==@jugadores.length-1)
        @indice_jugador_actual=0
      else
        @indice_jugador_actual=@indice_jugador_actual+1
      end
    end
    
    def final_del_juego
      fin=false
      
      i=0
      while(i<@jugadores.length)
        if(@jugadores[i].en_banca_rota)
          fin=true
        end
        i+=1
      end
      
      return fin
    end
    
    def contabilizar_pasos_por_salida(actual)
      while(tablero.por_salida>0)
        actual.pasa_por_salida
      end
    end
    
    def siguiente_paso_completo(operacion)
      @estado=gestor_estados.siguiente_estado(get_jugador_actual, @estado, operacion)
    end

    def avanza_jugador
      jugador_actual=get_jugador_actual
      posicion_actual=jugador_actual.get_num_casilla_actual
      tirada=Dado.instance.tirar
      
      posicion_nueva=@tablero.nueva_posicion(posicion_actual, tirada)
      casilla=Casilla.new(" ", nil, 0, 0, nil, nil)
      casilla=@tablero.get_casilla(posicion_nueva) # Es aquí donde sucede el error que le comento 
                                                   # en el inicio de juego_texto.rb. ctrl+puntero 
                                                   # indica que get_casilla es de tipo casilla, 
                                                   # pero en algunas ocasiones (pocas) netbeans indica
                                                   # que el método recibe_jugador (línea 268)
                                                   # no está definido para nil.
      contabilizar_pasos_por_salida(jugador_actual)
      
      jugador_actual.mover_a_casilla(posicion_nueva)
      casilla.recibe_jugador(@indice_jugador_actual, @jugadores)
      
      contabilizar_pasos_por_salida(jugador_actual)
    end
    
    def siguiente_paso
      jugador_actual=get_jugador_actual
      operacion=gestor_estados.operaciones_permitidas(jugador_actual, estado)
      
      if(operacion==Operaciones_juego::PASAR_TURNO)
        puts "Cambiando de jugador...\n"               
        pasar_turno
        siguiente_paso_completo(operacion)
      elsif(operacion==Operaciones_juego::AVANZAR)
        avanza_jugador
        siguiente_paso_completo(operacion)
      end
      
      return operacion
    end
    
    def ranking
      j=@jugadores
      j.sort
      
      return j
    end
  end
end
