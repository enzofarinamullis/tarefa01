package sistematurnos;

import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por gerenciar o sistema de progressão do jogo.
 *
 * <p>
 * Controla o avanço do jogador através de um {@link Mapa} composto por batalhas
 * conectadas em forma de grafo. O jogador inicia na batalha inicial e,
 * conforme vence, pode escolher entre diferentes caminhos até chegar ao fim ou morrer.
 * </p>
 * <p>
 * O progresso é armazenado por meio do atributo {@code estagio},
 * que é atualizado conforme a quantidade de caminhos disponíveis a cada decisão.
 * </p>
 */
public class SistemaProgressao {
  /** Mapa contendo as batalhas e suas conexões */
  private Mapa mapa;
  /** Estágio atual do jogador, representando o progresso no mapa */
  private int estagio;
  
  /**
   * Construtor do sistema de progressão.
   * @param mapa o mapa contendo as batalhas e suas conexões
   */
  public SistemaProgressao(Mapa mapa){
    this.mapa = mapa;
    estagio = -1;
    atualizaMapa();
    mapa.imprimeMatriz();
  }
  
  /**
   * Atualiza o mapa com base no estágio atual do jogador.
   *
   * <p>
   *   Também imprime a matriz do mapa para mostrar as batalhas disponíveis,
   *   após cada atualização.
   * </p>
   */
  private void atualizaMapa(){
    mapa.atualizaMapa(estagio);
    mapa.imprimeMatriz();
  }
  
  
  /**
   * Inicia o processo de seleção de estágio e progressão.
   *
   * <p>
   *   O fluxo funciona da seguinte forma:
   *   <ul>
   *     <li> O jogador inicia na batalha inicial do mapa.</li>
   *     <li> Cada batalha é executada via {@code iniciarBatalha()}</li>
   *     <li> Se o jogador perder o processo é encerrado</li>
   *     <li> Se o jogador vencer, ele pode escolher entre os caminhos disponíveis
   *     para a próxima batalha</li>
   *     <li> O processo se repete até não haver mais caminhos </li>
   *   </ul>
   * </p>
   * <p>
   *   A escolha do próximo caminho é feita via entrada do usuário no console.
   * </p>
   *
   * @see Mapa
   * @see Batalha#iniciar()
   *
   */
  public void selecionaEstagio(){
    Scanner teclado = new Scanner(System.in);
    Evento atual = mapa.getInicio();
    int qntEscolhas = 1;
    while (atual != null){
      System.out.println("Batalha selecionada: " + atual);
      boolean resultado = atual.iniciar();
      
      estagio += qntEscolhas; // a escolha variará de 0 a 1, precisamos
      // incrementar o estagio com base nela
      
      if(!resultado){
        System.out.println("Você morreu!");
        break;
      }
      
      /* limpamos o caminho simplesmente redeclarando ele */
      List<Evento> caminhos = new ArrayList<>();
      
      for(DefaultEdge aresta : mapa.grafo.outgoingEdgesOf(atual)){
        Evento destino = mapa.grafo.getEdgeTarget(aresta);
        caminhos.add(destino);
      }
      
      /* precisamos verificar se o jogador chegou ao último caminho */
      /* o último caminho não terá caminho para nenhum outro nó */
      if(caminhos.isEmpty()){
        System.out.println("Você chegou ao fim!");
        return;
      }
      
      atualizaMapa();
      System.out.println("Escolha a próxima batalha:");
      for(int i = 0; i < caminhos.size(); i++) {
        System.out.println(i + " - " + caminhos.get(i));
      }
      
      /* fazemos a leitura e a validacao baseado no indice da lista de escolhas */
      int escolha = -1;
      qntEscolhas = caminhos.size();
      while(escolha < 0 || escolha >= caminhos.size()) {
        escolha = teclado.nextInt();
      }
      
      atual = caminhos.get(escolha);
    }
  }
}
