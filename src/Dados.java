public class Dados {
  /* Nesta classe de dados, salvaremos todos os dados necessarios */
  /* para uma Cena, ou seja... qntInimigos, e o proprio Heroi que */
  /* estara em todas as cenas */

  // para adicionar os inimigos, podemos trabalhar com uma lista!
  // onde cada inimigo sera um elemento da lista!
  Heroi heroi;
  ListaInimigos listaInimigos;
  
  int frame;

  public Dados(Heroi heroi){
    this.heroi = heroi;
    this.listaInimigos = null;
    this.frame = 0;
  }

  /* sistema de cores */
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
}
