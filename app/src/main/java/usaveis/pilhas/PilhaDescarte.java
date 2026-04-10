package usaveis.pilhas;

import usaveis.Mao;

/**
 * Representa a pilha de descarte do jogador.
 *
 * <p>
 *   Armazena as cartas que foram usadas ou descartadas durante o turno. Essas cartas
 *   podem ser posteriormente transferidas de volta para a pilha de compra quando
 *   esta se esgota.
 * </p>
 */
public class PilhaDescarte extends Pilha {
  public PilhaDescarte() {
    super();
  }
  
  /**
   * Move todas as cartas da mão do jogador para a pilha de descarte.
   *
   * <p>
   *   Este método é chamado no final do turno, descartando todas as cartas
   *   restantes da mão.
   * </p>
   * @param mao a mão do jogador contendo as cartas a serem descartadas.
   */
  public void removeMao(Mao mao){
    while(!mao.cartas.isEmpty()){
      pilha.add(mao.cartas.removeFirst());
    }
  }
}
