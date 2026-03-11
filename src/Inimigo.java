public class Inimigo {
  public String nome;
  public int vida;
  public int escudo;
  public int dano;

  /* Construtor */
  public Inimigo(String nome, int vida, int escudo, int dano) {
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
  }

  void receberDano(int dano) {
        
    if (escudo > 0){
      int dano_no_escudo = Math.min(escudo, dano);
      escudo -= dano_no_escudo;
      dano -= dano_no_escudo;
      System.out.println("O escudo absorveu" + dano_no_escudo + "de escudo.");
    }
    System.out.println(nome + "recebeu" + dano + "de dano.");
    vida -= dano;
    if (vida <= 0) {
      System.out.println(nome + "morreu !");
      vida = 0;
    }
    else{
      System.out.println(nome + "tem" + vida + "de vida.");
    }
  }

  void atacar(Heroi heroi) {
    System.out.println(nome + "ataca" + heroi.nome + "!");
    System.out.println("Força do ataque:" + dano + "PF");
    if (dano > 0) {
      heroi.receberDano(dano);
    }
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

  public void printStats(){
    System.out.print(Main.ANSI_YELLOW + nome + Main.ANSI_RESET +
      " Vida: " + Main.ANSI_GREEN + vida + Main.ANSI_RESET + 
      " Dano: " + Main.ANSI_RED + dano + Main.ANSI_RESET +
      " Escudo " + Main.ANSI_BLUE + escudo + Main.ANSI_RESET +
      "\n");
  }
}
