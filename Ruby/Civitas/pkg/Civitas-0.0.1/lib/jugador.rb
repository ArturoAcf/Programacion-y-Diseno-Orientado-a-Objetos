# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'tablero'
require_relative 'diario'
require_relative 'dado'
require_relative 'titulo_propiedad'
require_relative 'sorpresa'

module Civitas
  class Jugador
    def initialize(nombre)
      @nombre=nombre
      @encarcelado=false
      @num_casilla_actual=0
      @puede_comprar=false
      @@Casas_max=4
      @@Casas_por_hotel=4
      @@Hoteles_max=4
      @@Paso_por_salida=1000
      @@Precio_libertad=200
      @@Saldo_inicial=7500
      @saldo=@@Saldo_inicial
      
      @propiedades=[]
      @salvoconducto
    end
    
    attr_reader  :Casas_max, :Casas_por_hotel, :Hoteles_max, :Paso_por_salida, :Precio_libertad, :Saldo_inicial
    attr_accessor :nombre, :encarcelado, :num_casilla_actual, :puede_comprar, :saldo, :propiedades, :salvoconducto
    
    protected
    
    def copia(otro)
      @nombre=otro.nombre
      @encarcelado=otro.encarcelado
      @num_casilla_actual=otro.num_casilla_actual
      @puede_comprar=otro.puede_cpmprar
      @saldo=otro.saldo
    end
    
    public
    
    def debe_ser_encarcelado
      debe=false
      
      if(!encarcelado)
        if(!tiene_salvoconducto)
          debe=true
          mensaje="El jugador debe ser encarcelado"
          Diario.instance.ocurre_evento(mensaje)
        else
          perder_salvoconducto
          debe=false
          mensaje="El jugador se libra de ser encarcelado"
          Diario.instance.ocurre_evento(mensaje)
        end
      end
      
      return debe
    end
    
    def get_nombre
      nom=@nombre
      
      return nom
    end
    
    def encarcelar(num_casilla_carcel)
      encarcelar=@encarcelado
      
      if(debe_ser_encarcelado==true && @num_casilla_actual==num_casilla_carcel)
        self.mover_a_casilla(num_casilla_carcel)
        @encarcelado=true
        encarcelar=@encarcelado
        mensaje="Jugador encarcelado"
        Diario.instance.ocurre_evento(mensaje)
      end
      
      return encarcelar
    end
    
    def obtener_salvoconducto(s)
      obtener=false
      
      if(!encarcelado)
        @salvoconducto=s
        obtener=true
      end
      
      return obtener
    end
    
    def perder_salvoconducto
      @salvoconducto.usada()
      @salvoconducto=nil
    end
    
    def tiene_salvoconducto
      tiene=false
      
      if(salvoconducto!=nil)
        tiene=true
      end
      
      return tiene
    end
    
    def puede_comprar_casilla
      puede=false
      
      if(encarcelado)
        @puede_comprar=false
        puede=@puede_comprar
      else
        @puede_comprar=true
        puede=@puede_comprar
      end
      
      return puede
    end
    
    def paga(cantidad)
      paga=modificar_saldo(cantidad*(-1))
      return paga
    end
    
    def paga_impuesto(cantidad)
      paga=false
      
      if(!encarcelado)
        paga=paga(cantidad)
      end
      
      return paga
    end
    
    def paga_alguiler
      paga=false
      
      if(!encarcelado)
        paga=paga(cantidad)
      end
      
      return paga
    end
    
    def recibe(cantidad)
      recibe=false
      
      if(!encarcelado)
        recibe=modificar_saldo(cantidad)
      end
      
      return recibe
    end
    
    def modificar_saldo(cantidad)
      @saldo=@saldo+cantidad
      mensaje="Saldo modificado correctamente, nuevo saldo -> #{@saldo}"
      Diario.instance.ocurre_evento(mensaje)
      return true
    end
    
    def get_propiedades
      p=@propiedades
      
      return p
    end
    
    public
    
    def get_saldo
      s=@saldo
      
      return s
    end
    
    def mover_a_casilla(num_casilla)
      mover=false
      
      if(!encarcelado)
        @num_casilla_actual=num_casilla
        @puede_comprar=false
        mensaje="\nEl jugador avanza a #{num_casilla}"
        Diario.instance.ocurre_evento(mensaje)
        mover=true
      end
      
      return mover
    end
    
    def get_num_casilla_actual
      casilla=@num_casilla_actual
      
      return casilla
    end
    
    def get_encarcelado
      encar=@encarcelado
      
      return encar
    end
    
    def puedo_gastar(precio)
      puedo=false
      
      if(!encarcelado && saldo>=precio)
        puedo=true
      end
      
      return puedo
    end
    
    def comprar(titulo)
      comprar=false
      
      if(!encarcelado && puede_comprar)
          precio=titulo.get_precio_compra
        if(puedo_gastar(precio))
            comprar=titulo.comprar(self)
           if(comprar)
             @propiedades.push(titulo)
             Diario.instance.ocurre_evento("\n#{@nombre} ha comprado #{titulo.get_nombre}")
           end
        end
      end
      
      return comprar
    end
    
    def vender(ip)
      vender=false
      
      if(!encarcelado && existe_la_propiedad(ip))
        @propiedades.delete_at(ip)
        vender=true
      end
      
      return vender
    end
    
    def hipotecar(ip)
      hipotecar=false
      
      if(!encarcelado && existe_la_propiedad(ip))
        propiedad=@propiedades[ip]
        hipotecar=propiedad.hipotecar(self)
      end
      
      if(hipotecar)
        Diario.instance.ocurre_evento("#{@nombre} Ha hipotecado la propiedad")
      end
      
      return hipotecar
    end
    
    def cantidad_casas_hoteles # Revisar 
      ch=0
      
      for i in (0..@propiedades.length)
        ch+=@propiedades[i].cantidad_casas_hoteles
      end
      
      return ch
    end
    
    def cancelar_hipoteca(ip)
      cancelar=false
      
      if(existe_la_propiedad(ip) && !encarcelado)
        propiedad=@propiedades[ip]
        cantidad=propiedad.get_importe_cancelar_hipoteca
        puedo_gastar=puedo_gastar(cantidad)
        
        if(puedo_gastar)
          cancelar=propiedad.cancelar_hipoteca(self)
        end
        
        if(cancelar)
          Diario.instance.ocurre_evento("#{@nombre} Ha cancelado la hipoteca")
        end
      end
      
      return cancelar
    end
    
    def en_banca_rota
      ebr=false
      
      if(saldo<1)
        ebr=true
      end
      
      return ebr
    end
    
    def tiene_algo_que_gestionar
      tiene=false
      
      if(propiedades.length>0)
        tiene=true
      end
      
      return tiene
    end
    
    def puede_salir_carcel_pagando
      puede=false
      
      if(saldo>get_precio_libertad)
        puede=true
      end
      
      return puede
    end
    
    def salir_carcel_pagando
      salir=false
      
      if(encarcelado && puede_salir_carcel_pagando)
        self.paga(get_precio_libertad)
        @encarcelado=false
        mensaje="El jugador "+@nombre+" ha salido de la carcel pagando"
        Diario.instance.ocurre_evento(mensaje)
        salir=true
      end
      
      return salir
    end
    
    def salir_carcel_tirando
      salir=false
      
      if(Dado.instance.salgo_de_la_carcel)
        salir=true
        mensaje="El jugador "+@nombre+" ha salido de la carcel tirando"
        Diario.instance.ocurre_evento(mensaje)      
      end
      
      return salir
    end
    
    private 
    
    def existe_la_propiedad(ip)
      existe=false
      
      for i in (0..@propiedades.length)
        if(propiedades[i]==propiedades[ip])
          existe=true
        end
      end
      
      return existe
    end
    
    def get_premio_paso_salida
      premio=@@Paso_por_salida
      
      return premio
    end
    
    def get_precio_libertad
      precio=@@Precio_libertad
      
      return precio
    end
    
    public
    
    def pasa_por_salida
      modificar_saldo(get_premio_paso_salida)
      mensaje="El jugador "+@nombre+" ha pasado por salida y ha obtenido "+@@Paso_por_salida+" euros"
      Diario.instance.ocurre_evento(mensaje)
      return true
    end
    
    def puedo_edificar_casa(propiedad)
      existe=false
      puedo=false
      
      i=0
      while(i<@propiedades.length)
        if(@propiedades[i].get_propietario==@propiedades[propiedad].get_propietario)
          existe=true
        end
        i+=1
      end
      
      if(existe && propiedad.get_num_casas<@@Casas_max)
        puedo=true
      end
      
      return puedo
    end
    
    def puedo_edificar_hotel(propiedad)
      existe=false
      puedo=false
      
      for i in (0..@propiedades.length)
        if(@propiedades[i].get_propietario==@propiedades[propiedad].get_propietario)
          existe=true
        end
      end
      
      if(existe && propiedad.get_num_hoteles<@@Hoteles_max)
        puedo=true
      end
      
      return puedo
    end
    
    def construir_casa(ip)
      construir=false
      
      p=@propiedades[ip]
      puedo=puedo_edificar_casa(p)
      precio=p.get_precio_edificar
      
      if(puedo_gastar(precio && puedo_edificar_casa(ip)))
        puedo=true
      end
      if(puedo)
        construir=true
        Diario.instance.ocurre_evento("\n#{@nombre} ha construido una casa eh #{p}")
      end
      
      return construir
    end
    
    def construir_hotel(ip)
      construir=false
      
      p=@propiedades[ip]
      puedo=puedo_edificar_hotel(p)
      precio=p.get_precio_edificar
      
      if(puedo_gastar(precio && puedo_edificar_hotel(ip)))
        puedo=true
      end
      if(puedo)
        construir=true
        Diario.instance.ocurre_evento("\n#{@nombre} ha construido un hotel eh #{p}")
      end
      
      return construir
    end
    
    def puedo_edificar_hotel(titulo)
      puedo=false
      precio=titulo.get_precio_edificar
      
      if(puedo_gastar(precio) && titulo.get_num_hoteles<@@Hoteles_max && titulo.get_num_casas>=@@Casas_por_hotel)
        puedo=true
      end
      
      return puedo
    end
    
    def construir_hotel(ip)
      construir=false
      
      if(!encarcelado && existe_la_propiedad(ip))
        propiedad=@propiedades[ip]
        puedo=puedo_edificar_hotel(propiedad)
        precio=propiedad.get_precio_edificar
        
        if(puedo_gastar(precio) && propiedad.get_num_hoteles<@@Hoteles_max && propiedades.get_num_casas>=@@Casas_por_hotel)
          puedo=true
        end
        
        if(puedo)
          construir=propiedad.construir_hotel(self)
          propiedad.derruir_casas(@@Casas_por_hotel, self)
        end
        
        Diario.instance.ocurre_evento("#{@nombre} Ha construido un hotel")
      end
      
      return construir
    end
    
    def compare_to(otro)
      return self.saldo <=> otro.saldo
      # Si el saldo del primer jugador es menor, devuelve -1
      # Si el saldo de ambos jugadores es igual, devuelve 0
      # Si el saldo del segundo jugador es menor, devuelve 1
    end
    
    def to_string
      puts "Nombre -> #{@nombre}
            Encarcelado -> #{@encarcelado}
            Saldo -> #{@saldo}
            Casilla actual -> #{@num_casilla_actual}"
    end
  end
end

