public class Inimigo{
  public String nome;
  public int vida;
  public int escudo;

  public Inimigo(String nome, int vida, int escudo){
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
  }
  
  public void receberDano(int dano){
    this.vida = this.vida - dano;
  }

  public void atacarHeroi(int dano, Heroi heroi){
    heroi.vida = heroi.vida - dano;
  }
}
