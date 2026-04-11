package sistematurnos;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import constantes.Cabecalho;
import constantes.Cores;
import constantes.Turnos;
import dados.Dados;
import dados.Heroi;
import dados.Inimigo;
import dados.ListaInimigos;
import sistematurnos.observer.Publisher;
import sistematurnos.observer.Subscriber;
import sistematurnos.observer.SubscriberEfeito;
import usaveis.Mao;
import usaveis.cartas.Carta;
import usaveis.cartas.Efeito;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;
import utilitarios.PrintTerminal;

/**
 * Gerenciador principal do sistema de turnos do jogo.
 * 
 * <p>Esta classe é responsável por orquestrar o fluxo de combate entre o herói
 * e os inimigos, gerenciando turnos, ações, sistema de cartas, efeitos,
 * fugas e condições de vitória/derrota.</p>
 * 
 * <p>O GameManager implementa a lógica completa de uma batalha por turnos,
 * incluindo:</p>
 * <ul>
 *   <li>Gerenciamento de turnos do herói e dos inimigos</li>
 *   <li>Sistema de compra e descarte de cartas</li>
 *   <li>Uso de cartas para atacar ou se defender</li>
 *   <li>Sistema de fuga com chance baseada em tentativas</li>
 *   <li>Aplicação de efeitos (cura, envenenamento, sangramento)</li>
 *   <li>Verificação de condições de vitória/derrota</li>
 *   <li>Reset de recursos (escudo, energia) entre turnos</li>
 * </ul>
 * 
 * @see dados.Dados
 * @see sistematurnos.observer.Publisher
 * @see sistematurnos.InterfaceBatalha
 */
public class GameManager {
  
  /**
   * Dados centrais do jogo contendo herói, lista de inimigos e publisher.
   */
  Dados dados;
  
  /**
   * Scanner para leitura de entrada do jogador.
   */
  Scanner teclado;
  
  /**
   * Flag que indica se o herói já realizou sua ação no turno atual.
   */
  boolean heroiAgiu;
  
  /**
   * Flag que indica se os inimigos já realizaram suas ações no turno atual.
   */
  boolean inimigoAgiu;
  
  /**
   * Número de tentativas de fuga realizadas (aumenta a cada falha).
   */
  int tentativasFuga;
  
  /**
   * Gerador de números aleatórios para seleção de inimigos e chances de fuga.
   */
  Random random;
  
  /**
   * Construtor que inicializa o gerenciador com os dados do jogo.
   * 
   * @param dados objeto contendo todas as informações centrais do jogo
   */
  public GameManager(Dados dados) {
    this.dados = dados;
    this.heroiAgiu = false;
    this.inimigoAgiu = false;
    this.teclado = new Scanner(System.in);
    this.random = new Random();
    tentativasFuga = 0;
  }
  
  /**
   * Exibe o menu de ações disponíveis para o herói no turno.
   * 
   * <p>Opções disponíveis:
   * <ul>
   *   <li>1 - Usar Carta</li>
   *   <li>2 - Tentar fugir</li>
   *   <li>0 - Passar turno</li>
   * </ul>
   * </p>
   */
  public void printAcoes() {
    System.out.println();
    PrintTerminal.printLinha(Cores.ANSI_RESET, Cabecalho.TAM_LINHA_DEQUE);
    dados.heroi.status();
    PrintTerminal.printLinha(Cores.ANSI_RESET, Cabecalho.TAM_LINHA_DEQUE);
    System.out.println("Escolha " + Cores.ANSI_BLUE +
      "uma" + Cores.ANSI_RESET + " opção:");
    System.out.println(Cores.ANSI_BLUE + "1 - " + Cores.ANSI_RESET +
      "Usar Carta" + Cores.ANSI_RESET);
    System.out.println(Cores.ANSI_BLUE + "2 - " + Cores.ANSI_RESET + "Tentar " +
      Cores.COR_CIMENTO_3 + "fugir" + Cores.ANSI_RESET);
    System.out.println(Cores.ANSI_BLUE + "0 - " + Cores.ANSI_RESET + "Passar turno");
    
    PrintTerminal.printLinha(Cores.ANSI_RESET, Cabecalho.TAM_LINHA_DEQUE);
  }
  
  /**
   * Verifica se ainda existem inimigos vivos na lista.
   * 
   * @return {@code true} se houver pelo menos um inimigo, {@code false} caso contrário
   */
  private boolean haInimigos() {
    return dados.listaInimigos.getTamanho() != 0;
  }
  
