package mapa.estruturas;

public abstract class Estrutura {
  public boolean ehDungeon;
  public boolean ehCidade;
  public String nome;
  public String descricao;
  public int x;
  public int y;
  public int indice;
  
  public Estrutura(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
