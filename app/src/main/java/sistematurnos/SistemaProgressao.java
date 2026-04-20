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
  
  public void selecionaEstagio(){
    int indice = 0;
    int escolha = 0;
    System.out.println("Selecione uma fase: ");
    boolean resultado = false;
    Batalha batalhaEscolhida;
    Batalha noAnterior = null;
    for(Batalha noAtual : mapa.grafo.vertexSet()){
      /* Baseado no estado que estamos, procuramos quais são os caminhos possíveis */
      if(estagio == 0) {
        System.out.println(noAtual);
        
        /* Verificamos se a batalha escolhida é valida */
        batalhaEscolhida = null;
        Scanner teclado = new Scanner(System.in);
        while (batalhaEscolhida == null){
          escolha = teclado.nextInt();
          batalhaEscolhida = mapa.getBatalha(escolha);
        }
        System.out.println("Batalha escolhida com sucesso!");
        
        /* iniciamos a batalha */
        resultado = batalhaEscolhida.iniciarBatalha();
      }
      else if(estagio == indice){
        for(DefaultEdge aresta : mapa.grafo.outgoingEdgesOf(noAnterior)){
          Batalha noDestino = mapa.grafo.getEdgeTarget(aresta);
          System.out.println(noDestino);
        }
        
        /* Verificamos se a batalha escolhida é valida */
        batalhaEscolhida = null;
        Scanner teclado = new Scanner(System.in);
        while (batalhaEscolhida == null){
          escolha = teclado.nextInt();
          batalhaEscolhida = mapa.getBatalha(escolha);
        }
        System.out.println("Batalha escolhida com sucesso!");
        
        /* Iniciamos a batalha */
        resultado = batalhaEscolhida.iniciarBatalha();
      }
      if(resultado) {
        indice++;
        estagio = escolha;
        noAnterior = noAtual;
      }
      else{
        break;
      }
    }
  }
}
