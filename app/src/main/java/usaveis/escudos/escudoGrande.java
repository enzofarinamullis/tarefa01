package usaveis.escudos;

import usaveis.cartas.CartaEscudo;
/**
 * Representa a carta de escudo "Escudo Grande" no jogo.
 * <p>
 * O Escudo Grande é um item de defesa robusto e confiável, oferecendo
 * proteção sólida sem frescuras ou efeitos especiais. É a escolha ideal
 * para jogadores que buscam simplicidade e eficiência em combate.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Defesa Base:</b> 3 pontos</li>
 *   <li><b>Custo de Mana:</b> 3 pontos</li>
 *   <li><b>Efeitos Especiais:</b> Nenhum</li>
 * </ul>
 * 
 * <p><b>Quando usar:</b></p>
 * <ul>
 *   <li>🛡️ Quando você precisa de proteção básica e confiável</li>
 *   <li>🛡️ Quando quer economizar mana para outras cartas</li>
 *   <li>🛡️ Quando não precisa de efeitos complexos</li>
 * </ul>
 * @see usaveis.cartas.CartaEscudo
 */

public class escudoGrande extends CartaEscudo {
  public escudoGrande() {
    super("Escudo Grande", 3, 3, "Um escudo grande");
  }
}