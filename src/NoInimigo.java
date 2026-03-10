public class NoInimigo {
  Inimigo inimigo;
  NoInimigo prox; 
  NoInimigo ant;

  public NoInimigo(Inimigo inimigo){
    this.inimigo = inimigo;
    this.prox = null;
  }
}
