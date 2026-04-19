import java.util.Scanner;

import cenas.Cena;
import cenas.DoisSlimesELesma;
import cenas.Logo;
import constantes.Cores;
import dados.Dados;
import dados.Heroi;
import musica.MidiPlayer;
import musica.MusicaInicial;
import sistematurnos.GameManager;
import sistematurnos.Mapa;

/**
 * Classe principal responsável por iniciar e executar o jogo.
 *
 * <p>
 * Realiza a configuração inicial do sistema, incluindo:<br>
 * - Inicialização da música<br>
 * - Criação do herói e dos dados iniciais<br>
 * - Execução da cena inicial ({@link Logo})<br>
 * - Reprodução de diálogos e animações
 * </p>
 *
 * <p>
 * O fluxo principal da aplicação consiste em:
 * <ol>
 *   <li>Iniciar a música de fundo</li>
 *   <li>Criar o herói e os dados do jogo</li>
 *   <li>Exibir o logo do jogo</li>
 *   <li>Solicitar o nome do herói ao jogador</li>
 *   <li>Inicializar o gerenciador de turnos</li>
 *   <li>Carregar a primeira cena de batalha</li>
 *   <li>Executar o loop de combate</li>
 *   <li>Finalizar os recursos ao encerrar</li>
 * </ol>
 * </p>
 * @see dados.Dados
 * @see dados.Heroi
 * @see sistematurnos.GameManager
 * @see cenas.Logo
 * @see cenas.DoisSlimesELesma
 */
public class App {
  
  /**
   * Método principal que inicia a execução do jogo.
   * 
   * <p>Este método configura todos os componentes necessários,
   * executa o fluxo principal do jogo e gerencia o ciclo de vida
   * dos recursos (como música e scanner de teclado).</p>
   * 
   * @param args argumentos da linha de comando (não utilizados)
   */
  public static void main(String[] args) {
    
    // Inicializa e inicia a música de fundo do jogo
    MidiPlayer midi = new MusicaInicial("/musica/noname.mid");
    midi.start();
    
    // Scanner para leitura da entrada do usuário
    Scanner teclado = new Scanner(System.in);

    // Cria o herói e os dados centrais do jogo
    Heroi heroi = new Heroi();
    Dados dados = new Dados(heroi);

    // Exibe o logo do jogo
    Cena cena = new Logo(dados);
    cena.renderizaCena();

    // Solicita e valida o nome do herói
    System.out.print("Digite o "); 
    Cores.cprint(Cores.ANSI_CYAN, "nome");
    System.out.print(" do seu heroi!\n");
    String nome = teclado.nextLine();
    while(!heroi.setNome(nome)) {
      nome = teclado.nextLine();
    }
    
    // Atualiza a cena após definir o nome
    cena.atualizaCena();
    
    // Código comentado para futura implementação de diálogos e animações
    // Dialogo dialogo = new DialogoInicial(dados);
    // dialogo.rodar();
    // Animacao animacao = new AnimacaoFogo();
    // animacao.run();
    
    // Exibe o status inicial do herói
    heroi.status();
    
    /* Testes de Mapa */
    Mapa mapa = new Mapa(dados);
    
    // Cria o gerenciador de turnos com os dados do jogo
    GameManager gameManager = new GameManager(dados);

    // Carrega a primeira cena de batalha (Dois Slimes e uma Lesma)
    cena = new DoisSlimesELesma(dados);

    // Inicia o loop principal de combate
    gameManager.turno();

    // Libera os recursos do scanner ao finalizar o programa
    teclado.close();
  }
}