package usaveis.cartas.danos;
import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;
/**
 * Representa a carta de dano "Espada Longa" no jogo.
 * <p>
 * A Espada Longa é uma arma clássica e versátil, presente em quase todos
 * os arsenais de combatentes. Seu design tradicional combina alcance
 * superior com bom poder de corte, causando dano sólido e aplicando um
 * efeito básico de sangramento no alvo.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Dano Base:</b> 3 pontos</li>
 *   <li><b>Custo de Mana:</b> 3 pontos</li>
 *   <li><b>Efeito Especial:</b> Sangramento I (2 de dano por turno durante 3 turnos)</li>
 * </ul>
 * 
 * <p><b>Dano total potencial:</b> 3 (imediato) + 6 (ao longo de 3 turnos) = 9 pontos</p>
 * 
 * <p><b>Nota sobre balanceamento:</b> Esta é uma carta de dano de entrada,
 * perfeita para personagens de níveis iniciais ou como uma opção econômica
 * para combates prolongados. Oferece bom custo-benefício para seu valor
 * de mana.</p>
 * 
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
