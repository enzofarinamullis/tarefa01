package usaveis.pilhas;
import java.util.ArrayList;
import java.util.List;
import usaveis.cartas.Carta;


/**
 * Reprensenta uma estrutura genérica de pilha de cartas.
 *
 * <p>
 *   Esta classe serve como base para diferentes pilhas no jogo,
 *   como a pilha de compra e a pilha de descarte.
 * </p>
 */
public class Pilha {
  public List<Carta> pilha;
  
  public Pilha() {
    pilha = new ArrayList<>();
  }
}
