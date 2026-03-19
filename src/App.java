import java.util.Scanner;

import anim.dialogos.DialogoInicial;
import anim.dialogos.DialogoPrimeiraBatalha;
import anim.dialogos.falas.Dialogo;
import cenas.Cena;
import cenas.Slime;
import cenas.Logo;
import constantes.Cores;
import dados.Dados;
import dados.Heroi;
import dados.Entidade;
import musica.MusicaInicial;
import sistematurnos.SistemaTurnos;
import usaveis.*;
import mapa.Matriz;
import musica.*;
import anim.*;
public class App {

  public static void main(String[] args) {
    
    Matriz mapa = new Matriz();
    mapa.gerarMapa();
    mapa.printMapaCompleto();
    
    
    MidiPlayer midi = new MusicaInicial("src/musica/noname.mid");
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
    
    Dialogo dialogo = new DialogoInicial(dados);
    dialogo.rodar();

    Animacao animacao = new AnimacaoFogo();
    animacao.run();
    
    heroi.status();

    SistemaTurnos sistemaTurnos = new SistemaTurnos(dados);

    cena = new Slime(dados);

    sistemaTurnos.turno();

    /* fechamos o teclado quando terminarmos o programa */
    teclado.close();
  }
    
   
  
}
