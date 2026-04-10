package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Corrupcao;
import usaveis.cartas.efeitos.Sangramento;

public class espadaCorrupta extends CartaDano {
  public espadaCorrupta() {
    super("Espada Corrupta", 20,10, "Um dano altíssimo para um custo altíssimo" );
    Corrupcao corrupcao = new Corrupcao("Corrupção", 1, 10, 1);
    adicionarEfeito(corrupcao);
  }
}