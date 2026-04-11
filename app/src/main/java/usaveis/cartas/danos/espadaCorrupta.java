package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Corrupcao;
import usaveis.cartas.efeitos.Sangramento;

/**
 * Representa a carta de dano "Espada Corrupta" no jogo.
 *
 * <p>
 *   A Espada Corrupta é uma carta de dano que causa 20 pontos de dano
 *   com um custo de 20 pontos de mana.
 *   </p>
 *   <p>
 *     Seu efeito adicional é a Corrupção I, que causa pontos de dano baseados
 *     no nível 10, com duração de 1 turno.
 *     </p>
 *
 *   @see usaveis.cartas.CartaDano
 *   @see usaveis.cartas.efeitos.Corrupcao
 */
public class espadaCorrupta extends CartaDano {
  public espadaCorrupta() {
    super("Espada Corrupta", 20,10, "Um dano altíssimo para um custo altíssimo" );
    Corrupcao corrupcao = new Corrupcao("Corrupção", 1, 10, 1);
    adicionarEfeito(corrupcao);
  }
}