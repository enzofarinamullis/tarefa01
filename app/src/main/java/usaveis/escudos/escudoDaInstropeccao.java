package usaveis.escudos;

import usaveis.cartas.CartaEscudo;
import usaveis.cartas.efeitos.Cura;
/**
 * Representa o Escudo da Introspecção, uma carta de escudo mágico que proporciona
 * efeitos de cura ao jogador.
 * <p>
 * Este escudo combina proteção física com capacidade de regeneração espiritual,
 * aplicando automaticamente um efeito de Cura II ao ser utilizado. Diz a lenda
 * que este escudo foi forjado por um monge que buscava a iluminação interior,
 * descobrindo que a verdadeira proteção vem do autoconhecimento.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Defesa Base:</b> 5 pontos</li>
 *   <li><b>Custo de Mana:</b> 5 pontos</li>
 *   <li><b>Efeito Especial:</b> Cura II (10 pontos de cura por turno durante 1 turno)</li>
 * </ul>
 * 
 * <p><b>Efeito de Cura aplicado:</b></p>
 * <ul>
 *   <li>💚 <b>Cura por ativação:</b> 10 pontos</li>
 *   <li>💚 <b>Duração:</b> 1 turno (cura instantânea)</li>
 *   <li>💚 <b>Intensidade:</b> 10 (cura poderosa)</li>
 * </ul>
 * 
 * <p><b>Nota de design:</b> O nome "Introspecção" reflete a natureza meditativa
 * do escudo - ele não apenas bloqueia danos físicos, mas também cura as feridas
 * da alma através da reflexão interior.</p>
 * @see CartaEscudo
 * @see Cura
 */
public class escudoDaInstropeccao extends CartaEscudo {
  public escudoDaInstropeccao() {
    super("Escudo da Instrospecção", 5,5, "Escudo de uma Cura Profunda" );
    Cura curaII = new Cura("Cura II", 1, 10, 5);
    adicionarEfeito(curaII);
  }
}
