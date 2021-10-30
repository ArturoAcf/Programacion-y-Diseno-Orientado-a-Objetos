# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
# 
# Nombre -> Arturo Alonso Carbonero  DNI -> 75936665-A
# Grupo -> 2ºC - C2
# 
# NOTA -> En las clases aparece "encoding: utf-8". Sin embargo, el error
#         relacionado con el uso de tildes persistía, por lo que las he eliminado.
#         
# NOTA 2 -> Al ejecutar, en ocasiones funciona correctamente y en ocasiones no.
#         El error que aparece cuando falla es, en: 
#         
#         undefined method `recibe_jugador' for nil:NilClass 
#         
#         Aparentemente este error no ha vuelto a aparecer, pero se lo quiero indicar ya que, aunque 
#         me perjudique en caso de que surja, simplemente volviendo a ejecutar se debería poder jugar.
#         
#         Además surgen en determinados métodos algunos errores relacionados con los tipos que no
#         he logrado resolver. A esto se le suman lo errores no detectados.
#
#         Le explico esto para ahorrar tiempo.

require_relative 'civitas_juego' 
require_relative 'dado'
require_relative 'vista_textual'
require_relative 'controlador'
require_relative 'jugador'

module Civitas
  class JuegoTexto
    vista=Vista_textual.new()

    jugadores=Array.new()
    jugadores.push("Arturo")
    jugadores.push("Felipe")
    jugadores.push("Javi")
    jugadores.push("Dani")
    juego=CivitasJuego.new(jugadores)

    Dado.instance.set_debug(true)

    controlador=Controlador.new(juego, vista)
    controlador.juega
  end
end
