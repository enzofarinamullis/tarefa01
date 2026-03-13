import java.util.Scanner;
import cenas.Cena;
import cenas.CenaSlime;
import cenas.CenaInicial2;
import constantes.Cores;
import dados.Dados;
import dados.Heroi;
import sistematurnos.SistemaTurnos;
import usaveis.*;

public class App {

  public static void main(String[] args) {

    Scanner teclado = new Scanner(System.in);


    Heroi heroi = new Heroi(null, 5,50); 
    Dados dados = new Dados(heroi);

    Cena cena = new CenaInicial2(dados);
    cena.carregaCena();

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

    cena = new CenaSlime(dados);

    sistemaTurnos.turno();

    /* fechamos o teclado quando terminarmos o programa */
    teclado.close();
  }
    
   
  
}
