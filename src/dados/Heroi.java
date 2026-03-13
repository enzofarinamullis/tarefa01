package dados;
import constantes.Cores;
import usaveis.Cartas;
import usaveis.Mao;


public class Heroi{
  /* Atributos */
  public String nome;
  public int vida;
  public int escudo;
  public int energia;
  public int energiaLimite;
  public Mao mao;

  /* Construtor */
  public Heroi(String nome, int vida, int energia){
    this.nome = nome;
    this.vida = vida;
    this.escudo = 0;
    this.energia = energia;
    this.energiaLimite = 20;
    this.mao = new Mao();
  }

  public void receberDano(int dano){
      
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

  public void ganharEscudo(int bonusDeEscudo) {
    System.out.println(nome + "ganhou" + bonusDeEscudo + "de escudo.");
    escudo += bonusDeEscudo;
  }

  public void ganharVida(int bonusDeVida) {
    System.out.println(nome + "ganhou" + Cores.ANSI_GREEN + bonusDeVida + Cores.ANSI_RESET + "de vida.");
    vida += bonusDeVida;
  }

  public Boolean estaVivo() {
    if (vida > 0) {
      System.out.println(nome + Cores.ANSI_GREEN + "está vivo!" + Cores.ANSI_RESET);
      return true;
    }
    else{
      System.out.println(nome + Cores.ANSI_RED + "não está vivo!" + Cores.ANSI_RESET);
      return false;
    } 
  }
  
  public boolean temEnergia(int energiaCarta) {
    return energia >= energiaCarta;
  }

  public void status(){
    System.out.println(Cores.ANSI_CYAN + "Status de " + Cores.ANSI_RESET + nome +
     Cores.ANSI_CYAN + ":" + Cores.ANSI_RESET);
    System.out.println("Vida: " + Cores.ANSI_RED + vida + Cores.ANSI_RESET);
    System.out.println("Escudo: " + Cores.ANSI_BLUE + escudo + Cores.ANSI_RESET);
    System.out.println("Energia: " + Cores.ANSI_GREEN + energia + Cores.ANSI_RESET);
    System.out.println("Status: " + (vida > 0 ? Cores.ANSI_GREEN + "Vivo" + Cores.ANSI_RESET :
     Cores.ANSI_RED + "Morto" + Cores.ANSI_RESET));
  }

  public boolean verificaEnergia(){
    Cartas atual = mao.raiz;
    while(atual != null){
      if(atual.custoEnergia <= energia){
        return true;
      }
      atual = atual.prox;
    }
    return false;
  }
}