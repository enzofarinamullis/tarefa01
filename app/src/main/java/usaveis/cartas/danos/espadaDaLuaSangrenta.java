package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;
/**
 * Representa a carta de dano "Espada da Lua Sangrenta" no jogo.
 * <p>
 * A Espada da Lua Sangrenta é uma poderosa carta de dano que combina 
 * dano físico com magia sangrenta. Esta arma lendária não apenas causa 
 * dano imediato significativo, mas também aplica um efeito de sangramento 
 * poderoso no alvo, causando dano contínuo ao longo do tempo.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Dano Base:</b> 5 pontos</li>
 *   <li><b>Custo de Mana:</b> 5 pontos</li>
 *   <li><b>Efeito Especial:</b> Sangramento V (5 de dano por turno durante 10 turnos)</li>
 * </ul>
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
