package mapa.estruturas;

public class Dungeon extends Estrutura {
  public Dungeon(int x, int y) {
    super(x, y);
    ehCidade = false;
    ehDungeon = true;
  }
}
