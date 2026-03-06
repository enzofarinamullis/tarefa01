public class Heroi{
  public String nome;
  public int vida;
  public int escudo;
  public int dano;

  /* Construtor */
  public Heroi(String nome, int vida, int escudo, int dano){
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
  }
  
  public void recebeDano(int dano){
    this.vida = this.vida - dano;
  }

  public void ganharEscudo(int escudo){
    this.escudo = this.escudo + escudo;
  }

  public int estaVivo(){
    if(this.vida == 0){
      return 0;
    }
    else{
      return 1;
    }
  }
}
