public class Heroi{
  /* Atributos */
  public String nome;
  public int vida;
  public int escudo;
  public int energia;
  public FilaCartasEscudo cartasEscudo;
  public DequeCartas deque;

  /* Construtor */
  public Heroi(String nome, int vida, int escudo, int energia, DequeCartas deque, 
    FilaCartasEscudo cartasEscudo) {
    this.nome = nome;
    this.vida = vida;
    this.escudo = escudo;
    this.energia = energia;
    this.deque = deque;
    this.cartasEscudo = cartasEscudo;
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
    System.out.println(nome + "ganhou" + Main.ANSI_GREEN + bonusDeVida + Main.ANSI_RESET + "de vida.");
    vida += bonusDeVida;
  }

  Boolean estaVivo() {
    if (vida > 0) {
      System.out.println(nome + Main.ANSI_GREEN + "está vivo!" + Main.ANSI_RESET);
      return true;
    }
    else{
      System.out.println(nome + Main.ANSI_RED + "não está vivo!" + Main.ANSI_RESET);
      return false;
    } 
  }
  
  public boolean temEnergia(int energiaCarta) {
    return energia >= energiaCarta;
  }

  void status(){
    System.out.println(Main.ANSI_CYAN + "Status do " + Main.ANSI_RESET + nome +
     Main.ANSI_CYAN + ":" + Main.ANSI_RESET);
    System.out.println("Vida: " + Main.ANSI_GREEN + vida + Main.ANSI_RESET);
    System.out.println("Escudo: " + Main.ANSI_BLUE + escudo + Main.ANSI_RESET);
    System.out.println("Status: " + (vida > 0 ? Main.ANSI_GREEN + "Vivo" + Main.ANSI_RESET :
     Main.ANSI_RED + "Morto" + Main.ANSI_RESET));
  }
}