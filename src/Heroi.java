public class Heroi{
  /* Atributos */
  public String nome;
  public int vida;
  public int escudo;
  public int dano;
  public int energia;

  /* Construtor */
  public Heroi(String nome, int vida, int escudo, int dano, int energia) {
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
    this.energia = energia;
  }

  void receberDano(int dano){
      
    if(escudo > 0) {
      int dano_no_escudo = Math.min(escudo, dano);
      escudo -= dano_no_escudo;
      dano -= dano_no_escudo;
      System.out.println("O escudo absorveu" + dano_no_escudo + "de escudo.");
    }

    System.out.println(nome + "recebeu" + dano + "de dano.");
    vida -= dano;

    if(vida <= 0){
      System.out.println(nome + "morreu !");
      vida = 0;
    }
    else{
      System.out.println(nome + "tem" + vida + "de vida.");
    }
      
  }

  void ganharEscudo(int bonusDeEscudo) {
    System.out.println(nome + "ganhou" + bonusDeEscudo + "de escudo.");
    escudo += bonusDeEscudo;
  }

  void ganharVida(int bonusDeVida) {
    System.out.println(nome + "ganhou" + bonusDeVida + "de vida.");
    vida += bonusDeVida;
  }

  Boolean estaVivo() {
    if (vida > 0) {
      System.out.println(nome + "está vivo!");
      return true;
    }
    else{
      System.out.println(nome + "não está vivo!");
      return false;
    } 
  }
  
  void status(){
    System.out.println("Status do" + nome + ":");
    System.out.println("Vida: " + vida);
    System.out.println("Escudo: " + escudo);
    System.out.println("Status: " + (vida > 0 ? "Vivo" : "Morto"));
  }
}