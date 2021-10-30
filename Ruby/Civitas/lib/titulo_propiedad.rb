# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative "casilla"
require_relative "diario"
require_relative "jugador"

module Civitas
  class TituloPropiedad
    @@Factor_intereses_hipoteca=1.1
    
    def initialize(nombre, base, factor, hipoteca, compra, edificar)
      @nombre=nombre
      @alquiler_base=base
      @factor_revalorizacion=factor
      @hipoteca_base=hipoteca
      @precio_compra=compra
      @precio_edificar=edificar
      @num_casas=0
      @num_casas=0
      @num_hoteles=0
      @hipotecado=false
      @propietario
    end
    
    attr_reader   :nombre, :alquiler_base, :factor_revalorizacion, 
                  :hipoteca_base, :precio_compra, :precio_edificar
    attr_accessor :num_casas, :num_hoteles, :hipotecado, :propietario
    
    def actualizar_propietario_por_conversion(jugador)
      @propietario=jugador
    end
    
    def get_precio_alquiler
      if(hipotecdo==true || propietario_encarcelado)
        precio=0
      else
        precio=@alquiler_base*(1+@num_casas*0.5+@num_hoteles*2.5)
      end
      
      return precio
    end
    
    def get_importe_cancelar_hipoteca
      importe=@hipoteca_base*@@Factor_intereses_hipoteca
      
      return importe
    end
    
    def tramitar_alquiler(jugador)
      actualizar_propietario_por_conversion(jugador)
      if(propietario!=nil && propietario!=jugador)
        jugador.paga(get_precio_alquiler)
        @propietario.recibe(get_precio_alquiler)
      end
    end
    
    def propietario_encarcelado
      p_encarcelado=false
      
      if(tiene_propietario && propietario.encarcelado==true)
        p_encarcelado=true
      end 
      
      return p_encarcelado
    end
    
    private
    
    def es_este_el_propietario(jugador)
      actualizar_propietario_por_conversion(jugador)
      es=false
      
      if(propietario==jugador)
        es=true
      end
      
      return es
    end
    
    def get_importe_hipoteca
      importe=(@hipoteca_base*(1+(@num_casas*0.5)+(@num_hoteles*2.5)))
        
      return importe 
    end
    
    public 
    
    def get_hipotecado
      esta_hipotecado=@hipotecado
      
      return esta_hipotecado
    end
    
    def get_importe_cancelar_hipoteca
      importe_hipoteca=@hipoteca_base*@@Factor_intereses_hipoteca
      
      return importe_hipoteca
    end
    
    def get_precio_compra
      precio=@precio_compra
      
      return precio
    end
    
    def get_propietario
      p=Jugador.new(" ")
      p=@propietario
      
      return p
    end
    
    private
    
    def get_precio_venta
      venta=@precio_compra+@precio_edificar*cantidad_casas_hoteles*@factor_revalorizacion
      
      return venta
    end
    
    def get_precio_alquiler
      precio=@alquiler_base*(1+(@num_casas*0.5)+(@num_hoteles*2.5))
    
      if(hipotecado || propietario_encarcelado)
        precio=0
      end
      
      return precio
    end
    
    public 
    
    def get_precio_edificar
      precio=@precio_edificar
      
      return precio
    end
    
    def get_num_casas
      casas=@num_casas
      
      return casas
    end
    
    def cantidad_casas_hoteles
      cantidad=@num_casas+@num_hoteles
      
      return cantidad
    end
    
    def get_num_hoteles
      hoteles=@num_hoteles
      
      return hoteles
    end
    
    def get_nombre
      nom=@nombre
      
      return nom
    end
    
    def derruir_casas(n, jugador)
      derruidas=false
      
      if(propietario==jugador && num_casas>=n)
        @num_casas=@num_casas-n
        derruidas=true
      end
      
      return derruidas
    end
    
    def derruir_hoteles(n, jugador)
      derruidos=false
      
      if(propietario==jugador && num_hoteles>=n)
        @num_hoteles=@num_hoteles-n
        derruidos=true
      end
      
      return derruidos
    end
    
    def comprar(jugador)
      comprar=false
      
      if(!tiene_propietario)
        actualizar_propietario_por_conversion(jugador)
        comprar=true
        @propietario.paga(get_precio_compra)
      end
      
      return comprar
    end
    
    def vender(jugador)
      vendido=false
      
      if(propietario==jugador && hipotecado==false)
        actualizar_propietario_por_conversion(jugador)
        jugador.recibe(get_precio_venta)
        derruir_casas(num_casas, jugador)
        derruir_hoteles(num_hoteles, jugador)
        @propietario=nil
        vendido=true
      end
      
      return vendido
    end
    
    def hipotecar(jugador)
      hipotecar=false
      
      if(!hipotecado && es_este_el_propietario(jugador))
        actualizar_propietario_por_conversion(jugador)
        @propietario.recibe(get_importe_hipoteca)
        @hipotecado=true
        hipotecar=true
      end
      
      return hipotecar
    end
    
    def cancelar_hipoteca(jugador)
      cancelar=false
      
      if(hipotecado && es_este_el_propietario(jugador))
        actualizar_propietario_por_conversion(jugador)
        @propietario.paga(get_importe_cancelar_hipoteca)
        @hipotecado=true
        cancelar=true
      end
      
      return cancelar
    end
    
    def construir_casa(jugador)
      construir=false
      
      if(es_este_el_propietario(jugador))
        @propietario.paga(get_precio_edificar)
        @num_casas+=1
        construir=true
      end
      
      return construir
    end
    
    def constuir_hotel(jugador)
      construir=false
      
      if(es_este_el_propietario(jugador))
        @propietario.paga(get_precio_edificar)
        @num_hoteles+=1
        construir=true
      end
      
      return construir
    end
    
    private
    
    def propietario_encarcelado
      encarcelado=false
      
      if(propietario.get_encarcelado && tiene_propietario)
        encarcelado=true
      end
      
      return encarcelado
    end
    
    public
    
    def tiene_propietario
      tiene=false
      
      if(propietario!=nil)
        tiene=true
      end
      
      return tiene
    end
    
    def to_s
      puts "\n\t\t-Titulo en Propiedad-
            \n\tNombre: #{@nombre}
            \n\tPrecio de compra: #{@precio_compra}
            \n\tAlquiler base: #{@alquiler_base}
            \n\tHipoteca base: #{@hipoteca_base}
            \n\tPrecio de edificacion: #{@precio_edificar}
            \n\tNombre del propietario: #{@propietario}
            \n\tNumero de casas: #{@num_casas}
            \n\tNumero de hoteles: #{@num_hoteles}
            \n\tHipotecado: #{@hipotecado}"
    end
  end
end
