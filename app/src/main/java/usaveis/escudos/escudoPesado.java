package usaveis.escudos;

import usaveis.cartas.CartaEscudo;
/**
 * Representa a carta de defesa "Escudo Pesado" no jogo.
 *
 * <p>
 * O Escudo Pesado é uma carta de defesa que concede 4 pontos de escudo
 * com um custo de 4 pontos de energia.
 * </p>
 *
 * @see usaveis.cartas.CartaEscudo
 */
public class escudoPesado extends CartaEscudo {
  public escudoPesado() {
    super("Escudo Pesado", 4, 4, "Um escudo muito pesado");
  }
}