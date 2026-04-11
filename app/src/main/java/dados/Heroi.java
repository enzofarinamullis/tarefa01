package dados;

import constantes.Cores;
import usaveis.Mao;
import usaveis.cartas.Carta;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;

/**
 * Representa o personagem controlado pelo jogador.
 * 
 * <p>
 * Estende {@link Entidade} e adiciona o sistema de cartas do jogador,
 * incluindo mão, pilha de compra (baralho) e pilha de descarte.
 * </p>
 * 
 * <p>
 * Responsabilidades:<br>
 * - Gerenciar recursos como vida, escudo e energia<br>
 * - Controlar o uso de cartas baseado na energia disponível<br>
 * - Manter e manipular o estado do baralho do jogador<br>
 * </p>
 *
 * <p>
 * Exemplo de uso:
 * <pre>
 * Heroi heroi = new Heroi();
 * if(heroi.temEnergia(3)) {
 *     // pode jogar carta
 * }
 * </pre>
 * </p>
 * @see Entidade
 * @see usaveis.Mao
 * @see usaveis.pilhas.PilhaCompra
 * @see usaveis.pilhas.PilhaDescarte
 */
public class Heroi extends Entidade {
  
  /* Atributos */
  
  /**
   * Mão do herói contendo as cartas que ele pode jogar atualmente.
   */
  private Mao mao;
  
  /**
   * Pilha de compra (baralho) de onde o herói compra novas cartas.
   */
  private PilhaCompra pilhaCompra;
  
  /**
   * Pilha de descarte onde as cartas usadas ou descartadas são armazenadas.
   */
  private PilhaDescarte pilhaDescarte;

  /* Construtor */
  
  /**
   * Construtor padrão que inicializa o herói com valores padrão e configura o sistema de cartas.
   * 
   * <p>Valores iniciais:
   * <ul>
   *   <li>Vida: 5</li>
   *   <li>Escudo: 0</li>
   *   <li>Energia: 20</li>
   *   <li>Limite de energia: 20</li>
   * </ul>
   * </p>
   * 
   * <p>O baralho (pilha de compra) é criado e automaticamente embaralhado.
   * A mão e a pilha de descarte são inicializadas vazias.</p>
   */
  public Heroi() {
    this.vida = 5;
    this.escudo = 0;
    this.energia = 20;
    this.energiaLimite = 20;
    this.mao = new Mao();
    this.pilhaCompra = new PilhaCompra();
    pilhaCompra.embraralhaPilha();
    this.pilhaDescarte = new PilhaDescarte();
  }

  /**
   * Verifica se o herói possui energia suficiente para jogar uma carta.
   * 
   * @param energiaCarta custo de energia da carta que se deseja jogar
   * @return {@code true} se a energia atual for maior ou igual ao custo da carta,
   *         {@code false} caso contrário
   */
  public boolean temEnergia(int energiaCarta) {
    return energia >= energiaCarta;
  }
  
  /**
   * Exibe o status atual do herói no console com formatação colorida.
   * 
   * <p>Informações exibidas:
   * <ul>
   *   <li>Nome do herói (ciano)</li>
   *   <li>Vida atual (vermelho)</li>
   *   <li>Escudo atual (azul)</li>
   *   <li>Energia atual (verde)</li>
   *   <li>Status: Vivo (verde) ou Morto (vermelho)</li>
   * </ul>
   * </p>
   * 
   * @see constantes.Cores
   */
  public void status() {
    System.out.println(Cores.ANSI_CYAN + "Status de " + Cores.ANSI_RESET + getNome() +
     Cores.ANSI_CYAN + ":" + Cores.ANSI_RESET);
    System.out.println("Vida: " + Cores.ANSI_RED + vida + Cores.ANSI_RESET);
    System.out.println("Escudo: " + Cores.ANSI_BLUE + escudo + Cores.ANSI_RESET);
    System.out.println("Energia: " + Cores.ANSI_GREEN + energia + Cores.ANSI_RESET);
    System.out.println("Status: " + (vida > 0 ? Cores.ANSI_GREEN + "Vivo" + Cores.ANSI_RESET :
     Cores.ANSI_RED + "Morto" + Cores.ANSI_RESET));
  }

  /**
   * Verifica se existe pelo menos uma carta na mão que possa ser jogada
   * com a energia atual disponível.
   * 
   * <p>Percorre todas as cartas na mão do herói e verifica se alguma delas
   * tem custo de energia menor ou igual à energia atual.</p>
   * 
   * @return {@code true} se houver pelo menos uma carta jogável,
   *         {@code false} caso contrário
   */
  public boolean verificaEnergia() {
    Carta atual = mao.cartas.getFirst();
    int indice = 0;
    while(indice != mao.cartas.size()) {
      if(atual.getCustoEnergia() <= energia) {
        return true;
      }
      atual = mao.cartas.get(indice);
      indice++;
    }
    return false;
  }
  
  /**
   * Retorna a mão atual do herói.
   * 
   * @return objeto {@link usaveis.Mao} contendo as cartas na mão do herói
   */
  public Mao getMao() {
    return mao;
  }
  
  /**
   * Retorna a pilha de compra (baralho) do herói.
   * 
   * @return objeto {@link usaveis.pilhas.PilhaCompra} representando o baralho
   */
  public PilhaCompra getPilhaCompra() {
    return pilhaCompra;
  }
  
  /**
   * Retorna a pilha de descarte do herói.
   * 
   * @return objeto {@link usaveis.pilhas.PilhaDescarte} representando o descarte
   */
  public PilhaDescarte getPilhaDescarte() {
    return pilhaDescarte;
  }
}