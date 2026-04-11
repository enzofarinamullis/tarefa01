package usaveis.cartas.danos;
import usaveis.cartas.CartaDano;

/**
 * Representa a carta de dano "Espada Curta" no jogo.
 * <p>
 * A Espada Curta é uma carta de dano que causa 1 ponto de dano
 * com um custo de 1 ponto de mana.
 * </p>
 * @see usaveis.cartas.CartaDano
 */
public class espadaCurta extends CartaDano {
  public espadaCurta() {
    super("Espada Curta", 1, 1, "Uma espada curta até de mais");
    this.efeitos = null;
  }
}
