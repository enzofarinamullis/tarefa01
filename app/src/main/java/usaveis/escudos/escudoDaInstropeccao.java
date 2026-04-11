package usaveis.escudos;

import usaveis.cartas.CartaEscudo;
import usaveis.cartas.efeitos.Cura;

/**
 * Representa uma carta de defesa que concede escudo e aplica cura.
 *
 * <p>
 *   Ao ser utilizada, esta carta concede 5 pontos de escudo
 *   e aplica um efeito de {@link Cura}, restaurando a vida do herói.
 * </p>
 * <p>
 *   É uma carta híbrida que combina defesa e recuperação de vida.
 *   <p>
 *     Exemplo de uso:<br>
 *     escudoDaInstrospecao carta = new escudoDaInstrospecao();<br>
 *     carta.usar(null, heroi);<br>
 *
 *   </p>
 * @see usaveis.cartas.CartaEscudo
 */
public class escudoDaInstropeccao extends CartaEscudo {
  public escudoDaInstropeccao() {
    super("Escudo da Instrospecção", 5,5, "Escudo de uma Cura Profunda" );
    Cura curaII = new Cura("Cura II", 1, 10, 5);
    adicionarEfeito(curaII);
  }
}
