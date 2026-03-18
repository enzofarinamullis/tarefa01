package dados;
import constantes.Cores;
import usaveis.Cartas;
import usaveis.Mao;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;


public class Heroi extends Seres {
  /* Atributos */
  public Mao mao;
  public PilhaCompra pilhaCompra;
  public PilhaDescarte pilhaDescarte;

  /* Construtor */
  public Heroi(String nome, int vida, int energia){
    this.nome = nome;
    this.vida = vida;
    this.escudo = 0;
    this.energia = energia;
    this.energiaLimite = 20;
    this.mao = new Mao();
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