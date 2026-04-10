package dados;
import constantes.Cores;
import usaveis.Mao;
import usaveis.cartas.Carta;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;

/**
 * Representa o personagem controlado pelo jogador.
 * <p>
 * Estende {@link Entidade} e adiciona o sistema de cartas do jogador,
 * incluindo mão, pilha de compra (baralho) e pilha de descarte.
 * </p>
 * <p>
 * Responsabilidades:<br>
 *  - Gerenciar recursos como vida, escudo e energia<br>
 *  - Controlar o uso de cartas baseado na energia disponível<br>
 *  - Manter e manipular o estado do baralho do jogador<br>
 * </p>
 *
 * Exemplo de uso:<br>
 * Heroi heroi = new Heroi();<br>
 * if(heroi.temEnergia(3)){<br>
 *   // pode jogar carta<br>
 * }
 */
public class Heroi extends Entidade {
  /* Atributos */
  private Mao mao;
  private PilhaCompra pilhaCompra;
  private PilhaDescarte pilhaDescarte;

  /* Construtor */
  public Heroi(){
    this.vida = 5;
    this.escudo = 0;
    this.energia = 20;
    this.energiaLimite = 20;
    this.mao = new Mao();
    this.pilhaCompra = new PilhaCompra();
    pilhaCompra.embraralhaPilha();
    this.pilhaDescarte = new PilhaDescarte();
  }

  public boolean temEnergia(int energiaCarta) {
    return energia >= energiaCarta;
  }
  
  public void status(){
    System.out.println(Cores.ANSI_CYAN + "Status de " + Cores.ANSI_RESET + getNome() +
     Cores.ANSI_CYAN + ":" + Cores.ANSI_RESET);
    System.out.println("Vida: " + Cores.ANSI_RED + vida + Cores.ANSI_RESET);
    System.out.println("Escudo: " + Cores.ANSI_BLUE + escudo + Cores.ANSI_RESET);
    System.out.println("Energia: " + Cores.ANSI_GREEN + energia + Cores.ANSI_RESET);
    System.out.println("Status: " + (vida > 0 ? Cores.ANSI_GREEN + "Vivo" + Cores.ANSI_RESET :
     Cores.ANSI_RED + "Morto" + Cores.ANSI_RESET));
  }

  public boolean verificaEnergia(){
    Carta atual = mao.cartas.getFirst();
    int indice = 0;
    while(indice != mao.cartas.size()){
      if(atual.getCustoEnergia() <= energia){
        return true;
      }
      atual = mao.cartas.get(indice);
      indice++;
    }
    return false;
  }
  
  public Mao getMao(){
    return mao;
  }
  public PilhaCompra getPilhaCompra(){
    return pilhaCompra;
  }
  public PilhaDescarte getPilhaDescarte(){
    return pilhaDescarte;
  }
}