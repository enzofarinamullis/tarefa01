package sistematurnos;

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
import sistematurnos.InterfaceBatalha;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Gerencia o fluxo do jogo, incluindo turnos, ações do jogador e inimigos, e a aplicação de efeitos.
 * <p>
 *   Responsabilidades:<br>
 *   - Controlar a sequência de turnos entre o herói e os inimigos<br
 *   - Gerenciar as ações disponíveis para o jogador e processar suas escolhas<br>
 *   - Determinar o comportamento dos inimigos durante seus turnos<br>
 *   - Aplicar os efeitos de cartas e habilidades, interagindo com o sistema de
 *   eventos ({@link Publisher} e {@link Subscriber})<br>
 *   - Verificar condições de vitória e derrota<br>
 * </p>
 * <p>
 *   O {@link GameManager} é o núcleo do sistema de turnos, coordenando
 *   todas as interações entre o jogador, os inimigos e os efeitos durante o combate.
 *   </p>
 *   <p>
 *     Exemplo de uso:<br>
 *     Dados dados = new Dados(new Heroi());<br>
 *     GameManager gameManager = new GameManager(dados);<br>
 *     gameManager.turno();<br>
 *     </p>
 */
public class GameManager {
  Dados dados;
  Scanner teclado;
  boolean heroiAgiu;
  boolean inimigoAgiu;
  int tentativasFuga;
  Random random;
  
  public GameManager(Dados dados){
    this.dados = dados;
    this.heroiAgiu = false;
    this.inimigoAgiu = false;
    this.teclado = new Scanner(System.in);
    this.random = new Random();
    tentativasFuga = 0;
  }
  
