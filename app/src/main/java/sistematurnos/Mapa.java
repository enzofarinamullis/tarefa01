package sistematurnos;
import com.sun.security.jgss.GSSUtil;
import sistematurnos.NoMapa.NoMapa;
import sistematurnos.batalhas.*;
import dados.Dados;
import constantes.ConstMapa;
import constantes.Cores;

/* para ref: https://jgrapht.org/guide/UserOverview */
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import sistematurnos.fogueira.FogueiraBasica;
import sistematurnos.lojas.LojaUm;

/**
 * Classe responsável por representar o mapa do jogo.
 *
 * <p>
 *   O mapa é modelado como um grafo direcionado de {@link Batalha},
 *   onde cada vértice representa uma batalha e as arestas representam os
 *   caminhos entre elas.
 *
 *
 * <p>
 *   Além do grafo, o mapa também possui uma representação visual em forma de uma
 *   matriz bidimensional {@code matrizMapa}, utilizada para exibição no terminal.
 * </p>
 *
 * <p>
 *   Para não complicarmos a impressão da matrizMapa, criamos uma convenção
 *   de somente ter no máximo 2 arestas saindo de um mesmo nó, e que os nós irmãos
 *   devem convergir sempre para o mesmo nó filho. Assim, conseguimos criar um padrão de impressão
 *   mais simples.
 *  </p>
 */
public class Mapa {
  DefaultDirectedGraph<Evento, DefaultEdge> grafo;
  int[][] matrizMapa;
  
  
  /**
   * Construtor do mapa, que inicializa o grafo e a matriz de representação visual.
   * @param dados objeto que armazena todas as informações relevantes do jogo, como o herói e os inimigos.
   */
  public Mapa(Dados dados){
    grafo = new DefaultDirectedGraph<>(DefaultEdge.class);
    inicializaMatriz();
    
    /* Criamos as batalhas que iremos utilizar */
    Evento b0 = new LojaUm(dados);
    Evento b00 = new FogueiraBasica()
    Evento b1 = new BatalhaNSlimes(dados, 1);
    Evento b2 = new BatalhaNSlimes(dados, 2);
    Evento b3 = new BatalhaNSlimes(dados, 3);
    Evento b4 = new BatalhaLesmasESlimes(dados, 1, 1);
    Evento b5 = new BatalhaLesmasESlimes(dados, 2, 1);
    Evento b6 = new BatalhaLesmasESlimes(dados, 1, 3);

    grafo.addVertex(b0);
    grafo.addVertex(b1);
    grafo.addVertex(b2);
    grafo.addVertex(b3);
    grafo.addVertex(b4);
    grafo.addVertex(b5);
    grafo.addVertex(b6);

    grafo.addEdge(b0, b1);
    grafo.addEdge(b1, b2);
    grafo.addEdge(b2, b3);
    grafo.addEdge(b2, b4);
    grafo.addEdge(b3, b5);
    grafo.addEdge(b4,b5);
    grafo.addEdge(b5, b6);
    
    geraMapa();
    imprimeMatriz();
  }
  
  /**
   * Inicializa a matriz de representação visual do mapa, preenchendo-a com zeros.
   * A matriz é dimensionada de acordo com as constantes definidas em {@link ConstMapa}
   */
  private void inicializaMatriz(){
    matrizMapa = new int[ConstMapa.TAM_MAPA_Y][ConstMapa.TAM_MAPA_X];
    for(int i = 0; i < ConstMapa.TAM_MAPA_Y; i++){
      for(int j = 0; j < ConstMapa.TAM_MAPA_X; j++){
        matrizMapa[i][j] = 0;
      }
    }
  }
  
