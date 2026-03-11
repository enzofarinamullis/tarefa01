import java.util.Scanner;

public class Main {

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


  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);

    DequeCartas deque = new DequeCartas();
    FilaCartasEscudo cartasEscudo = new FilaCartasEscudo();

    Heroi heroi = new Heroi(null, 200, 100,50, deque, cartasEscudo ); 
    Dados dados = new Dados(heroi);

    Cena cena = new CenaInicial(dados);
    cena.carregaCena();

    System.out.println("Digite o nome do seu heroi:");
    String nome = teclado.nextLine();
    heroi.nome = nome;

    cena.atualizaCena();

    heroi.status();

    CartaDano espada_enferrujada = new CartaDano("Espada enferrujada", 2, 1); 
    CartaDano adaga_de_pedra = new CartaDano("Adaga de pedra", 2, 1);
    CartaEscudo escudo_de_madeira = new CartaEscudo("Escudo de madeira", 3, 3);

    heroi.deque.adicionar_no_inicio(espada_enferrujada);
    heroi.deque.adicionar_no_inicio(adaga_de_pedra);
    heroi.deque.printDoDeck();
    heroi.deque.adicionar_no_inicio(adaga_de_pedra);
    heroi.deque.printDoDeck();
    heroi.cartasEscudo.enfileirar(escudo_de_madeira);

    SistemaTurnos sistemaTurnos = new SistemaTurnos(dados);

    sistemaTurnos.turno();
  }
    
   
  
}
