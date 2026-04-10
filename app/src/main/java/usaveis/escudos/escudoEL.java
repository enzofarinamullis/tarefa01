package usaveis.escudos;
import usaveis.cartas.CartaEscudo;
import usaveis.cartas.efeitos.Cura;

/**
 * Representa a carta de escudo "Escudo Estranhamente Largo" no jogo.
 * <p>
 * O Escudo Estranhamente Largo é um item de defesa peculiar que se destaca
 * por sua largura exagerada e aparência cômica. Apesar de seu visual
 * incomum, este escudo oferece proteção sólida e ainda concede um efeito
 * de cura ao usuário, restaurando suas feridas gradualmente.
 * </p>
 * 
 * <p><b>Características da carta:</b></p>
 * <ul>
 *   <li><b>Defesa Base:</b> 4 pontos</li>
 *   <li><b>Custo de Mana:</b> 4 pontos</li>
 *   <li><b>Efeito Especial:</b> Cura III (cura 5 pontos por turno durante 3 turnos)</li>
 * </ul>
 * 
 * <p><b>Nota de design:</b> O nome "Escudo Estranhamente Largo" é uma contraparte
 * defensiva da "Espada Estranhamente Longa", mantendo o elemento de humor
 * enquanto oferece utilidade prática em combate através da regeneração de vida.</p>
 * @see usaveis.cartas.CartaEscudo
 * @see usaveis.cartas.efeitos.Cura
 */

public class escudoEL extends CartaEscudo {
  public escudoEL() {
    super("Escudo Estranhamente Largo", 4, 4, "Um escudo comicamente largo");
    Cura curaIII = new Cura("Cura III", 3, 5, 1);
  }
}