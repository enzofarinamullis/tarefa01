package mapa;
import constantes.Cores;
import constantes.TamMapa;
import java.util.Random;

public class Matriz {
  public int lenX;
  public int lenY;
  public int cidadeInicialX;
  public int cidadeInicialY;
  public int numCidades;
  public int[][] mapa;
  public int proporcao;
  /* matriz que representa cada quadrante */
  public Quadrante[][] matrizQuadrantes;
  Camera camera;
  Random random;
  

  
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
      for(int i = 0; i < tamY; i++){
        for(int j = 0; j < tamX; j++){
          this.subMapa[i][j] = 0;
        }
      }
    }
    
    public void geraQuadrado(int x, int y, int tipo){
      for(int i = y - 1; i < y + 2; i++){
        for(int j = x - 1; j < x + 2; j++){
          this.subMapa[i][j] = tipo;
        }
      }
      subMapa[y][x] = 9;
    }
    
    public void geraRegiao(int tipo){
      int limiteX = this.x + this.tamX;
      int limiteY = this.y + this.tamY;
      /* ja estamos trabalhado com o quadrante */
      /* damos apenas um espacamento da borda para depois fazermos os caminhos */
      int regiaoX = random.nextInt(2, this.tamX - 2);
      int regiaoY = random.nextInt(2, this.tamY - 2);
      
      geraQuadrado(regiaoX, regiaoY, tipo);
    }
    
    public void printaQuadrante(){
      for(int i = 0; i < this.tamY; i++){
        for(int j = 0; j < this.tamX; j++){
          if(subMapa[i][j] == 0) {
            System.out.print(Cores.COR_MUSGO_1 + "▒ " + Cores.ANSI_RESET);
          }
          if(subMapa[i][j] == 1 || subMapa[i][j] == 9){
            System.out.print(Cores.ANSI_RED + "█ " + Cores.ANSI_RESET);
          }
        }
        System.out.print("\n");
      }
      System.out.println();
    }
    
    public int buscaRegiaoX() {
      for (int i = 0; i < TamMapa.displayY; i++) {
        for (int j = 0; j < TamMapa.displayX; j++) {
          if (this.subMapa[i][j] == 9) {
            return this.x + j;
          }
        }
      }
      return -1;
    }
    
    public int buscaRegiaoY() {
      for (int i = 0; i < TamMapa.displayY; i++) {
        for (int j = 0; j < TamMapa.displayX; j++) {
          if (this.subMapa[i][j] == 9) {
            return this.y + i;
          }
        }
      }
      return -1;
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
        if(mapa[i][j] == 0) {
          System.out.print(Cores.COR_MUSGO_1 + "▒ " + Cores.ANSI_RESET);
        }
        if(mapa[i][j] == 1 || mapa[i][j] == 9){
          System.out.print(Cores.ANSI_RED + "█ " + Cores.ANSI_RESET);
        }
      }
      System.out.println();
    }
  }
  

  /* eu iria implementar para mapas nao quadrados, mas faremos para mapas quadrados */
  /* agora precisamos fazer um algoritmo para fazer as cidades */
  public void decideQuadrante(){
    /* IMPORTANTE! Vamos querer que o primeiro quadrante sempre seja cidade */
    //matrizQuadrantes[0][0].viraCidade();
    matrizQuadrantes[0][0].geraRegiao(1);
    this.cidadeInicialX = matrizQuadrantes[0][0].buscaRegiaoX();
    this.cidadeInicialY = matrizQuadrantes[0][0].buscaRegiaoY();
    
    /* agora damos uma chance para o quadrante virar algo */
    /* 30 % de chance de virar algo */
    for(int i = 0; i < proporcao; i++){
      /* comecamos em 1, pois no quadrante 0 0 ja temos uma cidade 100 % das vezes*/
      for(int j = 1; j < proporcao; j++){
        if(random.nextInt(100) < TamMapa.probRegiao){
          /* decidimos a probabilidade de oque virar */
          matrizQuadrantes[i][j].geraRegiao(1);
        }
      }
    }

  }
  
  public void imprimeTodosQuadrantes(){
    for(int i = 0; i < proporcao; i++){
      for(int j = 0; j < proporcao; j++){
        matrizQuadrantes[i][j].printaQuadrante();
      }
    }
  }
}