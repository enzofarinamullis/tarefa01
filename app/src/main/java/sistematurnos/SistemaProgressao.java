package sistematurnos;

import org.jgrapht.graph.DefaultEdge;

import java.util.Scanner;

public class SistemaProgressao {
  private Mapa mapa;
  private int estagio;
  
  public SistemaProgressao(Mapa mapa){
    this.mapa = mapa;
    estagio = 0;
  }
  
  private Batalha selecionaBatalha(){
    Batalha batalhaEscolhida = null;
    int escolha = 0;
    Scanner teclado = new Scanner(System.in);
    while (batalhaEscolhida == null){
      escolha = teclado.nextInt();
      batalhaEscolhida = mapa.getBatalha(escolha);
    }
    System.out.println("Batalha escolhida com sucesso!");
    return batalhaEscolhida;
  }
  
  public void selecionaEstagio(){
    int indice = 0;
    System.out.println("Selecione uma fase: ");
    int escolha = 0;
    boolean resultado = false;
    Batalha batalhaEscolhida = null;
    for(Batalha noAtual : mapa.grafo.vertexSet()){
      /* Baseado no estado que estamos, procuramos quais são os caminhos possíveis */
      if(estagio == 0) {
        System.out.println(noAtual);
        batalhaEscolhida = selecionaBatalha();
        resultado = batalhaEscolhida.iniciarBatalha();
      }
      else if(estagio == indice){
        for(DefaultEdge aresta : mapa.grafo.outgoingEdgesOf(noAtual)){
          Batalha noDestino = mapa.grafo.getEdgeTarget(aresta);
          System.out.println(noDestino);
        }
        batalhaEscolhida = selecionaBatalha();
        resultado = batalhaEscolhida.iniciarBatalha();
      }
      if(resultado == true) {
        indice++;
      }
      else{
        break;
      }
    }
  }
}
