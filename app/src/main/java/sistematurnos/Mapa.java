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

public class Mapa {
  DefaultDirectedGraph<Batalha, DefaultEdge> mapa;
  int[][] matrizMapa;
  
  public Mapa(Dados dados){
    mapa = new DefaultDirectedGraph<>(DefaultEdge.class);
    inicializaMatriz();
    
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
    
    geraMapa();
    imprimeMatriz();
  }
  
  private void inicializaMatriz(){
    matrizMapa = new int[ConstMapa.TAM_MAPA_Y][ConstMapa.TAM_MAPA_X];
    for(int i = 0; i < ConstMapa.TAM_MAPA_Y; i++){
      for(int j = 0; j < ConstMapa.TAM_MAPA_X; j++){
        matrizMapa[i][j] = 0;
      }
    }
  }
  
  private void geraMapa(){
    int linha = 1;
    int meioX = ConstMapa.MEIO_X;
    int indiceIrmaos = 0;
    boolean pausa = false;
    int indicePausa = 0;
    int qntArestasAnterior = 0;
    boolean anteriorFoiDois = false;
    for(Batalha noAtual : mapa.vertexSet()) {
      if(pausa){
        indicePausa++;
        if(indicePausa == 2){
          pausa = false;
          indicePausa = 0;
          linha += 2;
          qntArestasAnterior = mapa.outgoingEdgesOf(noAtual).size();
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
        qntArestasAnterior = mapa.outgoingEdgesOf(noAtual).size();
      }
      if(anteriorFoiDois){
        anteriorFoiDois = false;
      }
      
    }
  }
  
  private void imprimeMatriz(){
    
    System.out.println("MAPA:");
    System.out.println("---------");
    for(int i = 0; i < ConstMapa.TAM_MAPA_Y; i++){
      for(int j = 0; j < ConstMapa.TAM_MAPA_X; j++){
        if(matrizMapa[i][j] == ConstMapa.NO) {
          System.out.print(Cores.ANSI_YELLOW + "⚫ " + Cores.ANSI_RESET);
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
}
