package mapa;
import constantes.Cores;
import constantes.TamMapa;
import mapa.estruturas.Cidade;
import mapa.estruturas.Dungeon;
import mapa.estruturas.Estrutura;
import mapa.estruturas.nomes.Nomes;
import mapa.estruturas.nomes.NomesCidades;
import mapa.estruturas.nomes.NomesDungeon;
import constantes.IdsEstruturas;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
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
  Nomes nomesCidades;
  Nomes nomesDungeon;
  /* lista de todas as estruturas do game */
  List<Estrutura> Lugares = new ArrayList<>();
  
  public class Quadrante{
    public int[][] subMapa;
    Estrutura estrutura;
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
      estrutura = null;
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
      if(tipo == IdsEstruturas.ID_CIDADE){
        this.estrutura = new Cidade(x, y);
      }
      else if(tipo == IdsEstruturas.ID_DUNGEON){
        this.estrutura = new Dungeon(x, y);
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
          if(subMapa[i][j] == IdsEstruturas.ID_GRAMA) {
            System.out.print(Cores.COR_MUSGO_1 + "▒ " + Cores.ANSI_RESET);
          }
          else if(subMapa[i][j] == IdsEstruturas.ID_CIDADE){
            System.out.print(Cores.ANSI_RED + "█ " + Cores.ANSI_RESET);
          }
          else if(subMapa[i][j] == IdsEstruturas.ID_DUNGEON){
            System.out.print(Cores.COR_CIMENTO_1 + "█ " + Cores.ANSI_RESET);
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
    nomesCidades = new NomesCidades();
    nomesDungeon = new NomesDungeon();
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
    while(Lugares.size() < 10) {
      this.geraSubMapas();
      decideQuadrante();
      atribuiNomes();
      copiaSubMapas();
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
        if(mapa[i][j] == IdsEstruturas.ID_GRAMA) {
          System.out.print(Cores.COR_MUSGO_1 + "▒ " + Cores.ANSI_RESET);
        }
        else if(mapa[i][j] == IdsEstruturas.ID_CIDADE){
          System.out.print(Cores.ANSI_ORANGE + "█ " + Cores.ANSI_RESET);
        }
        else if(mapa[i][j] == IdsEstruturas.ID_DUNGEON){
          System.out.print(Cores.COR_CIMENTO_1 + "█ " + Cores.ANSI_RESET);
        }
        else if(mapa[i][j] > 9){
          System.out.print(Cores.ANSI_CYAN + mapa[i][j] + Cores.ANSI_RESET);
        }
        else{
          System.out.print(Cores.ANSI_CYAN + mapa[i][j] + " " + Cores.ANSI_RESET);
        }
      }
      System.out.println();
    }
    System.out.println("Lugares:");
    Estrutura atual;
    for(int i = 0; i < Lugares.size(); i++){
      atual = Lugares.get(i);
      System.out.println(Cores.ANSI_CYAN + atual.indice + " - " +
        Cores.ANSI_YELLOW + atual.nome + Cores.ANSI_RESET);
    }
  }
  

  /* eu iria implementar para mapas nao quadrados, mas faremos para mapas quadrados */
  /* agora precisamos fazer um algoritmo para fazer as cidades */
  public void decideQuadrante(){
    /* IMPORTANTE! Vamos querer que o primeiro quadrante sempre seja cidade */
    //matrizQuadrantes[0][0].viraCidade();
    matrizQuadrantes[0][0].geraRegiao(101);
    this.cidadeInicialX = matrizQuadrantes[0][0].buscaRegiaoX();
    this.cidadeInicialY = matrizQuadrantes[0][0].buscaRegiaoY();
    
    /* agora damos uma chance para o quadrante virar algo */
    /* 30 % de chance de virar algo */
    Quadrante atual;
    for(int i = 0; i < proporcao; i++){
      /* comecamos em 1, pois no quadrante 0 0 ja temos uma cidade 100 % das vezes*/
      for(int j = 1; j < proporcao; j++){
        atual = matrizQuadrantes[i][j];
        if(random.nextInt(100) < TamMapa.probRegiao){
          /* decidimos a probabilidade de oque virar */
          if(random.nextInt(1000) > 500) {
            atual.geraRegiao(IdsEstruturas.ID_CIDADE);
          }
          else{
            atual.geraRegiao(IdsEstruturas.ID_DUNGEON);
          }
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
  
  public void atribuiNomes() {
    Quadrante atual;
    int indice = 1;
    int x;
    int y;
    for (int i = 0; i < proporcao; i++) {
      for (int j = 0; j < proporcao; j++) {
        atual = matrizQuadrantes[i][j];
        if (atual.estrutura != null) {
          /* verificamos o tipo da cidade */
          if (atual.estrutura.ehCidade) {
            /* Escolhemos o nome */
            atual.estrutura.nome = nomesCidades.escolheNome();
            /* definimos o indice */
            atual.estrutura.indice = indice;
            x = atual.estrutura.x;
            y = atual.estrutura.y;
            atual.subMapa[y][x] = indice;
            indice++;
            Lugares.add(atual.estrutura);
          } else if (atual.estrutura.ehDungeon) {
            atual.estrutura.nome = nomesDungeon.escolheNome();
            atual.estrutura.indice = indice;
            x = atual.estrutura.x;
            y = atual.estrutura.y;
            atual.subMapa[y][x] = indice;
            indice++;
            Lugares.add(atual.estrutura);
          }
        }
      }
    }
  }
}