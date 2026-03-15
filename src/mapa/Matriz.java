package mapa;
import constantes.TamMapa;
import java.util.Random;

public class Matriz {
  int lenX;
  int lenY;
  int cidadeInicialX;
  int cidadeInicialY;
  int numCidades;
  public char[][] mapa;
  int proporcao;
  /* matriz que representa cada quadrante */
  Quadrante[][] matrizQuadrantes;
  Camera camera;
  Random random;
  

  
  public class Quadrante{
    public char[][] subMapa;
    int x;
    int y;
    int tamX;
    int tamY;
    
    private Quadrante(int x, int y){
      this.x = calculaCoord(x);
      this.y = calculaCoord(y);
      this.subMapa = new char[TamMapa.displayY][TamMapa.displayX];
      this.tamX = TamMapa.displayX;
      this.tamY = TamMapa.displayY;
      for(int i = 0; i < tamY; i++){
        for(int j = 0; j < tamX; j++){
          this.subMapa[i][j] = '░';
        }
      }
    }
    
    public void geraQuadrado(int x, int y, char tipo){
      for(int i = y - 1; i < y + 2; i++){
        for(int j = x - 1; j < x + 2; j++){
          this.subMapa[i][j] = tipo;
        }
      }
      subMapa[y][x] = 'o';
    }
    
    public void geraRegiao(char tipo){
      int limiteX = this.x + this.tamX;
      int limiteY = this.y + this.tamY;
      
      int regiaoX = random.nextInt(this.x + 1, limiteX);
      int regiaoY = random.nextInt(this.y + 1, limiteY);
      geraQuadrado(regiaoX, regiaoY, tipo);
    }
    
    public void printaQuadrante(){
      for(int i = 0; i < this.tamY; i++){
        for(int j = 0; j < this.tamX; j++){
          System.out.print(this.subMapa[i][j] + " ");
        }
        System.out.print("\n");
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
    mapa = new char[lenY][lenX];
    camera = new Camera();
    random = new Random();
    proporcao = lenX / TamMapa.displayX;
    matrizQuadrantes = new Quadrante[TamMapa.y][TamMapa.x];
  }
  
  
  private int calculaCoord(int x){
    return (x / TamMapa.displayX) * TamMapa.displayX;
  }
  
  public void geraSubMapas(){
    int x = 0;
    int y = 0;
    for(int i = 0; i < proporcao; i++){
      for(int j = 0; j < proporcao; j++){
        this.matrizQuadrantes[i][j] = new Quadrante(x, y);
        x += TamMapa.displayX;
      }
      x = 0;
      y += TamMapa.displayY;
    }
  }
  
  public void copiaSubMapas(){
    /* andamos no mapa */
    int ix = 0;
    int iy = 0;
    int x = 0;
    int y = 0;
    
    for(int i = 0; i < this.lenY; i++){
      /* verificamos se mudamos de quadrante */
      if(i % TamMapa.displayY == 0 && i != 0) {
        iy++;
        y = 0;
      }
      
      ix = 0;
      x = 0;
      for(int j = 0; j < this.lenX; j++){
        /* veririficamos se mudamos de quadrante */
        if(j % TamMapa.displayX == 0 && j != 0){
          ix++;
          x = 0;
        }
        mapa[i][j] = matrizQuadrantes[iy][ix].subMapa[y][x];
        x++;
      }
      y++;
    }
  }
  
  
  public void gerarMapa(){
    this.geraSubMapas();
    decideQuadrante();
    copiaSubMapas();
    
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
    /* IMPORTANTE! Vamos querer que o primeiro quadrante sempre seja cidade */
    //matrizQuadrantes[0][0].viraCidade();
    matrizQuadrantes[0][0].geraRegiao('█');
    matrizQuadrantes[0][0].printaQuadrante();
  }
}