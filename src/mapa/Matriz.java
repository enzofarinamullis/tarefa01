package mapa;
import constantes.TamMapa;
import java.util.Random;

public class Matriz {
  int lenX;
  int lenY;
  int playerX;
  int playerY;
  int numCidades;
  int[][] mapa;
  Camera camera;
  Random random;


  private class Cidade{
    String nome;
    int populacao;
    String origem;
    Cidade inimiga;
  }

  private class Camera{
    /* tavez seja mais facil fazer uma classe chamada quadrante que seja uma sub matriz */
    int quadranteX;
    int quadranteY;
    /* vamos adicionar as coordernadas da camera */
    int tamX;
    int tamY;

    /* a camera funcionara da seguinte forma */
    // o x x x  x x x x
    // x x x x
    // x x x x
    // x x x x

    // o := coordenada da camera

    private Camera(){
      /* inicializemos ela no quadrante 1 de 25 */
      quadranteX = 0;
      quadranteY = 0;
      tamX = TamMapa.displayX;
      tamY = TamMapa.displayY;
    }

    private int calculaCoordX(){
      return (quadranteX + 1) * tamX - tamX;
    }
    private int calculaCoordY(){
      return (quadranteY + 1) * tamY - tamX;
    }
  }

  public Matriz(){
    this.lenX = TamMapa.x;
    this.lenY = TamMapa.y;
    mapa = new int[lenY][lenX];
    camera = new Camera();
    random = new Random();
  }

  public void gerarMatriz(){
    for(int i = 0; i < lenY; i++){
      for(int j = 0; j < lenX; j++){
        mapa[i][j] = 0; 
      }
    }
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
    if
  }
}

