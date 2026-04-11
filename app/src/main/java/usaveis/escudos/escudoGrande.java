package usaveis.escudos;

import usaveis.cartas.CartaEscudo;

/**
  * Representa a carta de defesa "Escudo Grande" no jogo.
  *
  * <p>
  * O Escudo Grande é uma carta de defesa que concede 3 pontos de escudo
  * com um custo de 3 pontos de mana.
  * </p>
  *
  * @see usaveis.cartas.CartaEscudo
  */
public class escudoGrande extends CartaEscudo {
  public escudoGrande() {
    super("Escudo Grande", 3, 3, "Um escudo grande");
  }
}