  /**
   * Exibe as opções de ação disponíveis para o jogador durante seu turno.
   * <p>
   *   As opções incluem usar uma carta, tentar fugir ou passar o turno.
   *   Também exibe o status atual do herói para auxiliar na tomada de decisão.
   *   </p>
   *   <p>
   *     Esta função é chamada no início do turno do jogador para apresentar as
   *     ações possíveis e o estado atual do herói.
import dados.Inimigo;
import dados.ListaInimigos;
   *     </p>
   *
   *   Exemplo de uso:<br>
   *   gameManager.printAcoes();<br>
   *     // Exibe as opções de ação para o jogador<br>
   *     </p>
   */
  public void printAcoes(){
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
   * Verifica se ainda existem inimigos vivos na lista de inimigos.
   * @return true se houverem inimigos vivos, false caso contrário
   */
  private boolean haInimigos(){
    return dados.listaInimigos.getTamanho() != 0;
  }
  
  /**
   * Exibe uma mensagem de combate indicando os inimigos presentes na cena.
   * <p>
   *   Se não houverem inimigos, exibe uma mensagem informando que a área
   *   está livre de inimigos. Caso contrário, exibe uma mensagem de alerta e
   *   lista os inimigos presentes para o jogador.
   *   </p>
   *   <p>
   *     Esta função é chamada no início do combate para informar o jogador sobre
   *     os inimigos que ele enfrentará.
   *   </p>
   *   Exemplo de uso:<br>
   *   gameManager.mensagemCombate();<br>
   *   // Exibe a mensagem de combate e os inimigos presentes<br>
   */
  private void mensagemCombate(){
    if(!haInimigos()){
      System.out.println("Não há inimigos, por aqui!");
    }
    else {
      System.out.println(Cores.ANSI_YELLOW + "Cuidado!" + Cores.ANSI_RESET +
        " Você entrou em combate com:");
      dados.listaInimigos.printInimigosSemIndice();
    }
  }
  
  /**
   * Seleciona um inimigo aleatório da lista de inimigos para atacar o jogador.
   * <p>
   *   Esta função é chamada no início do turno dos inimigos para determinar qual
   *   inimigo irá atacar o jogador. O inimigo selecionado é anunciado para o
   *   jogador antes de realizar seu ataque.
   *   </p>
   *
   * @return o inimigo selecionado para atacar o jogador
   */
  private Inimigo escolheInimigoAleatorio(){
    ListaInimigos listaInimigos = dados.listaInimigos;
    int indiceRand;
    indiceRand = random.nextInt(listaInimigos.getTamanho());
    /* inimigo que for atacar estar na posicao indiceRand */
    return listaInimigos.buscarInimigo(indiceRand + 1);
  }
  
  /**
   * Reseta o status do herói no início de cada turno, ajustando o valor do escudo
   * para 0 e restaurando a energia para o limite máximo.
   * <p>
   *   Esta função é chamada no início do turno do jogador para garantir que o
   *   herói comece o turno com o escudo zerado e a energia completa.
   *   </p>
   *
   *   Exemplo de uso:<br>
   *   gameManager.resetStatus(dados.heroi);<br>
   *   // Reseta o status do herói para o início do turno<br>
   *
   * @param heroi o herói cujo status será resetado
   */
  private void resetStatus(Heroi heroi){
    /* resetamos o valor de escudo como pedido no enunciado */
    heroi.setaEscudo(0);
    /* completamos a energia do heroi como pedido no enunciado */
    heroi.setaEnergia(heroi.getEnergiaLimite());
  }
  
  /**
   * Embaralha a pilha de compra do herói e compra 5 cartas para a mão.
   * <p>
   *   Esta função é chamada no início do combate para preparar a mão do jogador
   *   para o primeiro turno. Ela garante que o jogador comece o combate com uma
   *   mão completa de cartas, embaralhando o baralho para garantir aleatoriedade
   *   na compra.
   *   </p>
   *   Exemplo de uso:<br>
   *   gameManager.embaralhaECompra();<br>
   *   // Embaralha o baralho e compra 5 cartas para a mão
   */
  private void embaralhaECompra(){
    Heroi heroi = dados.heroi;
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    
    /* embaralhamos a pilha de compra do heroi do heroi */
    pilhaCompra.embraralhaPilha();
    /* compramos 5 cartas */
    pilhaCompra.compraCarta(mao, pilhaDescarte, Turnos.QNT_COMPRAR);
  }
  
  /**
   * Descarta as cartas da mão do herói e compra novas cartas da pilha de compra.
   * <p>
   *   Esta função é chamada no início de cada turno do jogador (exceto o primeiro)
   *   para renovar a mão do jogador. As cartas atualmente na mão são movidas para
   *   a pilha de descarte, e novas cartas são compradas da pilha
   *   de compra para a mão do jogador, garantindo que o jogador tenha novas opções de
   *   cartas a cada turno.
   *   </p>
   *   Exemplo de uso:<br>
   *   gameManager.descarteECompra();<br>
   *   // Descarta as cartas da mão e compra novas cartas para o jogador<br>
   */
  private void descarteECompra(){
    Heroi heroi = dados.heroi;
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    pilhaDescarte.removeMao(mao);
    pilhaCompra.compraCarta(mao, pilhaDescarte, Turnos.QNT_COMPRAR);
  }
  
  /**
   * Lê o comando do jogador para escolher uma ação durante seu turno.
   * <p>
   *   O jogador pode escolher entre usar uma carta, tentar fugir ou passar o turno.
   *   Esta função valida a entrada do jogador para garantir que um comando válido seja
   *   selecionado. Se o comando for inválido, a função continuará solicitando uma
   *   entrada até que um comando válido seja fornecido.
   *   </p>
   *   Exemplo de uso:<br>
   *   int comando = gameManager.leComando();<br>
   *   // Lê o comando do jogador para escolher uma ação<br>
   * @return o comando escolhido pelo jogador, representado por um inteiro correspondente às opções disponíveis
   */
  private int leComando(){
    int comando = teclado.nextInt(); // lemos o comando
    // verificamos se o comando eh valido
    if(comando != Turnos.USAR && comando != Turnos.FUGIR && comando != Turnos.PASSAR){
      comando = teclado.nextInt();
    }
    return comando;
  }
  
  /**
   * Limpa a lista de inimigos, removendo todos os inimigos presentes.
   * <p>
   *   Esta função é chamada quando o jogador consegue fugir do combate, garantindo
   *   que a lista de inimigos seja esvaziada e o jogador possa continuar
   *   jogando sem a presença dos inimigos anteriores. Ela percorre a lista de inimigos
   *   e remove cada inimigo individualmente, garantindo que a lista esteja completamente
   *   limpa após a fuga bem-sucedida.
   *   </p>
   *   Exemplo de uso:<br>
   *   gameManager.limpaInimigos(dados.listaInimigos);<br>
   *   // Limpa a lista de inimigos após uma fuga bem-sucedida<br>
    * @see #calculaChangeFuga()
   * @param listaInimigos a lista de inimigos a ser limpa.
   */
  private void limpaInimigos(ListaInimigos listaInimigos){
    Inimigo inimigo;
    for(int i = 1; i < listaInimigos.getTamanho() + 1; i++){
      inimigo = listaInimigos.buscarInimigo(i);
      listaInimigos.removerInimigo(inimigo);
    }
  }
  
  /**
   * Calcula a chance de fuga do jogador durante o combate.
   * <p>
   *   O jogador tem uma chance de 10% de escapar do combate a cada tentativa
   *   de fuga. Se o jogador tentar fugir mais de 2 vezes, uma mensagem de alerta é exibida
   *   indicando que as chances de fuga estão diminuindo.
   *   Se a fuga for bem-sucedida, a lista de inimigos é limpa e uma mensagem de sucesso é exibida.
   *   Caso contrário, uma mensagem de dificuldade é exibida e o número de tentativas de fuga é incrementado.
   *   </p>
   *   Exemplo de uso:<br>
   *   boolean fugaBemSucedida = gameManager.calculaChangeFuga();<br>
   *   //   if (fugaBemSucedida) {<br>
   *   //     // A fuga foi bem-sucedida, o jogador escapou do combate<br>
   * @return true se a fuga foi bem-sucedida, false caso contrário
   */
  private boolean calculaChangeFuga(){
    if(tentativasFuga > 2){
      System.out.println("Não há chance alguma de você escapar dessa!");
    }
    if(random.nextInt(100) <= 10){
      limpaInimigos(dados.listaInimigos);
      System.out.println(Cores.ANSI_PURPLE + " >> Parabéns... você escapou... -_- << " + Cores.ANSI_RESET);
      return true;
    }
    else{
      System.out.println(Cores.ANSI_PURPLE + " >> Dificilmente você escapará dessa << " + Cores.ANSI_RESET);
      tentativasFuga++;
      return false;
    }
  }
  
  /**
   * Exibe as cartas disponíveis na mão do jogador para uso durante seu turno.
   * <p>
   *   Esta função é chamada quando o jogador escolhe a opção de usar uma carta durante
   *   seu turno. Ela exibe as cartas atualmente na mão do jogador,
   *   permitindo que ele escolha qual carta deseja usar.
   *   Também oferece a opção de voltar ao menu anterior caso o jogador decida
   *   não usar uma carta.
   *
   *   Exemplo de uso:<br>
   *   gameManager.mostraCartas();<br>
   *   // Exibe as cartas disponíveis na mão do jogador<br>
   * @see #leCarta()
   */
  private void mostraCartas(){
    Mao mao = dados.heroi.getMao();
    System.out.println("Escolha uma opção:");
    mao.printMao();
    
    System.out.println();
    System.out.println("-1 - Voltar");
  }
  
  /**
   * Lê a carta escolhida pelo jogador para usar durante seu turno.
   * <p>
   *   O jogador pode escolher uma carta da mão para usar contra
   *   um inimigo ou para se beneficiar de um efeito.
   *   Esta função valida a entrada do jogador para garantir
   *   que uma carta válida seja selecionada.
   *   Se o jogador escolher voltar ao menu anterior, a função retornará null,
   *   indicando que nenhuma carta foi escolhida.
   * </p>
   * <p>
   *   Exemplo de uso:<br>
   *   Carta cartaEscolhida = gameManager.leCarta();<br>
   *   if (cartaEscolhida != null) {<br>
   *   // O jogador escolheu uma carta para usar<br>
   *   } else {<br>
   *   // O jogador optou por voltar ao menu anterior, nenhuma carta foi escolhida<br>
   *   }
   *
   * @return a carta escolhida pelo jogador para usar,
   * ou null se o jogador optar por voltar ao menu anterior
   * @see #mostraCartas()
   */
  public Carta leCarta(){
    int comando;
    Heroi heroi = dados.heroi;
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    Carta carta;
    
    comando = teclado.nextInt(); // lemos o numero da carta que queremos usar
    
    /* tentamos puxar a carta */
    while(-1 > comando  || comando > mao.cartas.size()){
      System.out.println("Comando inválido");
      comando = teclado.nextInt();
      mao.printMao();
    }
    
    if(comando == -1){
      return null;
    }
    
    /* removemos a carta da mao e colocamos em descarte */
    carta = mao.cartas.remove(comando);
    pilhaDescarte.pilha.add(carta);
    return carta;
  }
  
  /**
   * Permite ao jogador escolher um inimigo específico
   * para atacar usando uma carta de dano.
   *
   * <p>
   *   Esta função é chamada quando o jogador escolhe usar uma carta de dano durante
   *   seu turno. Ela exibe a lista de inimigos disponíveis e solicita que o jogador
   *   escolha um inimigo para atacar. A função valida
   *   a entrada do jogador para garantir que um inimigo válido seja selecionado.
   *   Se o jogador escolher um número inválido, a função continuará solicitando uma
   *   entrada até que um inimigo válido seja escolhido.
   *
   *   Exemplo de uso:<br>
   *   Inimigo inimigoEscolhido = gameManager.escolheInimigo();<br>
   *   // O jogador escolheu um inimigo para atacar<br>
   *
   * @return o inimigo escolhido pelo jogador
   */
  private Inimigo escolheInimigo(){
    ListaInimigos listaInimigos = dados.listaInimigos;
    int comando;
    
    System.out.println("Escolha um alvo:\n");
    listaInimigos.mostrarInimigos();
    comando = teclado.nextInt();
    
    /* caso o numero nao tenha sido aprovado lemos o numero denovo*/
    while (comando <= 0 || comando > listaInimigos.getTamanho()) {
      System.out.println("Numero inválido, escolha outro:");
      listaInimigos.mostrarInimigos();
      comando = teclado.nextInt();
    }
    
    /* buscamos o inimigo */
    return listaInimigos.buscarInimigo(comando);
  }
  
  /**
   * Verifica se o inimigo que iria atacar o jogador está vivo.
   * Se o inimigo estiver morto, a função seleciona um novo inimigo
   * aleatório para atacar o jogador e anuncia esse novo inimigo.
   * <p>
   *   Esta função é chamada no início do turno dos inimigos
   *   para garantir que o inimigo
   *   que irá atacar o jogador esteja vivo. Se o inimigo selecionado inicialmente
   *   estiver morto, a função seleciona um novo inimigo aleatório da lista de inimigos
   *   e o anuncia para o jogador, garantindo que o combate continue
   *   mesmo quando um inimigo morre antes de realizar seu ataque.
   *
   * @param inimigoAnunciar o inimigo inicialmente selecionado para atacar o jogador,
   *                        que será verificado para determinar se está vivo ou morto
   * @return o inimigo que irá atacar o jogador, garantindo que seja um inimigo vivo
   */
  private Inimigo escolheAtacante(Inimigo inimigoAnunciar){
    int indiceRand;
    ListaInimigos listaInimigos = dados.listaInimigos;
    /* verificamos se o inimigo que iria atacar morreu */
    if(!inimigoAnunciar.estaVivoSemPrint()) {
      System.out.println();
      System.out.println("O inimigo que iria te atacar morreu");
      pausa(1000);
      System.out.println("Cuidado, que outro irá atacar:");
      /* caso nao esteja vivo */
      indiceRand = random.nextInt(listaInimigos.getTamanho());
      /* inimigo que for atacar estar na posicao indiceRand */
      inimigoAnunciar = listaInimigos.buscarInimigo(indiceRand + 1);
      pausa(1000);
      inimigoAnunciar.anunciar();
    }
    return inimigoAnunciar;
  }
  
  private void acionaPublisher(int idAtivacao){
    Publisher publisher = dados.getPublisher();
    SubscriberEfeito finalizou;
    List<SubscriberEfeito> subscribersEfeito;
    
    publisher.notificar(idAtivacao);
    
    subscribersEfeito = publisher.getSubscribersEfeitos();
    
    /* verificamos se algum efeito acabou */
    for(int i = 0; i < subscribersEfeito.size(); i++){
      finalizou = subscribersEfeito.get(i);
      if(finalizou.acabou()){
        publisher.desinscrever(finalizou);
      }
    }
    
    pausa(2000);
    
  }
  
  
  /**
   * Atualiza a lista de inimigos removendo aqueles que estão mortos.
   * <p>
   *   Esta função é chamada após qualquer ação do jogador que possa
   *   resultar na morte de um inimigo, como o uso de uma carta de dano.
   *   Ela percorre a lista de inimigos e remove aqueles que estão mortos,
   *   garantindo que a lista apresente apenas os inimigos vivos.
   *   Se todos os inimigos forem removidos, a função retorna true,
   *   indicando que o jogador venceu o combate.
   *
   * @return true se todos os inimigos foram removidos (vencendo o combate),
   * false caso contrário
   */
  private boolean atualizaInimigosMortos(){
    ListaInimigos listaInimigos = dados.listaInimigos;
    Inimigo inimigo;
    for(int i = 0; i < listaInimigos.getTamanho(); i++){
      inimigo = listaInimigos.buscarInimigo(i + 1);
      if(!inimigo.estaVivoSemPrint()){
        /* se morreu */
        listaInimigos.removerInimigo(inimigo);
      }
    }
    return listaInimigos.getTamanho() == 0;
  }
  
  /**
   * Faz uma pausa na execução do jogo por um determinado número de milissegundos.
   * @param milissegundos o número de milissegundos para pausar a execução do jogo
   */
  private void pausa(long milissegundos){
    try {
      Thread.sleep(milissegundos);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  private void mensagemVitoria(){
    pausa(3000);
    PrintTerminal.limparTerminal();
    System.out.println(Cores.ANSI_GREEN + "Parabéns, você venceu a luta!" + Cores.ANSI_RESET);
  }
  
  private void tranfereMaoCompra(){
    Mao mao = dados.heroi.getMao();
    PilhaCompra pilhaCompra = dados.heroi.getPilhaCompra();
    
    while(!mao.cartas.isEmpty()){
      pilhaCompra.pilha.add(mao.cartas.removeFirst());
    }
  }
  
  private void tranfereDescarteCompra(){
    PilhaCompra pilhaCompra = dados.heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = dados.heroi.getPilhaDescarte();
    
    while(!pilhaDescarte.pilha.isEmpty()){
      pilhaCompra.pilha.add(pilhaDescarte.pilha.removeFirst());
    }
  }
  
  private void iniciaMao(){
    Mao mao = dados.heroi.getMao();
    PilhaDescarte pilhaDescarte = dados.heroi.getPilhaDescarte();
    PilhaCompra pilhaCompra = dados.heroi.getPilhaCompra();
    
    tranfereMaoCompra();
    tranfereDescarteCompra();
  }
  
  private void limpaBatalha(Publisher publisher){
    int numero = 1;
    dados.listaInimigos.limparListaInimigos();
    if(publisher != null) {
      publisher.limparPublisher();
    }
  }
  
  /**
   * Gerencia o fluxo principal do combate, alternando entre os turnos do jogador
   * e dos inimigos, e aplicando as ações e efeitos conforme as escolhas do jogador
   * e o comportamento dos inimigos.
   *
   * <p>
   *   O método inicia o combate exibindo os inimigos presentes e embaralhando o
   *   baralho do jogador para comprar as cartas iniciais. Em seguida, entra em
   *   um loop principal onde o jogador realiza suas ações, como usar cartas ou
   *   tentar fugir, enquanto os inimigos atacam o jogador em seus turnos.
   *   O método também gerencia a aplicação de efeitos e a atualização do estado do combate,
   *   verificando condições de vitória ou derrota a cada etapa do processo.
   *   <p>
   *     Exemplo de uso:<br>
   *     gameManager.turno();<br>
   *     // Inicia o fluxo principal do combate<br>
   */
  public int turno(){
    int numTurno = 0;
    pausa(5000);
    PrintTerminal.limparTerminal();
    
    
    /* para facilitar a leitura */
    Heroi heroi = dados.heroi;
    ListaInimigos listaInimigos = dados.listaInimigos;
    /* Como o jogo funciona com progressão */
    /* precisamos garantir que a cada estágio o jogador */
    /* não acumule cartas e possa sempre usar novas */
    iniciaMao();
    embaralhaECompra();
    
    int comando;
    Carta carta;
    Inimigo inimigoAnunciar;
    Inimigo inimigo = null;
    Publisher publisher = dados.getPublisher();
    Subscriber subscriber = null;
    Efeito efeito;
    
    
    while(true){
      PrintTerminal.limparTerminal();
      mensagemCombate();
      InterfaceBatalha interfaceBatalha = new InterfaceBatalha(dados);
      interfaceBatalha.imprimeTodosInimigos();
      /* escolhemos o inimigo que ira atacar */
      inimigoAnunciar = escolheInimigoAleatorio();
      inimigoAnunciar.anunciar(); // fazemos o seu anuncio
      pausa(5000);
      
      resetStatus(heroi);
      
      if(numTurno != 0) {
        descarteECompra();
      }
      
      /* turno do heroi */
      acionaPublisher(Turnos.INICIO_TURNO_JOAGADOR); // notificamos os efeitos do inicio do combate
      if(atualizaInimigosMortos()){
        mensagemVitoria();
        limpaBatalha(publisher);
        return Turnos.GANHOU;
      }
      
      while(!heroiAgiu){
        
        if(!heroi.estaVivo()){
          System.out.println(Cores.ANSI_PURPLE + "Você morreu!" + Cores.ANSI_RESET);
          return Turnos.PERDEU;
        }
        
        /* Vemos qual acao o heroi quer tomar */
        printAcoes();
        comando = leComando();
        
        if(comando == Turnos.PASSAR){
          heroiAgiu = true;
          break;
        }
        
        if(comando == Turnos.FUGIR){
          
          if(calculaChangeFuga()){
            limpaBatalha(publisher);
            return Turnos.FUGIU;
          }
          else{
            break;
          }
        }
        
        /* Caso a escolha seja USAR, verificamos se ha energia suficiente */
        if(comando == Turnos.USAR && heroi.verificaEnergia()){
          mostraCartas();
          carta = leCarta(); // caso o jogador deseja retornar ao menu anterior
                             // a carta lida sera null
          
          if(carta != null){
            if (carta.isDano()) {
              /* escolhemos o inimigo que queremos atacar */
              inimigo = escolheInimigo();
              carta.usar(inimigo, heroi);
              pausa(2000);
              
              /* verificamos se o inimigo morreu */
              if (!inimigo.estaVivoSemPrint()) {
                listaInimigos.removerInimigo(inimigo); // removemos ele da lista de inimigos
                /* se o inimigo morreu, removemos os efeitos aplicados nele */
                /* da lista de publishers */
                for(SubscriberEfeito subscriberSelecionado : publisher.getSubscribersEfeitos()){
                  subscriberSelecionado.matarEfeito(inimigo);
                }
                inimigo = null;
              }
              
              /* verificamos se todos morreram e o turno deve acabar */
              if (listaInimigos.getTamanho() == 0) {
                mensagemVitoria();
                limpaBatalha(publisher);
                return Turnos.GANHOU;
              }
              
              /* verificamos se o heroi ainda possui energia */
              if (heroi.getEnergia() == 0) {
                heroiAgiu = true;
              }
            } else if (carta.isEscudo()) {
              carta.usar(null, heroi);
              pausa(2000);
            }
            
            /* colocamos o efeito, caso houver no publisher */
            if(carta.temEfeito()) {
              for (int i = 0; i < carta.quantidadeEfeitos(); i++) {
                efeito = carta.retornarEfeito(i);
                
                if (efeito.ehCura()) {
                  subscriber = new SubscriberEfeito(heroi, efeito, efeito.getIdAtivacao());
                  
                } else if (efeito.ehEnvenenamento() || efeito.ehSangramento() || efeito.ehCurrupcao()) {
                  /* como os efeito de dano, inicialmente so estarao nas espadas
                   * o inimigo estara selecionado, a nao ser que ele tenha morrido */
                  
                  if(inimigo != null) {
                    subscriber = new SubscriberEfeito(inimigo, efeito, efeito.getIdAtivacao());
                  }
                }
                if(subscriber != null) {
                  publisher.inscrever(subscriber);
                  /* notificamos todos os efeitos instantaneos */
                  acionaPublisher(Turnos.INSTANTANEO);
                  if(atualizaInimigosMortos()){
                    mensagemVitoria();
                    limpaBatalha(publisher);
                    return Turnos.GANHOU;
                  }
                }
              }
            }
          }
        }
      }
      
      
      if(atualizaInimigosMortos()){
        mensagemVitoria();
        return Turnos.GANHOU;
      }
      
      /* Agora que o turno do heroi acabou */
      /* aplicamos os efeitos */
      acionaPublisher(Turnos.FINAL_TURNO_JOGADOR);
      
      /* devemos fazer duas verificacoes
       verificamos se alguem morreu e
       verificamos se ainda existem inimigos vivos
       pois nao queremos entrar no turno dos inimigos sem inimigos
       */
      
      if(atualizaInimigosMortos()){
        mensagemVitoria();
        return Turnos.GANHOU;
      }
      
      /* turno inimigo */
      /* queremos um inimigo random atacar o heroi */
      System.out.println();
      System.out.println("------------------------------");
      System.out.println("Turno dos Inimigos");
      /* verificamos se o inimigo que iria atacar morreu */
      inimigoAnunciar = escolheAtacante(inimigoAnunciar);
      inimigoAnunciar.atacar(heroi);
      pausa(2000);
      
      /* caso o inimigo tenha efeito, adicionamos o subscriber no publisher */
      if(inimigoAnunciar.temEfeitos()){
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