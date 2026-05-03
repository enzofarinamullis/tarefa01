package sistematurnos.interfaces;

import dados.Dados;
import sistematurnos.lojas.NoLoja;
import usaveis.cartas.Carta;
import usaveis.cartas.CartaDano;
import usaveis.cartas.CartaEscudo;

import java.util.List;

public class LojaSegunda implements LojaStrategy{
  public void adicionarNos(List<NoLoja> nosDisponiveis, Dados dados){
    NoLoja noAdicionar;
    int custo = 0;
    Carta carta = new CartaDano("Monóculo Quebrado", 10, 10,
        "Ele está quebrado mas você pode rodá-lo para atacar os inimigos.");
    custo = 20;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);

    carta = new CartaDano("Panela de Pressão Elétrica", 4, 8,
        "Estranhamente a panela parece bem vedada e está" +
            " com muita pressão dentro.");
    custo = 10;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);

    carta = new CartaEscudo("Reprodutor de CD/DVD", 10, 8,
        "Você já sabe que a eletricidade ainda não foi descoberta, né? então" +
            " se vira para achar um uso pra isso.");
    custo = 10;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);
  }
}
