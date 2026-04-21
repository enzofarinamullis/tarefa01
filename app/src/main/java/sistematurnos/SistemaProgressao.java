package sistematurnos;

import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaProgressao {
  private Mapa mapa;
  private int estagio;
  
  public SistemaProgressao(Mapa mapa){
    this.mapa = mapa;
    estagio = -1;
    atualizaMapa();
    mapa.imprimeMatriz();
  }
  
  private void atualizaMapa(){
    mapa.atualizaMapa(estagio);
    mapa.imprimeMatriz();
  }
  
  public void selecionaEstagio(){
    Scanner teclado = new Scanner(System.in);
    Batalha atual = mapa.getInicio();
    int qntEscolhas = 1;
    while (atual != null){
      System.out.println("Batalha selecionada: " + atual);
      boolean resultado = atual.iniciarBatalha();
      
      estagio += qntEscolhas; // a escolha variará de 0 a 1, precisamos
      // incrementar o estagio com base nela
      
      if(!resultado){
        System.out.println("Você morreu!");
        break;
      }
      
      /* limpamos o caminho simplesmente redeclarando ele */
      List<Batalha> caminhos = new ArrayList<>();
      
      for(DefaultEdge aresta : mapa.grafo.outgoingEdgesOf(atual)){
        Batalha destino = mapa.grafo.getEdgeTarget(aresta);
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
