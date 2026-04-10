package usaveis.cartas.danos;
import usaveis.cartas.CartaDano;
import usaveis.cartas.efeitos.Sangramento;
/**
 * Representa a carta de dano "Espada Estranhamente Longa" no jogo.
 * <p>
 * A Espada Estranhamente Longa é uma arma incomum que se destaca pelo seu
 * comprimento exagerado e aparência cômica. Apesar de seu visual peculiar,
 * esta espada é surpreendentemente eficaz em combate, causando dano
 * significativo e aplicando um efeito de sangramento forte no alvo.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Dano Base:</b> 4 pontos</li>
 *   <li><b>Custo de Mana:</b> 4 pontos</li>
 *   <li><b>Efeito Especial:</b> Sangramento III (3 de dano por turno durante 5 turnos)</li>
 * </ul>
 * 
 * <p><b>Dano total potencial:</b> 4 (imediato) + 15 (ao longo de 5 turnos) = 19 pontos</p>
 * 
 * <p><b>Nota de design:</b> O nome "Espada Estranhamente Longa" é uma referência
 * a armas exageradamente longas comuns em jogos e animes, trazendo um elemento
 * de humor ao jogo enquanto mantém utilidade prática em combate.</p>
 * 
 * @see usaveis.cartas.CartaDano
 * @see usaveis.cartas.efeitos.Sangramento
 */

public class espadaEL extends CartaDano {
  public espadaEL() {
    super("Espada Estranhamente Longa", 4, 4, 
    "Uma espada comicamente longa");
    Sangramento sangramentoIII = new Sangramento("Sangramento III", 3, 5, 3);
    adicionarEfeito(sangramentoIII);
  }
}
