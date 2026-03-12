import java.util.Scanner;
import cenas.Cena;
import cenas.CenaInicial;
import cenas.CenaSlime;
import cenas.CenaInicial2;
import constantes.Cores;
import dados.Dados;
import dados.Heroi;
import mapa.Matriz;
import sistematurnos.SistemaTurnos;
import usaveis.DequeCartas;
import usaveis.cartadano.CartaDano;
import usaveis.escudos.CartaEscudo;
import usaveis.escudos.FilaCartasEscudo;

public class Main {

  public static void main(String[] args) {

    Matriz matriz = new Matriz();
    matriz.gerarMatriz();
    matriz.printMapaCompleto();
    System.out.println();
    matriz.visualizarMapa();

    Scanner teclado = new Scanner(System.in);

    DequeCartas deque = new DequeCartas();
    FilaCartasEscudo cartasEscudo = new FilaCartasEscudo();

    Heroi heroi = new Heroi(null, 200, 100,50, deque, cartasEscudo ); 
    Dados dados = new Dados(heroi);

    Cena cena = new CenaInicial2(dados);
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

    cena = new CenaSlime(dados);

    sistemaTurnos.turno();

    /* fechamos o teclado quando terminarmos o programa */
    teclado.close();
  }
    
   
  
}
