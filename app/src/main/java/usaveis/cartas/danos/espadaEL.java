package usaveis.cartas.danos;
import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;

/**
 * Representa a carta de dano "Espada Estranhamente Longa" no jogo.
 * <p>
 *   A Espada Estranhamente Longa é uma carta de dano que causa 4 pontos de dano
 *   com um custo de 4 pontos de mana.
 * </p>
 * <p>
 *   Seu efeito adicional é o Sangramento III, que causa 3 pontos de dano por turno
 *  durante 5 turnos.
 *  </p>
 *  @see usaveis.cartas.CartaDano
 *  @see usaveis.cartas.efeitos.Sangramento
 */
public class espadaEL extends CartaDano {
  public espadaEL() {
    super("Espada Estranhamente Longa", 4, 4, 
    "Uma espada comicamente longa");
    Sangramento sangramentoIII = new Sangramento("Sangramento III", 3, 5, 3);
    adicionarEfeito(sangramentoIII);
  }
}
