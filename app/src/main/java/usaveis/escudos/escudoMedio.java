package usaveis.escudos;

import usaveis.cartas.CartaEscudo;

/**
 * Representa a carta de defesa "Escudo Médio" no jogo.
 *
 * <p>
 * O Escudo Médio é uma carta de defesa que concede 2 pontos de escudo
 * com um custo de 2 pontos de mana.
 * </p>
 *
 * @see usaveis.cartas.CartaEscudo
 */
public class escudoMedio extends CartaEscudo {
  public escudoMedio() {
    super("Escudo Médio", 2, 2, "Um escudo na na média");
  }
}