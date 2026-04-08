import java.util.Scanner;

import anim.dialogos.DialogoInicial;
import anim.dialogos.falas.Dialogo;
import cenas.Cena;
import cenas.Logo;
import cenas.TresSlimes;
import constantes.Cores;
import dados.Dados;
import dados.Heroi;
import musica.MusicaInicial;
import sistematurnos.GameManager;
import usaveis.*;
import mapa.Matriz;
import musica.*;
import anim.*;

public class App {

  public static void main(String[] args) {
    
    //Matriz mapa = new Matriz();
    //mapa.gerarMapa();
    
    MidiPlayer midi = new MusicaInicial("/musica/noname.mid");
    midi.start();
    

    
    Scanner teclado = new Scanner(System.in);

    Heroi heroi = new Heroi();
    Dados dados = new Dados(heroi);

    Cena cena = new Logo(dados);
    cena.renderizaCena();

    System.out.print("Digite o "); 
    Cores.cprint(Cores.ANSI_CYAN, "nome");
    System.out.print(" do seu heroi!\n");
    String nome = teclado.nextLine();
    while(!heroi.setNome(nome)){
      nome = teclado.nextLine();
    }

    cena.atualizaCena();
    
    //Dialogo dialogo = new DialogoInicial(dados);
    //dialogo.rodar();

    //Animacao animacao = new AnimacaoFogo();
    //animacao.run();
    
    heroi.status();

     GameManager gameManager = new GameManager(dados);

    cena = new TresSlimes(dados);

    gameManager.turno();

    /* fechamos o teclado quando terminarmos o programa */
    teclado.close();
  }
    
   
  
}
