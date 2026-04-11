package usaveis.escudos;

import usaveis.cartas.CartaEscudo;

/**
 * Representa uma carta de defesa que é completamente inútil,
 * com um custo de mana extremamente baixo e um valor de escudo zero.
 *
 * Sua implementação traz um aspécto importante ao combate,
 * pois haverá cartas que apenas ocuparão espaço na mão do jogador.
 *
 */
public class escudoQueNaoFazNada extends CartaEscudo {
  public escudoQueNaoFazNada() {
    super("Escudo Que Não Faz Nada", 0, 1, "Um escudo que comicamente não faz nada");
  }
}