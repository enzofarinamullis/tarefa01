package usaveis.escudos;

import usaveis.cartas.CartaEscudo;

/**
 * Representa uma carta de defesa que é completamente inútil,
 * com um custo de mana extremamente alto e um valor de escudo ridiculamente baixo.
 *
 * Sua implementação traz um aspécto importante ao combate,
 * pois haverá cartas que apenas ocuparão espaço na mão do jogador, e não poderão ser usadas.
 */
public class escudoInusavel extends CartaEscudo {
  public escudoInusavel() {
    super("O Escudo Que Não Pode Ser Usado", 1, 1000000, "Um escudo que não pode ser usado");
  }
}
