package mapa;
import constantes.TamMapa;
import java.util.Random;
import java.util.Objects;

import javax.lang.model.element.QualifiedNameable;

public class Matriz {
  int lenX;
  int lenY;
  int numCidades;
  int[][] mapa;
  int proporcao;
  /* matriz que representa cada quadrante */
  Quadrante[][] matrizQuadrantes;
  Camera camera;
  Random random;


  private class Cidade{
    String nome;
    int populacao;
    String origem;
    Cidade inimiga;
  }

  private class Quadrante{
    int[][] subMapa;
    int x;
    int y;
    int tamX;
    int tamY;

    private Quadrante(int x, int y){
      this.x = calculaCoord(x);
      this.y = calculaCoord(y);
      this.subMapa = new int[proporcao][proporcao];
      this.tamX = TamMapa.displayX;
      this.tamY = TamMapa.displayY;
    }

    private void transfereSubMapa(){}
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
    matrizQuadrantes = new Quadrante[proporcao][proporcao];
  }


  private int calculaCoord(int x){
    return (x + 1) * TamMapa.displayX - TamMapa.displayX;
  }


  public void gerarMatriz(){

    /* geramos junto a matriz quadrante */
    int xQuadranteAntigo = 0, xQuadranteNovo;
    int yQuadranteAntigo = 0, yQuadranteNovo; 
    int x = 0;
    int y = 0;
    matrizQuadrantes[y][x] = new Quadrante(xQuadranteAntigo, yQuadranteAntigo);
    x++;
    /* inserir na matriz de quadrantes */
    for(int i = 0; i < lenY; i++){
      for(int j = 0; j < lenX; j++){
        mapa[i][j] = 0; 

        xQuadranteNovo = calculaCoord(j);
        yQuadranteNovo = calculaCoord(i);

        if(xQuadranteAntigo != xQuadranteNovo || yQuadranteAntigo != yQuadranteNovo){
          matrizQuadrantes[y][x] = new Quadrante(xQuadranteNovo, yQuadranteNovo);
          x++;
        }
      }
      x = 0;
      y++;
    }
  }

  public void visualizarMapa(){
    for(int i = 0; i < matrizQuadrantes[0][0].tamY; i++){
      for(int j = 0; j < matrizQuadrantes[0][0].tamX; j++){
        System.out.println(matrizQuadrantes[][]);
      }
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

