package dados;

import constantes.Cores;
import usaveis.cartas.Efeito;

/**
 * Classe abstrata base para todas as entidades do jogo.
 *
 * <p>
 * Define atributos e comportamentos comuns como vida, dano, escudo e energia.
 * Serve como superclasse para os inimigos e o personagem jogável (Herói).
 * </p>
 * <p>
 * Fornecendo métodos importantes para:<br>
 *  - Gerenciar ataques entre as entidades; <br>
 *  - Verificar o estado da entidade (vivo/morto); <br>
 *  - Gerenciar o recebimento de dano, vida e escudo; <br>
 *  - Gerenciar atributos como energia e escudo; <br>
 * </p>
 * <p>
 *  Observações:
 *  - O dano é absorvido primeiramente pelo escudo e somente aplicado à vida,
 *  caso não se haja mais escudo para absorver o dano.
 * </p>
 * <p>
 *  Exemplo de uso:
 * </p>
 *  Entidade slime = new Slime(); <br>
 *  slime.receberDano(10);
 */
public abstract class Entidade {
  protected int vida;
  protected int dano;
  protected int escudo;
  protected int energia;
  protected int energiaLimite;
  protected int id;
  private String nome;
  
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
      System.out.println();
      System.out.println(Cores.ANSI_CYAN + nome + Cores.ANSI_RESET +
        Cores.ANSI_GREEN + " está vivo!" + Cores.ANSI_RESET);
      return true;
    }
    else{
      System.out.println();
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
      " ataca " + Cores.ANSI_CYAN + heroi.getNome() + Cores.ANSI_RESET + "!");
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
  
  public void setaEscudo(int escudo){
    if(escudo < 0){
      return;
    }
    this.escudo = escudo;
  }
  
  public void setaEnergia(int energia){
    this.energia = energia;
  }
  
  public int getEnergia(){
    return energia;
  }
  public int getVida() {
    return vida;
  }

  public int getEnergiaLimite(){
    return energiaLimite;
  }
  
  public String getNome(){
    return nome;
  }
  
  public boolean setNome(String nome){
    this.nome = nome;
    return true;
  }

  public int getEscudo() {
    return escudo;
  }
}
