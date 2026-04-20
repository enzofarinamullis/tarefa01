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
    estagio = 0;
  }
  
  public void selecionaEstagio(){
    Scanner teclado = new Scanner(System.in);
    Batalha atual = mapa.getInicio();
    
    while (atual != null){
      System.out.println("Batalha selecionada: " + atual);
      
      boolean resultado = atual.iniciarBatalha();
      if(!resultado){
        System.out.println("Você morreu!");
        break;
      }
      
      List<Batalha> caminhos = new ArrayList<>();
      
      for(DefaultEdge aresta : mapa.grafo.outgoingEdgesOf(atual)){
        Batalha destino = mapa.grafo.getEdgeTarget(aresta);
        caminhos.add(destino);
      }
      System.out.println("Escolha a próxima batalha:");
      for(int i = 0; i < caminhos.size(); i++) {
        System.out.println(i + " - " + caminhos.get(i));
      }
      
      /* fazemos a leitura e a validacao baseado no indice da lista de escolhas */
      int escolha = -1;
      while(escolha < 0 || escolha >= caminhos.size()) {
        escolha = teclado.nextInt();
      }
      
      atual = caminhos.get(escolha);
    }
  }
}
