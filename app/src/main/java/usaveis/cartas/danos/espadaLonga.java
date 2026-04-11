package usaveis.cartas.danos;
import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;

/**
 * Representa a carta de dano "Espada Longa" no jogo.
 * <p>
 * A Espada Longa é uma carta de dano que causa 3 pontos de dano
 * com um custo de 3 pontos de mana.
 * </p>
 * <p>
 * Seu efeito adicional é o Sangramento I, que causa 2 pontos de dano por turno
 * durante 3 turnos.
 * </p>
 * @see usaveis.cartas.CartaDano
 * @see usaveis.cartas.efeitos.Sangramento
 */
public class espadaLonga extends CartaDano {
  public espadaLonga() {
    super("Espada Longa", 3, 3, "Uma espada estranhamente longa");
    Sangramento sangramentoI = new Sangramento("Sangramento I", 2, 3, 1);
    adicionarEfeito(sangramentoI);
  }
}
