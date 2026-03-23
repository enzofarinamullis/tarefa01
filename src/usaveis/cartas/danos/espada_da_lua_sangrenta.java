package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;


public class espada_da_lua_sangrenta extends CartaDano{
  public espada_da_lua_sangrenta() {
    super("Espada da Lua Sangrenta", 5,5, "Espada com magia sangrenta" );
    Sangramento sangramentoV = new Sangramento("Sangramento V", 5, 10, 5);
    adicionarEfeito(sangramentoV);
  }
  
}
