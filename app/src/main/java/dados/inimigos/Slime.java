package dados.inimigos;

import dados.Inimigo;

public class Slime extends Inimigo {
  public Slime(){
    super("Slime Selvagem", 30, 10, 1, "morra seu fedelho mizerável!");
    this.ASCII = "ASCIIInimigos/slime.txt"
  }
}
