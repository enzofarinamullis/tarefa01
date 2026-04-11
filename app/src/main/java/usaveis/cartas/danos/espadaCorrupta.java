package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Corrupcao;
import usaveis.cartas.efeitos.Sangramento;

/**
 * Representa a carta de dano "Espada Corrupta" no jogo.
 * <p>
 * A Espada Corrupta é uma carta de dano forte que causa 20 pontos de dano
 * com um custo de 10 pontos de mana. Além do dano instantâneo, ela aplica o efeito
 * de Corrupção, causando dano adicional ao longo do tempo.
 * </p>
 * @see usaveis.cartas.CartaDano
 * @see usaveis.cartas.efeitos.Corrupcao
 */
public class espadaCorrupta extends CartaDano {
  public espadaCorrupta() {
    super("Espada Corrupta", 20,10, "Um dano altíssimo para um custo altíssimo" );
    Corrupcao corrupcao = new Corrupcao("Corrupção", 1, 10, 1);
    adicionarEfeito(corrupcao);
  }
}