  /**
   * Exibe mensagem de início de combate com a lista de inimigos.
   */
  private void mensagemCombate() {
    if(!haInimigos()) {
      System.out.println("Não há inimigos, por aqui!");
    }
    else {
      System.out.println(Cores.ANSI_YELLOW + "Cuidado!" + Cores.ANSI_RESET +
        " Você entrou em combate com:");
      dados.listaInimigos.printInimigosSemIndice();
    }
  }
  
  /**
   * Seleciona aleatoriamente um inimigo da lista para atacar.
   * 
   * @return inimigo aleatório da lista
   */
  private Inimigo escolheInimigoAleatorio() {
    ListaInimigos listaInimigos = dados.listaInimigos;
    int indiceRand;
    indiceRand = random.nextInt(listaInimigos.getTamanho());
    return listaInimigos.buscarInimigo(indiceRand + 1);
  }
  
  /**
   * Reseta os status do herói no início de cada turno.
   * 
   * <p>O escudo é zerado e a energia é completamente restaurada.</p>
   * 
   * @param heroi herói que terá os status resetados
   */
  private void resetStatus(Heroi heroi) {
    heroi.setaEscudo(0);
    heroi.setaEnergia(heroi.getEnergiaLimite());
  }
  
  /**
   * Embaralha a pilha de compra e compra as cartas iniciais.
   * 
   * <p>Utilizado apenas no primeiro turno da batalha.</p>
   */
  private void embaralhaECompra() {
    Heroi heroi = dados.heroi;
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    
    pilhaCompra.embraralhaPilha();
    pilhaCompra.compraCarta(mao, pilhaDescarte, Turnos.QNT_COMPRAR);
  }
  
  /**
   * Move todas as cartas da mão para o descarte e compra novas cartas.
   * 
   * <p>Utilizado no início de cada turno após o primeiro.</p>
   */
  private void descarteECompra() {
    Heroi heroi = dados.heroi;
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    pilhaDescarte.removeMao(mao);
    pilhaCompra.compraCarta(mao, pilhaDescarte, Turnos.QNT_COMPRAR);
  }
  
  /**
   * Lê e valida o comando digitado pelo jogador.
   * 
   * @return comando válido (0, 1 ou 2)
   */
  private int leComando() {
    int comando = teclado.nextInt();
    if(comando != Turnos.USAR && comando != Turnos.FUGIR && comando != Turnos.PASSAR) {
      comando = teclado.nextInt();
    }
    return comando;
  }
  
  /**
   * Remove todos os inimigos da lista.
   * 
   * @param listaInimigos lista a ser esvaziada
   */
  private void limpaInimigos(ListaInimigos listaInimigos) {
    Inimigo inimigo;
    for(int i = 1; i < listaInimigos.getTamanho() + 1; i++) {
      inimigo = listaInimigos.buscarInimigo(i);
      listaInimigos.removerInimigo(inimigo);
    }
  }
  
  /**
   * Calcula a chance de fuga baseada no número de tentativas.
   * 
   * <p>Chance base de 10% de sucesso. A cada tentativa falha,
   * as chances não aumentam, mas após 3 tentativas é informado
   * que não há mais chance.</p>
   * 
   * @return {@code true} se a fuga foi bem-sucedida, {@code false} caso contrário
   */
  private boolean calculaChangeFuga() {
    if(tentativasFuga > 2) {
      System.out.println("Não há chance alguma de você escapar dessa!");
    }
    if(random.nextInt(100) <= 10) {
      limpaInimigos(dados.listaInimigos);
      System.out.println(Cores.ANSI_PURPLE + " >> Parabéns... você escapou... -_- << " + Cores.ANSI_RESET);
      return true;
    }
    else {
      System.out.println(Cores.ANSI_PURPLE + " >> Dificilmente você escapará dessa << " + Cores.ANSI_RESET);
      tentativasFuga++;
      return false;
    }
  }
  
  /**
   * Exibe as cartas disponíveis na mão do herói.
   */
  private void mostraCartas() {
    Mao mao = dados.heroi.getMao();
    System.out.println("Escolha uma opção:");
    mao.printMao();
    
    System.out.println();
    System.out.println("-1 - Voltar");
  }
  
