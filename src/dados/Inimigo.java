package dados;
import constantes.Cores;

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

  public void receberDano(int dano) {
        
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

  public void atacar(Heroi heroi) {
    System.out.println(nome + "ataca" + heroi.nome + "!");
    System.out.println("Força do ataque:" + dano + "PF");
    if (dano > 0) {
      heroi.receberDano(dano);
    }
  }

  public Boolean estaVivo() {
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
    System.out.print(Cores.ANSI_YELLOW + nome + Cores.ANSI_RESET +
      " Vida: " + Cores.ANSI_GREEN + vida + Cores.ANSI_RESET + 
      " Dano: " + Cores.ANSI_RED + dano + Cores.ANSI_RESET +
      " Escudo " + Cores.ANSI_BLUE + escudo + Cores.ANSI_RESET +
      "\n");
  }
}
