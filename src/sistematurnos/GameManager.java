package sistematurnos;

import constantes.Cabecalho;
import constantes.Cores;
import constantes.Turnos;
import dados.Dados;
import dados.Heroi;
import dados.Inimigo;
import dados.ListaInimigos;
import usaveis.Mao;
import usaveis.cartas.Carta;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;
import utilitarios.PrintTerminal;

import java.util.Random;
import java.util.Scanner;

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
  
  private boolean haInimigos(){
    return dados.listaInimigos.getTamanho() != 0;
  }
  
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
  
  private Inimigo escolheInimigoAleatorio(){
    ListaInimigos listaInimigos = dados.listaInimigos;
    int indiceRand = 0;
    indiceRand = random.nextInt(listaInimigos.getTamanho());
    /* inimigo que for atacar estar na posicao indiceRand */
    return listaInimigos.buscarInimigo(indiceRand + 1);
  }
  
  private void resetStatus(Heroi heroi){
    /* resetamos o valor de escudo como pedido no enunciado */
    heroi.setaEscudo(0);
    /* completamos a energia do heroi como pedido no enunciado */
    heroi.setaEnergia(heroi.getEnergiaLimite());
  }
  
  private void embaralhaECompra(){
    Heroi heroi = dados.heroi;
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    
    /* embaralhamos a pilha de compra do heroi do heroi */
    pilhaCompra.embraralhaPlha();
    /* compramos 5 cartas */
    pilhaCompra.compraCarta(mao, pilhaDescarte, Turnos.QNT_COMPRAR);
  }
  
  /* como pedido pelo enunciado remover a mao inteira no inicio do turno */
  /* colocar a Mao inteira no descarte */
  /* comprar 5 cartas */
  private void descarteECompra(){
    Heroi heroi = dados.heroi;
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    pilhaDescarte.removeMao(mao);
    pilhaCompra.compraCarta(mao, pilhaDescarte, Turnos.QNT_COMPRAR);
  }
  
  private int leComando(){
    int comando = teclado.nextInt(); // lemos o comando
    // verificamos se o comando eh valido
    if(comando != Turnos.USAR && comando != Turnos.FUGIR && comando != Turnos.PASSAR){
      comando = teclado.nextInt();
    }
    return comando;
  }
  
  private void limpaInimigos(ListaInimigos listaInimigos){
    Inimigo inimigo = null;
    for(int i = 1; i < listaInimigos.getTamanho() + 1; i++){
      inimigo = listaInimigos.buscarInimigo(i);
      listaInimigos.removerInimigo(inimigo);
    }
  }
  
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
  
  private void mostraCartas(){
    Mao mao = dados.heroi.getMao();
    System.out.println("Escolha uma opção:");
    mao.printMao();
    
    System.out.println();
    System.out.println("-1 - Voltar");
  }
  
  public Carta leCarta(){
    int comando;
    Heroi heroi = dados.heroi;
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    Mao mao = heroi.getMao();
    Carta carta = null;
    
    comando = teclado.nextInt(); // lemos o numero da carta que queremos usar
    
    /* tentamos puxar a carta */
    while(-1 > comando  || comando > mao.cartas.size()){
      if(comando == -1){
        return null;
      }
      System.out.println("Comando inválido");
      comando = teclado.nextInt();
      mao.printMao();
    }
    
    /* removemos a carta da mao e colocamos em descarte */
    carta = mao.cartas.remove(comando);
    pilhaDescarte.pilha.add(carta);
  }
  
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
  
  private Inimigo escolheAtacante(Inimigo inimigoAnunciar){
    int indiceRand;
    ListaInimigos listaInimigos = dados.listaInimigos;
    Heroi heroi = dados.heroi;
    /* verificamos se o inimigo que iria atacar morreu */
    if(!inimigoAnunciar.estaVivo()) {
      System.out.println();
      System.out.println("O inimigo que iria te atacar morreu...");
      System.out.println("Cuidado, que outro irá atacar:");
      /* caso nao esteja vivo */
      indiceRand = random.nextInt(listaInimigos.getTamanho());
      /* inimigo que for atacar estar na posicao indiceRand */
      inimigoAnunciar = listaInimigos.buscarInimigo(indiceRand + 1);
      inimigoAnunciar.anunciar();
    }
    inimigoAnunciar.atacar(heroi);
  }
  
  public void turno(){
    int numTurno = 0;
    boolean voltar = false;
    mensagemCombate();
    
    /* para facilitar a leitura */
    Heroi heroi = dados.heroi;
    Mao mao = heroi.getMao();
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    ListaInimigos listaInimigos = dados.listaInimigos;
    
    embaralhaECompra();
    
    int comando;
    Carta carta = null;
    int inimigosMortos = 0;
    Inimigo inimigoAnunciar;
    Inimigo inimigo;
    int qntInimigosInicial = listaInimigos.getTamanho();
    
    while(true){
      /* escolhemos o inimigo que ira atacar */
      inimigoAnunciar = escolheInimigoAleatorio();
      inimigoAnunciar.anunciar(); // fazemos o seu anuncio
      
      resetStatus(heroi);
      
      if(numTurno != 0) {
        descarteECompra();
      }
      
      /* turno do heroi */
      while(!heroiAgiu){
        
        if(!heroi.estaVivo()){
          System.out.println(Cores.ANSI_PURPLE + "Você morreu!" + Cores.ANSI_RESET);
          return;
        }
        
        /* Vemos qual acao o heroi quer tomar */
        printAcoes();
        comando = leComando();
        
        if(comando == Turnos.PASSAR){
          heroiAgiu = true;
          break;
        }
        
        if(comando == Turnos.FUGIR){
          if(calculaChangeFuga()){ return; }
          else{ break; }
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
              
              /* verificamos se o inimigo morreu */
              if (!inimigo.estaVivo()) {
                inimigosMortos++;
                listaInimigos.removerInimigo(inimigo); // removemos ele da lista de inimigos
              }
              
              /* verificamos se todos morreram e o turno deve acabar */
              if (listaInimigos.getTamanho() == 0) {
                return;
              }
              
              /* verificamos se o heroi ainda possui energia */
              if (heroi.getEnergia() == 0) {
                heroiAgiu = true;
              }
            } else if (carta.isEscudo()) {
              carta.usar(null, heroi);
            }
          }
        }
      }
      
      /* Agora que o turno do heroi acabou */
      /* NOTIFICAR */
      
      /* turno inimigo */
      /* queremos um inimigo random atacar o heroi */
      System.out.println("Turno dos Inimigos");
      /* verificamos se o inimigo que iria atacar morreu */
      inimigoAnunciar = escolheAtacante(inimigoAnunciar);
      inimigoAnunciar.atacar(heroi);
      
      inimigoAgiu = true;
      numTurno++;
    }
  }
  
}
