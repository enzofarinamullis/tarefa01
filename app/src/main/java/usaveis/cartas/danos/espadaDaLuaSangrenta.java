package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;

/**
 * Representa a carta de dano "Espada da Lua Sangrenta" no jogo.
 * <p>
 * A Espada da Lua Sangrenta é uma carta de dano poderosa que causa 5 pontos de dano
 * com um custo de 5 pontos de mana.
 * </p>
 * <p>
 * Seu efeito adicional é o Sangramento V, que causa pontos de dano por
 * turno baseados no nível 5, durante 5 turnos.
 * </p>
 * @see usaveis.cartas.CartaDano
 * @see usaveis.cartas.efeitos.Sangramento
 */
public class espadaDaLuaSangrenta extends CartaDano{
  public espadaDaLuaSangrenta() {
    super("Espada da Lua Sangrenta", 5,5, "Espada com magia sangrenta" );
    Sangramento sangramentoV = new Sangramento("Sangramento V", 5, 10, 5);
    adicionarEfeito(sangramentoV);
  }
}
