package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;

/**
 * Representa a carta de dano "Espada Média" no jogo.
 * <p>
 * A Espada Média é uma carta de dano que causa 3 pontos de dano
 * com um custo de 3 pontos de mana.
 * </p>
 * @see usaveis.cartas.CartaDano
 */
public class espadaMedia extends CartaDano  {
  public espadaMedia() {
    super("Espada média", 3, 3, "Uma espada na média");
  }
}