  /**
   * Lê a escolha de carta do jogador e a remove da mão para o descarte.
   * 
   * @return a carta escolhida, ou {@code null} se o jogador optou por voltar
   */
  public Carta leCarta() {
    int comando;
    Heroi heroi = dados.heroi;
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    Carta carta;
    
    comando = teclado.nextInt();
    
    while(-1 > comando || comando > mao.cartas.size()) {
      System.out.println("Comando inválido");
      comando = teclado.nextInt();
      mao.printMao();
    }
    
    if(comando == -1) {
      return null;
    }
    
    carta = mao.cartas.remove(comando);
    pilhaDescarte.pilha.add(carta);
    return carta;
  }
  
  /**
   * Permite ao jogador escolher um inimigo como alvo.
   * 
   * @return o inimigo escolhido
   */
  private Inimigo escolheInimigo() {
    ListaInimigos listaInimigos = dados.listaInimigos;
    int comando;
    
    System.out.println("Escolha um alvo:\n");
    listaInimigos.mostrarInimigos();
    comando = teclado.nextInt();
    
    while (comando <= 0 || comando > listaInimigos.getTamanho()) {
      System.out.println("Numero inválido, escolha outro:");
      listaInimigos.mostrarInimigos();
      comando = teclado.nextInt();
    }
    
    return listaInimigos.buscarInimigo(comando);
  }
  
  /**
   * Verifica se o inimigo que iria atacar ainda está vivo e escolhe outro se necessário.
   * 
   * @param inimigoAnunciar inimigo que originalmente iria atacar
   * @return inimigo vivo para atacar (pode ser o mesmo ou outro)
   */
  private Inimigo escolheAtacante(Inimigo inimigoAnunciar) {
    int indiceRand;
    ListaInimigos listaInimigos = dados.listaInimigos;
    
    if(!inimigoAnunciar.estaVivoSemPrint()) {
      System.out.println();
      System.out.println("O inimigo que iria te atacar morreu");
      pausa(1000);
      System.out.println("Cuidado, que outro irá atacar:");
      indiceRand = random.nextInt(listaInimigos.getTamanho());
      inimigoAnunciar = listaInimigos.buscarInimigo(indiceRand + 1);
      pausa(1000);
      inimigoAnunciar.anunciar();
    }
    return inimigoAnunciar;
  }
  
  /**
   * Notifica o publisher para ativar efeitos com o ID especificado.
   * 
   * @param idAtivacao ID do momento de ativação dos efeitos
   */
  private void acionaPublisher(int idAtivacao) {
    Publisher publisher = dados.getPublisher();
    SubscriberEfeito finalizou;
    List<SubscriberEfeito> subscribersEfeito;
    
    publisher.notificar(idAtivacao);
    
    subscribersEfeito = publisher.getSubscribersEfeitos();
    
    for(int i = 0; i < subscribersEfeito.size(); i++) {
      finalizou = subscribersEfeito.get(i);
      if(finalizou.acabou()) {
        publisher.desinscrever(finalizou);
      }
    }
    
    pausa(2000);
  }
  
  /**
   * Remove da lista todos os inimigos que estão mortos.
   * 
   * @return {@code true} se não houver mais inimigos vivos, {@code false} caso contrário
   */
  private boolean atualizaInimigosMortos() {
    ListaInimigos listaInimigos = dados.listaInimigos;
    Inimigo inimigo;
    for(int i = 0; i < listaInimigos.getTamanho(); i++) {
      inimigo = listaInimigos.buscarInimigo(i + 1);
      if(!inimigo.estaVivoSemPrint()) {
        listaInimigos.removerInimigo(inimigo);
      }
    }
    return listaInimigos.getTamanho() == 0;
  }
  
