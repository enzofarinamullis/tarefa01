package usaveis.cartas.danos;

import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Envenenamento;
/**
 * Representa a carta de dano "Espada Envenenada" no jogo.
 * <p>
 * A Espada Envenenada é uma arma insidiosa que combina dano físico imediato
 * com um potente veneno que continua causando dano ao alvo ao longo do tempo.
 * Banhada em veneno de rato, esta espada é perfeita para estratégias que
 * priorizam dano sustentado e controle de combate.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Dano Base:</b> 5 pontos</li>
 *   <li><b>Custo de Mana:</b> 5 pontos</li>
 *   <li><b>Efeito Especial:</b> Envenenamento V (5 de dano por turno durante 5 turnos)</li>
 * </ul>
 * 
 * <p><b>Dano total potencial:</b> 5 (imediato) + 25 (ao longo de 5 turnos) = 30 pontos</p>
 * 
 * <p><b>Nota de design:</b> Diferente do sangramento que causa dano físico contínuo,
 * o envenenamento representa dano mágico ou químico sustentado, sendo igualmente
 * mortal mas com duração mais concentrada.</p>
 * 
 * @see usaveis.cartas.CartaDano
 * @see usaveis.cartas.efeitos.Envenenamento
 */

public class espadaEnvenenada extends CartaDano{
  
  public espadaEnvenenada() {
    super("Espada Envenenada", 5,5, "Espada banhada em veneno de rato" );
    Envenenamento envenenamentoV = new Envenenamento("Envenenamento V", 5, 5, 5);
    adicionarEfeito(envenenamentoV);
  }
}

