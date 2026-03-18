package dados;

import constantes.Cores;

public class Seres {
  public int vida;
  public int dano;
  public int escudo;
  public int energia;
  public int energiaLimite;
  public int id;
  public String nome;
  
  public void receberDano(int dano){
    if(escudo > 0) {
      int dano_no_escudo = Math.min(escudo, dano);
      escudo -= dano_no_escudo;
      dano -= dano_no_escudo;
      System.out.println("O " + Cores.ANSI_BLUE + "escudo" + Cores.ANSI_RESET + " absorveu " + Cores.ANSI_RED + dano_no_escudo +
        Cores.ANSI_RESET + " de " + Cores.ANSI_RED + "dano" + Cores.ANSI_RED);
    }
    
    System.out.println(Cores.ANSI_CYAN + nome + Cores.ANSI_RESET + " recebeu " +
      Cores.ANSI_RED + dano + Cores.ANSI_RESET + " de dano.");
    vida -= dano;
  }
  
  public Boolean estaVivo() {
    if (vida > 0) {
      System.out.println(Cores.ANSI_CYAN + nome + Cores.ANSI_RESET +
        Cores.ANSI_GREEN + " está vivo!" + Cores.ANSI_RESET);
      return true;
    }
    else{
      System.out.println(Cores.ANSI_CYAN + nome + Cores.ANSI_RESET +
        Cores.ANSI_RED + " não está vivo!" + Cores.ANSI_RESET);
      return false;
    }
  }
  
  public void ganharVida(int bonusDeVida) {
    System.out.println(nome + "ganhou" + Cores.ANSI_GREEN + bonusDeVida + Cores.ANSI_RESET + "de vida.");
    vida += bonusDeVida;
  }
  
  public void atacar(Heroi heroi) {
    System.out.println(Cores.ANSI_YELLOW + nome + Cores.ANSI_RESET +
      " ataca " + Cores.ANSI_CYAN + heroi.nome + Cores.ANSI_RESET + "!");
    System.out.println("Força do ataque: " + Cores.ANSI_RED + dano + Cores.ANSI_RESET);
    if (dano > 0) {
      heroi.receberDano(dano);
    }
  }
  
  
  public void printStats(){
    System.out.print(Cores.ANSI_YELLOW + nome + Cores.ANSI_RESET +
      " Vida: " + Cores.ANSI_GREEN + vida + Cores.ANSI_RESET +
      " Dano: " + Cores.ANSI_RED + dano + Cores.ANSI_RESET +
      " Escudo " + Cores.ANSI_BLUE + escudo + Cores.ANSI_RESET +
      "\n");
  }
  
  public void ganharEscudo(int bonusDeEscudo) {
    System.out.println("O " + Cores.ANSI_CYAN + nome + Cores.ANSI_RESET + " ganhou " +
      Cores.ANSI_BLUE + bonusDeEscudo + Cores.ANSI_RESET + " de escudo.");
    escudo += bonusDeEscudo;
  }
  
  
  
}
