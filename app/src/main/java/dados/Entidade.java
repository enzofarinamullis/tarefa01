package dados;

import constantes.Cores;

/**
 * Classe abstrata base para todas as entidades do jogo.
 *
 * <p>
 * Define atributos e comportamentos comuns como vida, dano, escudo e energia.
 * Serve como superclasse para os inimigos e o personagem jogável (Herói).
 * </p>
 * 
 * <p>
 * Fornecendo métodos importantes para:<br>
 * - Gerenciar ataques entre as entidades;<br>
 * - Verificar o estado da entidade (vivo/morto);<br>
 * - Gerenciar o recebimento de dano, vida e escudo;<br>
 * - Gerenciar atributos como energia e escudo;<br>
 * </p>
 * 
 * <p>
 * Observações:
 * - O dano é absorvido primeiramente pelo escudo e somente aplicado à vida,
 * caso não se haja mais escudo para absorver o dano.
 * </p>
 * 
 * <p>
 * Exemplo de uso:
 * <pre>
 * Entidade slime = new Slime();
 * slime.receberDano(10);
 * </pre>
 * </p>
 * @see Heroi
 * @see constantes.Cores
 */
public abstract class Entidade {
  
  /**
   * Pontos de vida atuais da entidade.
   * Quando chega a 0 ou menos, a entidade morre.
   */
  protected int vida;
  
  /**
   * Quantidade de dano que a entidade causa em ataques.
   */
  protected int dano;
  
  /**
   * Pontos de escudo atuais da entidade.
   * Absorvem dano antes da vida.
   */
  protected int escudo;
  
  /**
   * Quantidade atual de energia da entidade.
   * Pode ser usada para habilidades especiais.
   */
  protected int energia;
  
  /**
   * Limite máximo de energia que a entidade pode ter.
   */
  protected int energiaLimite;
  
  /**
   * Identificador único da entidade.
   */
  protected int id;
  
  /**
   * Nome da entidade.
   */
  private String nome;
  
  /**
   * Faz a entidade receber dano, aplicando primeiro ao escudo e depois à vida.
   * 
   * <p>O dano é absorvido pelo escudo primeiro. Se o escudo for insuficiente,
   * o dano restante é aplicado diretamente à vida da entidade.</p>
   * 
   * <p>O método exibe mensagens coloridas informando:
   * <ul>
   *   <li>Quanto dano foi absorvido pelo escudo (em azul/vermelho)</li>
   *   <li>Quanto dano foi recebido pela vida (em vermelho)</li>
   * </ul>
   * </p>
   * 
   * @param dano quantidade de dano a ser aplicada à entidade
   * @see constantes.Cores
   */
  public void receberDano(int dano) {
    if (dano >= 0) {
      if(escudo > 0) {
        int dano_no_escudo = Math.min(escudo, dano);
        escudo -= dano_no_escudo;
        dano -= dano_no_escudo;
        System.out.println("O " + Cores.ANSI_BLUE + "escudo" + Cores.ANSI_RESET + 
          " absorveu " + Cores.ANSI_RED + dano_no_escudo +
          Cores.ANSI_RESET + " de " + Cores.ANSI_RED + "dano" + Cores.ANSI_RESET);
      }
      
      System.out.println(Cores.ANSI_CYAN + nome + Cores.ANSI_RESET + " recebeu " +
        Cores.ANSI_RED + dano + Cores.ANSI_RESET + " de dano.");
      vida -= dano;
    }
  }
  
  /**
   * Verifica se a entidade está viva sem exibir mensagens no console.
   * 
   * @return {@code true} se a vida for maior que 0, {@code false} caso contrário
   */
  public Boolean estaVivoSemPrint() {
    return vida > 0;
  }
  
  /**
   * Verifica se a entidade está viva e exibe uma mensagem colorida no console.
   * 
   * <p>Se estiver viva, exibe mensagem em verde.
   * Se estiver morta, exibe mensagem em vermelho.</p>
   * 
   * @return {@code true} se a vida for maior que 0, {@code false} caso contrário
   * @see constantes.Cores
   */
  public Boolean estaVivo() {
    if (vida > 0) {
      System.out.println();
      System.out.println(Cores.ANSI_CYAN + nome + Cores.ANSI_RESET +
        Cores.ANSI_GREEN + " está vivo!" + Cores.ANSI_RESET);
      return true;
    }
    else {
      System.out.println();
      System.out.println(Cores.ANSI_CYAN + nome + Cores.ANSI_RESET +
        Cores.ANSI_RED + " não está vivo!" + Cores.ANSI_RESET);
      return false;
    }
  }
  
