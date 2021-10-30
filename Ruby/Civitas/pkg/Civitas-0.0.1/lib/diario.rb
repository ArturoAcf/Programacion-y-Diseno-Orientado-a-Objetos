# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require 'singleton'

module Civitas
  class Diario
    include Singleton

    def initialize
      @eventos = []
    end

    def ocurre_evento(e)
      @eventos << e
    end

    def eventos_pendientes
      return (@eventos.length > 0)
    end

    def leer_evento
      e = @eventos.shift
      return e
    end

  end
end