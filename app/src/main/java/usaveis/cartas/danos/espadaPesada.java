package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;

/**
 * Representa a carta de dano "Espada Pesada" no jogo.
 * <p>
 * A Espada Pesada é uma carta de dano que causa 5 pontos de dano
 * com um custo de 5 pontos de energia.
 * </p>
 * @see usaveis.cartas.CartaDano
 */
public class espadaPesada extends CartaDano {
  public espadaPesada() {
    super("Espada Pesada", 5,5, "Uma espada muito pesada" );
  }
}
