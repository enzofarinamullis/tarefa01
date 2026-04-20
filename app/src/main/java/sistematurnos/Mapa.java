package sistematurnos;
import org.jgrapht.generate.CompleteGraphGenerator;
import sistematurnos.NoMapa.NoMapa;
import sistematurnos.batalhas.*;
import dados.Dados;

/* para ref: https://jgrapht.org/guide/UserOverview */
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Mapa {
  DefaultDirectedGraph<Batalha, DefaultEdge> mapa;
  
  public Mapa(Dados dados){
    mapa = new DefaultDirectedGraph<>(DefaultEdge.class);
    /* Criamos as batalhas que iremos utilizar */
    NoMapa no;
    Batalha b1 = new BatalhaNSlimes(dados, 1);
    Batalha b2 = new BatalhaNSlimes(dados, 2);
    Batalha b3 = new BatalhaNSlimes(dados, 3);
    Batalha b4 = new BatalhaLesmasESlimes(dados, 1, 1);
    Batalha b5 = new BatalhaLesmasESlimes(dados, 2, 1);
    Batalha b6 = new BatalhaLesmasESlimes(dados, 1, 3);
    
    mapa.addVertex(b1);
    mapa.addVertex(b2);
    mapa.addVertex(b3);
    mapa.addVertex(b4);
    mapa.addVertex(b5);
    mapa.addVertex(b6);
    
    mapa.addEdge(b1, b2);
    mapa.addEdge(b2, b3);
    mapa.addEdge(b2, b4);
    mapa.addEdge(b3, b5);
    mapa.addEdge(b4,b5);
    mapa.addEdge(b5, b6);
    
    imprimirMapa();
  }
  
  public void imprimirMapa(){
    Batalha noDestino;
    for(Batalha noInicio : mapa.vertexSet()){
      System.out.println("Vértice: " + noInicio);
      
      for(DefaultEdge aresta : mapa.outgoingEdgesOf(noInicio)){
        noDestino = mapa.getEdgeTarget(aresta);
        System.out.println(noInicio + " -> " + noDestino);
      }
    }
  }
}
