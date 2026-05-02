package sistematurnos.lojas;

import dados.Dados;
import usaveis.cartas.Carta;
import usaveis.cartas.CartaDano;
import usaveis.cartas.CartaEscudo;

public class LojaUm extends Loja {
  public LojaUm(Dados dados){
    super(dados, "Loja Inicial");
  }

  protected void adicionarNos(){
    NoLoja noAdicionar;
    int custo = 0;
    Carta carta = new CartaDano("Obelisco mortal", 10, 10,
        "Um obelisco muito mortal");
    custo = 10;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);

    carta = new CartaEscudo("Escudo", 10, 10, "Um escudo");
    custo = 3;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);

    carta = new CartaEscudo("asd", 10, 10, "asd");
    custo = 1;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);
  }
}
