package sistematurnos.lojas;

import dados.Dados;
import sistematurnos.interfaces.LojaInicialStrategy;
import usaveis.cartas.Carta;
import usaveis.cartas.CartaDano;
import usaveis.cartas.CartaEscudo;

public class LojaUm extends Loja {
  public LojaUm(Dados dados){
    super(dados, "Loja Inicial", new LojaInicialStrategy());
  }
}