  /**
   * Gera a representação visual do mapa com base na estrutura de grafo.
   *
   * <p>
   *   Posiciona nós e caminhos na matriz de acordo com a quantidade de arestas
   *   de saída de cada nó. Utilizando a convenção de somente ter no máximo 2 arestas saindo
   *   de um mesmo nó, e que os nós irmãos
   *   devem convergir sempre para o mesmo nó filho.
   * </p>
   */
  private void geraMapa(){
    int linha = 1;
    int meioX = ConstMapa.MEIO_X;
    int indiceIrmaos = 0;
    boolean pausa = false;
    int indicePausa = 0;
    int qntArestasAnterior = 0;
    boolean anteriorFoiDois = false;
    for(Evento noAtual : grafo.vertexSet()) {
      if(pausa){
        indicePausa++;
        if(indicePausa == 2){
          pausa = false;
          indicePausa = 0;
          linha += 2;
          qntArestasAnterior = grafo.outgoingEdgesOf(noAtual).size();
          anteriorFoiDois = true;
        }
      }
     
      if (qntArestasAnterior == 1 || qntArestasAnterior == 0 && !pausa){
        if(!anteriorFoiDois) {
          matrizMapa[linha - 1][meioX] = ConstMapa.CAMINHO_RETO;
        }
        matrizMapa[linha][meioX] = ConstMapa.NO;
      }
      if(qntArestasAnterior == 2  && indiceIrmaos == 0 && !pausa){
        matrizMapa[linha - 1][meioX - 1] = ConstMapa.CAMINHO_ESQ;
        matrizMapa[linha][meioX - 2] = ConstMapa.NO;
        matrizMapa[linha + 1][meioX - 1] = ConstMapa.CAMINHO_DIR;
        indiceIrmaos++;
      }
      if (qntArestasAnterior == 2 && indiceIrmaos == 1 && !pausa){
        matrizMapa[linha - 1][meioX + 1] = ConstMapa.CAMINHO_DIR;
        matrizMapa[linha][meioX + 2] = ConstMapa.NO;
        matrizMapa[linha + 1][meioX + 1] = ConstMapa.CAMINHO_ESQ;
        indiceIrmaos = 0;
        pausa = true;
      }
      if(!pausa) {
        linha = linha + 2;
        qntArestasAnterior = grafo.outgoingEdgesOf(noAtual).size();
      }
      if(anteriorFoiDois){
        anteriorFoiDois = false;
      }
      
    }
  }
  
  /**
   * Imprime a matriz de representação visual do mapa no terminal.
   *
   * <p>
   *   Utiliza cores para diferenciar:
   *   <ul>
   *     <li> Nós disponíveis futuros </li>
   *     <li> Nós já terminados </li>
   *     <li> Caminhos entre os nós </li>
   *   </ul>
   *   </p>
   */
  protected void imprimeMatriz(){
    
    System.out.println("MAPA:");
    System.out.println("---------");
    for(int i = 0; i < ConstMapa.TAM_MAPA_Y; i++){
      for(int j = 0; j < ConstMapa.TAM_MAPA_X; j++){
        if(matrizMapa[i][j] == ConstMapa.NO) {
          System.out.print(Cores.ANSI_YELLOW + "⚫ " + Cores.ANSI_RESET);
        }
        else if(matrizMapa[i][j] == ConstMapa.NO_TERMINADO){
          System.out.print(Cores.ANSI_RED + "⚫ " + Cores.ANSI_RESET);
        }
        else if (matrizMapa[i][j] == ConstMapa.NO_ATUAL){
          System.out.print(Cores.ANSI_BLUE + "⚫ " + Cores.ANSI_RESET);
        }
        else if(matrizMapa[i][j] == ConstMapa.CAMINHO_RETO){
          System.out.print(Cores.ANSI_YELLOW + "▎▎");
        }
        else if (matrizMapa[i][j] == ConstMapa.CAMINHO_ESQ) {
          System.out.print(Cores.ANSI_YELLOW + "▞ " + Cores.ANSI_RESET);
        }
        else if (matrizMapa[i][j] == ConstMapa.CAMINHO_DIR){
          System.out.print(Cores.ANSI_YELLOW + "▚ " + Cores.ANSI_RESET);
        }
        else {
          System.out.print(Cores.COR_CIMENTO_3 + "█ " + Cores.ANSI_RESET);
        }
      }
      System.out.println();
    }
    System.out.println("---------");
  }
  
  /**
   * Retorna o nó inicial do mapa.
   * @return a primeira batalha do mapa ou {@code null} se o mapa estiver vazio.
   */
  protected Evento getInicio() {
    for (Evento noAtual : grafo.vertexSet()) {
      return noAtual;
    }
    return null;
  }
  
  /**
   * Atualiza o estado visual do mapa com base no progresso do jogador.
   *
   * <p>
   *   Marca os nós como terminados (vermelho) à medida que o jogador avança pelos estágios do mapa.
   * @param estagio o estágio atual do jogador.
   */
  protected void atualizaMapa(int estagio){
    int indice = -1;
    /* percorremos a matriz até achar os nós */
    for(int i = 0; i < ConstMapa.TAM_MAPA_Y; i++){
      for(int j = 0; j < ConstMapa.TAM_MAPA_X; j++){
        if(matrizMapa[i][j] == ConstMapa.NO){
          matrizMapa[i][j] = ConstMapa.NO_TERMINADO;
          indice++;
        }
        else if (matrizMapa[i][j] == ConstMapa.NO_TERMINADO) {
          indice++;
        }
        if(indice == estagio){
          return;
        }
      }
    }
  }
}
