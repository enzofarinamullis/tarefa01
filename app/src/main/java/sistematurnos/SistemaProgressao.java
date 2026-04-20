package sistematurnos;

import org.jgrapht.graph.DefaultEdge;

public class SistemaProgressao {
  private Mapa mapa;
  private int estagio;
  
  public SistemaProgressao(Mapa mapa){
    this.mapa = mapa;
    estagio = 0;
  }
  
  public void selecionaEstagio(){
    int indice = 0;
    for(Batalha noAtual : mapa.grafo.vertexSet()){
      /* Baseado no estado que estamos, procuramos quais são os caminhos possíveis */
      if(estagio == -1){
        System.out.println(noAtual);
      }
      else if(estagio == indice){
        for(DefaultEdge aresta : mapa.grafo.outgoingEdgesOf(noAtual)){
          Batalha noDestino = mapa.grafo.getEdgeTarget(aresta);
          System.out.println(noDestino);
        }
        break;
      }
      indice++;
    }
  }
}
