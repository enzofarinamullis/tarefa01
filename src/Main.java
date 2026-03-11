import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);

    DequeCartas deque = new DequeCartas();
    FilaCartasEscudo cartasEscudo = new FilaCartasEscudo();

    System.out.println("Digite o nome do seu heroi:");
    String nome = teclado.nextLine();

    Heroi heroi = new Heroi(nome, 200, 100,50, deque, cartasEscudo ); 
    Dados dados = new Dados(heroi);
    heroi.status();

    CartaDano espada_enferrujada = new CartaDano("Espada enferrujada", 2, 1); 
    CartaDano adaga_de_pedra = new CartaDano("Adaga de pedra", 2, 1);
    CartaEscudo escudo_de_madeira = new CartaEscudo("Escudo de madeira", 3);

    heroi.deque.adicionar_no_inicio(espada_enferrujada);
    heroi.deque.adicionar_no_inicio(adaga_de_pedra);
    heroi.deque.printDoDeck();
    heroi.deque.adicionar_no_inicio(adaga_de_pedra);
    heroi.deque.printDoDeck();
    heroi.cartasEscudo.enfileirar(carta);
  }
    
   
  
}
