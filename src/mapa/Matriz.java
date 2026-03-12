package mapa;
import constantes.TamMapa;
import java.util.Random;

public class Matriz {
  int lenX;
  int lenY;
  int numCidades;
  public int[][] mapa;
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

  public class Quadrante{
    public int[][] subMapa;
    int x;
    int y;
    int tamX;
    int tamY;

    private Quadrante(int x, int y){
      this.x = calculaCoord(x);
      this.y = calculaCoord(y);
      this.subMapa = new int[TamMapa.displayY][TamMapa.displayX];
      this.tamX = TamMapa.displayX;
      this.tamY = TamMapa.displayY;
    }

    public void transfereSubMapa(Quadrante quadrante){
      /* nao queria ter que percorrer a matriz duas vezes */
      /* mas sera mais facil de implementar e nossa matriz eh pequena */ 
      int x = 0;
      int y = 0;
      /* percorremos o mapa no quadrante */
      for(int i = quadrante.y; i < quadrante.y + quadrante.tamY; i++){
        for(int j = quadrante.x; j < quadrante.x + quadrante.tamY; j++){
          quadrante.subMapa[y][x] = mapa[i][j]; // transferimos o dado para o quadrante
          x++;
        }
        x = 0;
        y++;
      }
    }
  }

  private class Camera{
    Quadrante quadrante;

    private Camera(){
      this.quadrante = null;
    }

    private void moveCamera(Quadrante quadrante){
      this.quadrante = quadrante;
    }
  }

  public Matriz(){
    this.lenX = TamMapa.x;
    this.lenY = TamMapa.y;
    mapa = new int[lenY][lenX];
    camera = new Camera();
    random = new Random();
    proporcao = lenX / TamMapa.displayX;
    matrizQuadrantes = new Quadrante[TamMapa.y][TamMapa.x];
  }


  private int calculaCoord(int x){
    return (x / TamMapa.displayX) * TamMapa.displayX; 
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
          /* transferimos os dados para o novo quadrante */
          matrizQuadrantes[y][x].transfereSubMapa(matrizQuadrantes[y][x]); // eu poderia fazer isso inves de copia
                                                                           // fazer limitadores sup e inf em x e y
          x++;
        }
      }
      x = 0;
      y++;
    }
  }

  public void visualizarMapa(int v, int h){
    for(int i = 0; i < matrizQuadrantes[0][0].tamY; i++){
      for(int j = 0; j < matrizQuadrantes[0][0].tamX; j++){
        System.out.print(matrizQuadrantes[v][h].subMapa[i][j] + " ");
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

