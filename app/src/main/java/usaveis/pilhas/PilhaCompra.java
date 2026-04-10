package usaveis.pilhas;

import constantes.Cores;
import java.util.NoSuchElementException;
import java.util.Random;
import usaveis.*;
import usaveis.cartas.Carta;
import usaveis.cartas.danos.*;
import usaveis.escudos.*;

/**
 * Representa a pilha de compra (baralho) do jogador.
 *
 * <p>
 *   Contém as cartas disponíveis para serem compradas dutante o jogo.
 *   Quando a pilha se esgota, as cartas da pilha de descarte são transferidas
 *   para a pilha de compra e embaralhadas.
 * </p>
 *
 * <p>
 *   A pilha é inicializada com um conjunto padrão de cartas.
 * </p>
 */
public class PilhaCompra extends Pilha {
  /* ja temos a pilha exatamente pronta */
  public PilhaCompra() {
    super();
    /* criamos o deque */
    Carta carta = new espadaCurta();
    pilha.add(carta);
    carta = new espadaMedia();
    pilha.add(carta);
    carta = new espadaLonga();
    pilha.add(carta);
    carta = new espadaEL();
    pilha.add(carta);
    carta = new escudoPequeno();
    pilha.add(carta);
    carta = new escudoMedio();
    pilha.add(carta);
    carta = new escudoGrande();
    pilha.add(carta);
    carta = new escudoEL();
    pilha.add(carta);
    carta = new espadaDaLuaSangrenta();
    pilha.add(carta);
    carta = new espadaEnvenenada();
    pilha.add(carta);
    carta = new escudoDaInstropeccao();
    pilha.add(carta);
    carta = new espadaCorrupta();
    pilha.add(carta);
  }
  
  
  /**
   * Embralha a pilha de compra.
   *
   * <p>
   *   Remove cartas aleatoriamente da pilha atual e as insere em uma nova
   *   pilha temporária, garantindo uma ordem aleatória das cartas.
   *   Após esvaziar a pilha original, a referência da pilha é
   *   atualizada para a pilha embaralhada.
   * </p>
   */
  public void embraralhaPilha() {
    /* Remove cartas aleatoriamente da pilha original e as adiciona a uma nova pilha
     * para simular embaralhamento.
     */
    Pilha temp = new Pilha();
    Carta atual;
    Random aleatorio = new Random();
    while(!pilha.isEmpty()){
      /* pegamos uma carta aleatoria da pilha */
      atual = pilha.remove(aleatorio.nextInt(pilha.size()));
      /* colocamos na pilha temporaria */
      temp.pilha.add(atual);
      /* fazemos isto ate zerar a pilha */
    }
    /* copiamos a referencia para a pilha embaralhada */
    pilha = temp.pilha;
  }
  
  /**
   * Transfere as cartas da pilha de descarte para a pilha de compra.
   * <blockquote>
   *   Este método é chamado quando a pilha de compra se esgota, garantindo que
   *   o jogo possa continuar sem interrupções. As cartas da pilha de descarte
   *   são movidas para a pilha de compra, permitindo que o jogador continue
   *   comprando cartas mesmo após esgotar o baralho inicial.
   * </blockquote>
   *
   * @param pilhaDescarte a pilha de descarte da qual as cartas serão transferidas para a pilha de compra
   * @see #compraCarta(Mao, PilhaDescarte, int)
   * @see PilhaDescarte
   */
  private void transfereDescarte(PilhaDescarte pilhaDescarte){
    Carta carta;
    while(!pilhaDescarte.pilha.isEmpty()){
      carta = pilhaDescarte.pilha.removeFirst();
      pilha.add(carta);
    }
    System.out.println();
    System.out.println("A pilha de compras acabou");
    System.out.println("Transferindo:");
    System.out.println(Cores.COR_CIMENTO_3 + "Pilha de Descarte" +
      Cores.ANSI_RESET + "->" + Cores.ANSI_BLUE + "Pilha Compra" + Cores.ANSI_RESET);
  }
  
  
  /**
   * Compra uma quantidade específica de cartas da pilha de compra para a mão do jogador.
   *
   * <p>
   *   As cartas compradas são adicionadas à mão do jogador. Caso a pilha
   *   de compra esteja vazia durante o processo de compra, as cartas da pilha de
   *   descarte são transferidas para a pilha de compra, embaralhadas e o
   *   processo de compra continua.
   * </p>
   * @param mao a mão do jogador para a qual as cartas compradas serão adicionadas
   * @param pilhaDescarte a pilha de descarte usada para reciclar cartas quando a pilha de compra se esgota
   * @param qnt a quantidade de cartas a ser comprada da pilha de compra
   */
  public void compraCarta(Mao mao, PilhaDescarte pilhaDescarte, int qnt) {
    int compradas = 0;
    try {
      for (int i = 0; i < qnt; i++) {
        Carta carta = pilha.removeFirst();
        mao.cartas.add(carta);
        compradas++;
      }
      /* caso a pilha esteja vazia, recicla o descarte e continua a compra */
    } catch (NoSuchElementException e) {
      /* caso estiver vazia */
      /* colocamos a pilha de descarte embaralhada */
      transfereDescarte(pilhaDescarte);
      embraralhaPilha();
      compraCarta(mao, pilhaDescarte, qnt - compradas);
    }
  }
}
