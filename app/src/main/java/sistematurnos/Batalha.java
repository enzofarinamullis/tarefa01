package sistematurnos;
import constantes.Turnos;
import dados.Dados;
import sistematurnos.idsBatalhas.GeradorIds;

/**
 * Classe abstrata que representa uma batalha genérica no jogo.
 *
 * <p>
 *   Cada batalha é composta por um conjunto de inimigos e
 *   utiliza um {@link GameManager} para controlar o fluxo do combate,
 *   incluindo os turnos e as ações das entidades.
 *  <p>
 *    As subclasses de {@code Batalha} devem implementar o método {@link #adicionarInimigos()},
 *    onde devem definir quais inimigos estarão presentes na batalha específica.
 *  </p>
 */
public abstract class Batalha {
  private GameManager gameManager;
  protected Dados dados;
  protected String nome;
  protected int id;
  
  
  /**
   * Construtor da batalha.
   * @param dados objeto que armazena todas as informações relevantes do jogo, como o herói e os inimigos.
   * @param nome nome da batalha, utilizado para identificação e exibição no terminal.
   */
  public Batalha(Dados dados, String nome) {
    this.dados = dados;
    gameManager = new GameManager(dados);
    this.nome = nome;
    this.id = GeradorIds.proximoId();
  }
  
  /**
   * Método abstrato que deve ser implementado por cada batalha
   * específica para adicionar os inimigos necessários.
   *
   * <p>
   *   Deve ser implementado por cada subclasse de {@code Batalha} para definir os inimigos
   *   que estarão presentes na batalha.
   * </p>
   */
  protected abstract void adicionarInimigos();
  
  /**
   * Inicia a excução da batalha.
   * <p>
   *   O fluxo consiste em:
   *   <ul>
   *     <li>Adicionar os inimigos necessários para a batalha</li>
   *     <li>Iniciar o turno utilizando {@link GameManager#turno()}</li>
   *     <li>Analisar o resultado do turno para determinar se o jogador ganhou, perdeu ou fugiu</li>
   *   </ul>
   * </p>
   * @return {@code true} se o jogador ganhou ou fugiu, {@code false} se perdeu.
   */
  public boolean iniciarBatalha(){
    /* Adicionamos os inimigos necessários para cada batalha */
    adicionarInimigos();
    /* Iniciamos o turno */
    int resultado = gameManager.turno();
    /* Analisamos o resultado da batalha */
    if(resultado == Turnos.GANHOU){
      System.out.println("Ganhou");
      return true;
    }
    else if(resultado == Turnos.PERDEU){
      System.out.println("Perdeu");
      return false;
    }
    else{
      System.out.println("Fugiu");
      return true;
    }
  }
  
  /**
   * Retorna uma representação em string da batalha, que inclui seu nome.
   * @return nome formatado da batalha.
   */
  @Override
  public String toString(){
    return " - " + nome;
  }
}
