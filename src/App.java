import java.util.Scanner;
import cenas.Cena;
import cenas.Slime;
import cenas.Logo;
import constantes.Cores;
import dados.Dados;
import dados.Heroi;
import sistematurnos.SistemaTurnos;
import usaveis.*;
import mapa.Matriz;

public class App {

  public static void main(String[] args) {
    
    Matriz mapa = new Matriz();
    mapa.gerarMapa();
    mapa.printMapaCompleto();
    
    Scanner teclado = new Scanner(System.in);

    Heroi heroi = new Heroi(null, 5,50); 
    Dados dados = new Dados(heroi);

    Cena cena = new Logo(dados);
    cena.renderizaCena();

    System.out.print("Digite o "); 
    Cores.cprint(Cores.ANSI_CYAN, "nome");
    System.out.print(" do seu heroi!\n");
    String nome = teclado.nextLine();
    heroi.nome = nome;

    cena.atualizaCena();

    heroi.status();

    Cartas espadaEnferrujada = new CartaDano("Espada Enferrujada", 1, 5);
    Cartas espadaTorta = new CartaDano("Espada Torta", 1, 5);
    Cartas escudoMadeira = new CartaEscudo("Escudo de Madeira", 5, 1);
    Cartas escudoFerro = new CartaEscudo("Escudo de Ferro", 10, 3);

    heroi.mao.adicionarCartaMao(espadaEnferrujada);
    heroi.mao.adicionarCartaMao(espadaTorta);
    heroi.mao.adicionarCartaMao(escudoMadeira);
    heroi.mao.adicionarCartaMao(escudoFerro);

    SistemaTurnos sistemaTurnos = new SistemaTurnos(dados);

    cena = new Slime(dados);

    sistemaTurnos.turno();

    /* fechamos o teclado quando terminarmos o programa */
    teclado.close();
  }
    
   
  
}
