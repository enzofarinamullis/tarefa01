package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;

/**
 * Representa a carta de dano "A Espada Que Não Pode Ser Usada" no jogo.
 *
 * <p>
 *   A Espada Que Não Pode Ser Usada é uma carta de dano baseado no nível 1
 *   com um custo de 1.000.000 pontos de energia.
 *   </p>
 *
 *   @see usaveis.cartas.CartaDano
 */
public class espadaInusavel extends CartaDano {
  public espadaInusavel() {
    super("A Espada Que Não Pode Ser Usada", 1000000,1, "Uma espada que você nunca poderá usar" );
  }
}
