package dados;
import constantes.Cores;

public class Inimigo {
  public String nome;
  public int vida;
  public int escudo;
  public int dano;
  int id;

  /* Construtor */
  public Inimigo(String nome, int vida, int escudo, int dano) {
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
    this.id = -1; // colocamos como -1 para quando nao tiver sido fornecido um id (ainda)
  }

  public void receberDano(int dano) {
        
    if (escudo > 0){
      int dano_no_escudo = Math.min(escudo, dano);
      escudo -= dano_no_escudo;
      dano -= dano_no_escudo;
      System.out.print("O ");
      Cores.cprint(Cores.ANSI_BLUE, "escudo");
      System.out.print(" absorveu ");
      Cores.cprintInt(Cores.ANSI_RED ,dano_no_escudo);
      Cores.cprintn(Cores.ANSI_RED, " de dano.");

    }
    Cores.cprint(Cores.ANSI_YELLOW, nome);
    System.out.println(" recebeu ");
    Cores.cprintInt(Cores.ANSI_RED, dano);
    System.out.print(" de dano.\n");

    vida -= dano;
    if (vida <= 0) {
      Cores.cprint(Cores.ANSI_YELLOW, nome);
      Cores.cprintn(Cores.ANSI_RED, " morreu!");
      vida = 0;
    }
    else{
      Cores.cprint(Cores.ANSI_YELLOW, nome);
      System.out.print(" tem "); 
      Cores.cprintInt(Cores.ANSI_GREEN, vida);
      System.out.print(" de vida.\n");
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
      Cores.cprint(Cores.ANSI_YELLOW, nome);
      System.out.print(" está vivo!\n");
      return true;
    }
    else{
      Cores.cprint(Cores.ANSI_YELLOW, nome);
      System.out.print(" não está vivo!\n");
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
