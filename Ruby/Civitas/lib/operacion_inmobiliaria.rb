# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

# encoding: utf-8
require_relative 'gestiones_inmobiliarias'

module Civitas
  class OperacionInmobiliaria
    def initialize(gest, ip)
      @gestion=gest
      @num_propiedad=ip
    end
    
    def get_gestion
      gest=@gestion
      
      return gest
    end
    
    def get_num_propiedad
      ip=@num_propiedad
      
      return ip
    end
  end
end
