package usaveis.escudos;

import usaveis.cartas.CartaEscudo;

/**
 * Representa a carta de defesa "Escudo Pequeno" no jogo.
 *
 * <p>
 * O Escudo Pequeno é uma carta de defesa que concede 1 ponto de escudo
 * com um custo de 1 ponto de mana.
 * </p>
 *
 * @see usaveis.cartas.CartaEscudo
 */
public class escudoPequeno extends CartaEscudo {
  public escudoPequeno() {
    super("Escudo Pequeno", 1, 1, "Um escudo estranhamente pequeno");
  }
}
