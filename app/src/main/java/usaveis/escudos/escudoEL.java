package usaveis.escudos;
import usaveis.cartas.CartaEscudo;
import usaveis.cartas.efeitos.Cura;


/**
 * Representa uma carta de defesa que concede escudo e aplica cura.
 *
 * <p>
 *   Ao ser utilizada, fornece escudo ao herói e aplica um efeito de {@link Cura},
 *   restaurando a vida do herói.
 * </p>
 * <p>
 *   Exemplo de uso:<br>
 *   escudoEl carta = new escudoEL();<br>
 *   carta.usar(null, heroi);<br>
 * </p>
 */
public class escudoEL extends CartaEscudo {
  public escudoEL() {
    super("Escudo Estranhamente Largo", 4, 4, "Um escudo comicamente largo");
    Cura curaIII = new Cura("Cura III", 3, 5, 1);
    adicionarEfeito(curaIII);
  }
}