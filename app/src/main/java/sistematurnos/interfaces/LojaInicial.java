package sistematurnos.interfaces;

import dados.Dados;
import sistematurnos.lojas.NoLoja;
import usaveis.cartas.Carta;
import usaveis.cartas.CartaDano;
import usaveis.cartas.CartaEscudo;

import java.util.List;

public class LojaInicial implements LojaStrategy{
  @Override
  public void adicionarNos(List<NoLoja> nosDisponiveis, Dados dados){
    NoLoja noAdicionar;
    int custo = 0;
    Carta carta = new CartaDano("Toalha", 10, 10,
        "Uma arma letal. (curiosidade: o dia da toalha é o dia 25 de maio)." +
            " (PS: O Douglas Adams não existe neste universo).");
    custo = 10;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);

    carta = new CartaDano("Frigideira Anti-Aderente", 3, 6,
        "Uma frigideira anti-aderente muito pesada e com o teflon saindo.");
    custo = 5;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);

    carta = new CartaEscudo("Torradeira elétrica", 10, 10,
        "No nosso universo a eletricidade ainda não foi descoberta, então você" +
            " só pode usá-la para defesa pessoal.");
    custo = 5;
    noAdicionar = new NoLoja(carta, custo);
    nosDisponiveis.add(noAdicionar);
  }
}
