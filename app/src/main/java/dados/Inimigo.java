package dados;

import java.util.ArrayList;
import java.util.List;

import constantes.Cores;
import usaveis.cartas.Efeito;

/**
 * Representa uma entidade inimiga.
 *
 * <p>
 * Estende {@link Entidade} e adiciona comportamentos específicos de um inimigo
 * como o anúncio de ataque e aplicação de efeitos no herói.
 * </p>
 * 
 * <p>
 * Responsabilidades:<br>
 * - Gerenciar o dano recebido com lógica de escudo<br>
 * - Exibir mensagens de combate no terminal<br>
 * - Aplicar efeitos ao herói<br>
 * </p>
 * 
 * <p>
 * Cada inimigo pode possuir uma lista de {@link Efeito} que são aplicados
 * durante o combate, cada efeito terá um momento de atuação durante o combate.
 * </p>
 * 
 * <p>
 * Exemplo de uso:
 * <pre>
 * Inimigo slime = new Inimigo("Slime Selvagem", 30, 10, 1, "morra seu fedelho miserável!");
 * slime.anunciar();
 * </pre>
 * </p>
 * @see Entidade
 * @see usaveis.cartas.Efeito
 * @see Heroi
 */
public class Inimigo extends Entidade {
  
  /**
   * Mensagem de anúncio que o inimigo fala ao iniciar o combate.
   */
  protected String anuncio;
  
  /**
   * Caminho ou representação ASCII da arte do inimigo.
   */
  protected String ASCII;
  
  /**
   * Lista de efeitos que o inimigo pode aplicar ao herói durante o combate.
   */
  protected List<Efeito> listaEfeitos;
  
  /* Construtor */
  
  /**
   * Construtor que inicializa um inimigo com seus atributos básicos.
   * 
   * <p>O identificador (id) é inicializado com -1, indicando que nenhum ID foi fornecido ainda.
   * A lista de efeitos é inicializada como uma ArrayList vazia.</p>
   * 
   * @param nome nome do inimigo
   * @param vida pontos de vida iniciais do inimigo
   * @param escudo pontos de escudo iniciais do inimigo
   * @param dano quantidade de dano que o inimigo causa em ataques
   * @param anuncio mensagem que o inimigo fala ao anunciar sua presença
   */
  public Inimigo(String nome, int vida, int escudo, int dano, String anuncio) {
    setNome(nome);
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
    this.id = -1; // colocamos como -1 para quando não tiver sido fornecido um id (ainda)
    this.anuncio = anuncio;
    this.listaEfeitos = new ArrayList<>();
  }
  
  /**
   * Aplica dano ao inimigo com lógica de absorção por escudo.
   * 
   * <p>O dano é primeiramente absorvido pelo escudo. Se o escudo for insuficiente,
   * o dano restante é aplicado à vida. O método exibe mensagens coloridas informando
   * a quantidade de dano absorvido e o dano recebido.</p>
   * 
   * <p>Se a vida do inimigo chegar a 0 ou menos, uma mensagem de morte é exibida
   * e a vida é ajustada para 0. Caso contrário, exibe a vida restante.</p>
   * 
   * @param dano quantidade de dano a ser aplicada ao inimigo
   * @see constantes.Cores#cprint(String, String)
   * @see constantes.Cores#cprintInt(String, int)
   * @see constantes.Cores#cprintn(String, String)
   */
  @Override
  public void receberDano(int dano) {
        
    if (escudo > 0) {
      int dano_no_escudo = Math.min(escudo, dano);
      escudo -= dano_no_escudo;
      dano -= dano_no_escudo;
      System.out.print("O ");
      Cores.cprint(Cores.ANSI_BLUE, "escudo");
      System.out.print(" absorveu ");
      Cores.cprintInt(Cores.ANSI_RED, dano_no_escudo);
      Cores.cprintn(Cores.ANSI_RED, " de dano.");
    }
    
    Cores.cprint(Cores.ANSI_YELLOW, getNome());
    System.out.println(" recebeu ");
    Cores.cprintInt(Cores.ANSI_RED, dano);
    System.out.print(" de dano.\n");

    vida -= dano;
    if (vida <= 0) {
      Cores.cprint(Cores.ANSI_YELLOW, getNome());
      Cores.cprintn(Cores.ANSI_RED, " morreu!");
      vida = 0;
    }
    else {
      Cores.cprint(Cores.ANSI_YELLOW, getNome());
      System.out.print(" tem "); 
      Cores.cprintInt(Cores.ANSI_GREEN, vida);
      System.out.print(" de vida.\n");
    }
  }
  
  /**
   * Retorna a mensagem de anúncio do inimigo.
   * 
   * @return mensagem de anúncio
   */
  public String getAnuncio() {
    return anuncio;
  }

  /**
   * Exibe o anúncio do inimigo no console com formatação colorida.
   * 
   * <p>O nome do inimigo é exibido em vermelho e o anúncio em azul.</p>
   */
  public void anunciar() {
    System.out.println();
    System.out.println("Anúncio:");
    System.out.println(Cores.ANSI_RED + getNome() + ": " +
      Cores.ANSI_BLUE + getAnuncio() + Cores.ANSI_RESET);
  }

  /**
   * Aplica todos os efeitos do inimigo ao herói.
   * 
   * <p>Percorre a lista de efeitos e aplica cada um deles ao herói
   * através do método {@link Efeito#aplicar(Heroi)}.</p>
   * 
   * @param heroi herói que receberá os efeitos
   */
  public void usarEfeitoHeroi(Heroi heroi) {
    Efeito efeito;
    if(!listaEfeitos.isEmpty()) {
      for(int i = 0; i < listaEfeitos.size(); i++) {
        efeito = listaEfeitos.get(i);
        efeito.aplicar(heroi);
      }
    }
  }
  
  /**
   * Retorna o caminho da arte ASCII do inimigo.
   * 
   * @return string contendo o caminho ou representação ASCII
   */
  public String getCaminho() {
    return ASCII;
  }
  
  /**
   * Verifica se o inimigo possui efeitos em sua lista.
   * 
   * @return {@code true} se a lista de efeitos não estiver vazia,
   *         {@code false} caso contrário
   */
  public boolean temEfeitos() {
    return !listaEfeitos.isEmpty();
  }
  
  /**
   * Retorna a quantidade de efeitos que o inimigo possui.
   * 
   * @return número de efeitos na lista
   */
  public int getQuantidadeEfeitos() {
    return listaEfeitos.size();
  }
  
  /**
   * Retorna um efeito específico da lista pelo índice.
   * 
   * @param indice posição do efeito na lista (base 0)
   * @return o efeito na posição especificada, ou {@code null} se o índice
   *         estiver fora dos limites da lista
   */
  public Efeito retornarEfeito(int indice) {
    if(indice >= 0 && indice < listaEfeitos.size()) {
      return listaEfeitos.get(indice);
    }
    return null;
  }
}