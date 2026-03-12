package mapa;
import constantes.TamMapa;
import java.util.Random;
import java.util.Objects;

import javax.lang.model.element.QualifiedNameable;

public class Matriz {
  int lenX;
  int lenY;
  int playerX;
  int playerY;
  int numCidades;
  int[][] mapa;
  int proporcao;
  int[][] matrizRefQuadrantes;
  Camera camera;
  Random random;


  private class Cidade{
    String nome;
    int populacao;
    String origem;
    Cidade inimiga;
  }

  private class Quadrante{
    int x;
    int y;
    int tamX;
    int tamY;

    private Quadrante(int x, int y){
      this.x = calculaCoord(x);
      this.y = calculaCoord(y);
      this.tamX = TamMapa.displayX;
      this.tamY = TamMapa.displayY;
    }
  }

  private class Camera{
    Quadrante quadrante;

    private Camera(){
      this.quadrante = new Quadrante(0, 0);
    }

    private int calculaCoordX(){
      return (quadrante.x + 1) * quadrante.tamX - quadrante.tamX;
    }
    private int calculaCoordY(){
      return (quadrante.y + 1) * quadrante.tamY - quadrante.tamX;
    }
  }

  public Matriz(){
    this.lenX = TamMapa.x;
    this.lenY = TamMapa.y;
    mapa = new int[lenY][lenX];
    camera = new Camera();
    random = new Random();
    proporcao = lenX / TamMapa.displayX;
  }


  private int calculaCoord(int x){
    return (x + 1) * TamMapa.displayX - TamMapa.displayX;
  }


  public void gerarMatriz(){
    int xQuadranteAntigo = 0, xQuadranteNovo;
    int yQuadranteAntigo = 0, yQuadranteNovo; 
    Quadrante quadranteNovo = new Quadrante(0, 0);
    /* inserir na matriz de quadrantes */
    for(int i = 0; i < lenY; i++){
      for(int j = 0; j < lenX; j++){
        mapa[i][j] = 0; 
        xQuadranteNovo = calculaCoord(j);
        yQuadranteNovo = calculaCoord(i);
        if(xQuadranteAntigo != xQuadranteNovo){

        }
      }
    }
    /* geramos junto os quadrantes */
  }

  public void visualizarMapa(){
    /* queremos sempre centralizar o mapa no player */
    /* mas nem sempre isso sera possivel, pois o player pode estar no canto do mapa */
    /* entao vamos centralizar a camera de 20 x 20 (x,y) neste mapa */
    /* o mapa tera inicialmente 100 x 100 */
    /* assim podemos dividi-lo em 5 quadrantes em x e 5 em y */
    int inicioQuadranteX = camera.calculaCoordX();
    int inicioQuadranteY = camera.calculaCoordY();

    for(int i = inicioQuadranteY; i < inicioQuadranteY + camera.tamX; i++){
      for(int j = inicioQuadranteX; j < inicioQuadranteX + camera.tamX; j++){
        /* estamos percorrendo dentro do quadrante */
        System.out.print(mapa[i][j] + " ");
      }
      System.out.println();
    }
  }

  public void printMapaCompleto(){
    for(int i = 0; i < lenY; i++){
      for(int j = 0; j < lenX; j++){
        System.out.print(mapa[i][j]);
      }
      System.out.println();
    }
  }
  /* eu iria implementar para mapas nao quadrados, mas faremos para mapas quadrados */
  /* agora precisamos fazer um algoritmo para fazer as cidades */
  public void decideQuadrante(){
    int aleatorio = random.nextInt(8);
    
  }
}