  /**
   * Adiciona um bônus de vida à entidade.
   * 
   * @param bonusDeVida quantidade de vida a ser adicionada (valor positivo)
   */
  public void ganharVida(int bonusDeVida) {
    System.out.println(nome + " ganhou " + Cores.ANSI_GREEN + bonusDeVida + 
      Cores.ANSI_RESET + " de vida.");
    vida += bonusDeVida;
  }
  
  /**
   * Realiza um ataque contra um herói.
   * 
   * <p>Exibe mensagens coloridas informando quem está atacando e a força do ataque.
   * Se o dano for positivo, aplica o dano ao herói.</p>
   * 
   * @param heroi o herói que será atacado
   * @see Heroi#receberDano(int)
   * @see constantes.Cores
   */
  public void atacar(Heroi heroi) {
    System.out.println(Cores.ANSI_YELLOW + nome + Cores.ANSI_RESET +
      " ataca " + Cores.ANSI_CYAN + heroi.getNome() + Cores.ANSI_RESET + "!");
    System.out.println("Força do ataque: " + Cores.ANSI_RED + dano + Cores.ANSI_RESET);
    if (dano > 0) {
      heroi.receberDano(dano);
    }
  }
  
  /**
   * Exibe as estatísticas atuais da entidade no console.
   * 
   * <p>Mostra:
   * <ul>
   *   <li>Nome (amarelo)</li>
   *   <li>Vida (verde)</li>
   *   <li>Dano (vermelho)</li>
   *   <li>Escudo (azul)</li>
   * </ul>
   * </p>
   * 
   * @see constantes.Cores
   */
  public void printStats() {
    System.out.print(Cores.ANSI_YELLOW + nome + Cores.ANSI_RESET +
      " Vida: " + Cores.ANSI_GREEN + vida + Cores.ANSI_RESET +
      " Dano: " + Cores.ANSI_RED + dano + Cores.ANSI_RESET +
      " Escudo " + Cores.ANSI_BLUE + escudo + Cores.ANSI_RESET +
      "\n");
  }
  
  /**
   * Adiciona um bônus de escudo à entidade.
   * 
   * @param bonusDeEscudo quantidade de escudo a ser adicionada
   */
  public void ganharEscudo(int bonusDeEscudo) {
    System.out.println("O " + Cores.ANSI_CYAN + nome + Cores.ANSI_RESET + " ganhou " +
      Cores.ANSI_BLUE + bonusDeEscudo + Cores.ANSI_RESET + " de escudo.");
    escudo += bonusDeEscudo;
  }
  
  /**
   * Define o valor do escudo da entidade.
   * Se o valor fornecido for negativo, o método retorna sem fazer alterações.
   * 
   * @param escudo novo valor de escudo (deve ser maior ou igual a 0)
   */
  public void setaEscudo(int escudo) {
    if(escudo < 0) {
      return;
    }
    this.escudo = escudo;
  }
  
  /**
   * Define o valor da energia da entidade.
   * 
   * @param energia novo valor de energia
   */
  public void setaEnergia(int energia) {
    this.energia = energia;
  }
  
  /**
   * Retorna a quantidade atual de energia da entidade.
   * 
   * @return valor atual da energia
   */
  public int getEnergia() {
    return energia;
  }
  
  /**
   * Retorna a quantidade atual de vida da entidade.
   * 
   * @return valor atual da vida
   */
  public int getVida() {
    if(vida < 0){
      vida = 0;
    }
    return vida;
  }

  /**
   * Retorna o limite máximo de energia da entidade.
   * 
   * @return valor do limite de energia
   */
  public int getEnergiaLimite() {
    return energiaLimite;
  }
  
  /**
   * Retorna o nome da entidade.
   * 
   * @return nome da entidade
   */
  public String getNome() {
    return nome;
  }
  
  /**
   * Define o nome da entidade.
   * 
   * @param nome novo nome para a entidade
   * @return {@code true} sempre (indicando sucesso na operação)
   */
  public boolean setNome(String nome) {
    this.nome = nome;
    return true;
  }

  /**
   * Retorna a quantidade atual de escudo da entidade.
   * 
   * @return valor atual do escudo
   */
  public int getEscudo() {
    return escudo;
  }
  public void setVida(int vida) {
    if (vida >= 0) {
      this.vida = vida;
    }
  }
}