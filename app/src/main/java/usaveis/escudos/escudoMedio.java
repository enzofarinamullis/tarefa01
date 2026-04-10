package usaveis.escudos;

import usaveis.cartas.CartaEscudo;
/**
 * Representa a carta de escudo "Escudo Médio" no jogo.
 * <p>
 * O Escudo Médio é um item de defesa versátil e equilibrado, oferecendo
 * proteção moderada sem exigir muitos recursos. É a escolha ideal para
 * jogadores que buscam um bom custo-benefício em combate.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Defesa Base:</b> 2 pontos</li>
 *   <li><b>Custo de Mana:</b> 2 pontos</li>
 *   <li><b>Efeitos Especiais:</b> Nenhum</li>
 * </ul>
 * 
 * <p><b>Quando usar:</b></p>
 * <ul>
 *   <li>🛡️ Quando você precisa de proteção rápida nos primeiros turnos</li>
 *   <li>🛡️ Quando quer manter mana para outras estratégias</li>
 *   <li>🛡️ Quando busca um escudo que não atrapalhe seu early game</li>
 * </ul>
 * 
 * @see usaveis.cartas.CartaEscudo
 */
public class escudoMedio extends CartaEscudo {
  public escudoMedio() {
    super("Escudo Médio", 2, 2, "Um escudo na na média");
  }
}