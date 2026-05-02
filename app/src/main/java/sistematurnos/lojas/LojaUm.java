package sistematurnos.lojas;

import dados.Dados;
import usaveis.cartas.Carta;
import usaveis.cartas.CartaDano;

public class LojaUm extends Loja {
  public LojaUm(Dados dados, String nome){
    super(dados, nome);

    NoLoja noAdicionar;
    Carta carta = new CartaDano("Obelisco mortal", 10, 10,
        "Um obelisco muito mortal");
  }
}