  /**
   * Pausa a execução por um determinado número de milissegundos.
   * 
   * @param milissegundos tempo de pausa em milissegundos
   */
  private void pausa(long milissegundos) {
    try {
      Thread.sleep(milissegundos);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Exibe mensagem de vitória ao final da batalha.
   */
  private void mensagemVitoria() {
    pausa(3000);
    PrintTerminal.limparTerminal();
    System.out.println(Cores.ANSI_GREEN + "Parabéns, você venceu a luta!" + Cores.ANSI_RESET);
  }
  
  /**
   * Executa o loop principal de turnos da batalha.
   * 
   * <p>Este método contém a lógica completa do combate, incluindo:
   * <ul>
   *   <li>Inicialização do combate e compra de cartas</li>
   *   <li>Turno do herói (uso de cartas, fuga, passar)</li>
   *   <li>Turno dos inimigos (ataque e aplicação de efeitos)</li>
   *   <li>Verificação contínua de condições de fim de batalha</li>
   * </ul>
   * </p>
   */
  public void turno() {
    int numTurno = 0;
    pausa(5000);
    PrintTerminal.limparTerminal();
    
    Heroi heroi = dados.heroi;
    ListaInimigos listaInimigos = dados.listaInimigos;
    
    embaralhaECompra();
    
    int comando;
    Carta carta;
    Inimigo inimigoAnunciar;
    Inimigo inimigo = null;
    Publisher publisher = dados.getPublisher();
    Subscriber subscriber = null;
    Efeito efeito;
    
    while(true) {
      PrintTerminal.limparTerminal();
      mensagemCombate();
      InterfaceBatalha interfaceBatalha = new InterfaceBatalha(dados);
      interfaceBatalha.imprimeTodosInimigos();
      
      inimigoAnunciar = escolheInimigoAleatorio();
      inimigoAnunciar.anunciar();
      pausa(5000);
      
      resetStatus(heroi);
      
      if(numTurno != 0) {
        descarteECompra();
      }
      
      acionaPublisher(Turnos.INICIO_TURNO_JOAGADOR);
      if(atualizaInimigosMortos()) {
        mensagemVitoria();
        return;
      }
      
      while(!heroiAgiu) {
        if(!heroi.estaVivo()) {
          System.out.println(Cores.ANSI_PURPLE + "Você morreu!" + Cores.ANSI_RESET);
          return;
        }
        
        printAcoes();
        comando = leComando();
        
        if(comando == Turnos.PASSAR) {
          heroiAgiu = true;
          break;
        }
        
        if(comando == Turnos.FUGIR) {
          if(calculaChangeFuga()) { return; }
          else { break; }
        }
        
        if(comando == Turnos.USAR && heroi.verificaEnergia()) {
          mostraCartas();
          carta = leCarta();
          
          if(carta != null) {
            if (carta.isDano()) {
              inimigo = escolheInimigo();
              carta.usar(inimigo, heroi);
              pausa(2000);
              
              if (!inimigo.estaVivoSemPrint()) {
                listaInimigos.removerInimigo(inimigo);
                inimigo = null;
              }
              
              if (listaInimigos.getTamanho() == 0) {
                mensagemVitoria();
                return;
              }
              
              if (heroi.getEnergia() == 0) {
                heroiAgiu = true;
              }
            } else if (carta.isEscudo()) {
              carta.usar(null, heroi);
              pausa(2000);
            }
            
            if(carta.temEfeito()) {
              for (int i = 0; i < carta.quantidadeEfeitos(); i++) {
                efeito = carta.retornarEfeito(i);
                
                if (efeito.ehCura()) {
                  subscriber = new SubscriberEfeito(heroi, efeito, efeito.getIdAtivacao());
                } else if (efeito.ehEnvenamento() || efeito.ehSangramento()) {
                  if(inimigo != null) {
                    subscriber = new SubscriberEfeito(inimigo, efeito, efeito.getIdAtivacao());
                  }
                }
                if(subscriber != null) {
                  publisher.inscrever(subscriber);
                  acionaPublisher(Turnos.INSTANTANEO);
                  if(atualizaInimigosMortos()) {
                    mensagemVitoria();
                    return;
                  }
                }
              }
            }
          }
        }
      }
      
      if(atualizaInimigosMortos()) {
        mensagemVitoria();
        return;
      }
      
      acionaPublisher(Turnos.FINAL_TURNO_JOGADOR);
      
      if(atualizaInimigosMortos()) {
        mensagemVitoria();
        return;
      }
      
      System.out.println();
      System.out.println("------------------------------");
      System.out.println("Turno dos Inimigos");
      
      inimigoAnunciar = escolheAtacante(inimigoAnunciar);
      inimigoAnunciar.atacar(heroi);
      pausa(2000);
      
      if(inimigoAnunciar.temEfeitos()) {
        for(int i = 0; i < inimigoAnunciar.getQuantidadeEfeitos(); i++) {
          efeito = inimigoAnunciar.retornarEfeito(i);
          if(efeito != null) {
            subscriber = new SubscriberEfeito(heroi, efeito, efeito.getIdAtivacao());
            publisher.inscrever(subscriber);
            acionaPublisher(Turnos.INSTANTANEO);
          }
        }
      }
      
      inimigoAgiu = true;
      heroiAgiu = false;
      numTurno++;
    }
  }
}
