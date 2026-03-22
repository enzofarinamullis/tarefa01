package sistematurnos;
import constantes.Cabecalho;
import constantes.Cores;
import constantes.Turnos;
import dados.Dados;
import dados.Heroi;
import dados.Inimigo;
import dados.ListaInimigos;
import java.util.Random;
import java.util.Scanner;
import usaveis.*;
import usaveis.cartas.Carta;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;
import utilitarios.PrintTerminal;

public class SistemaTurnos {
  Dados dados;
  Scanner teclado;
  int heroiAgiu;
  int inimigoAgiu;
  Random random;

  public SistemaTurnos(Dados dados){
    this.dados = dados;
    this.heroiAgiu = 0;
    this.inimigoAgiu = 0;
    this.teclado = new Scanner(System.in);
    this.random = new Random();
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


  public boolean turno(){
    int numTurno = -1;
    /* sanity check: verificamos se nao existem inimigos */
    if(dados.listaInimigos.getTamanho() == 0){
      System.out.println("Não há inimigos, por aqui!");
      return true;
    }
    /* informamos quais inimigos estao presentes */
    else{
      System.out.println(Cores.ANSI_YELLOW + "Cuidado!" + Cores.ANSI_RESET +
        " Você entrou em combate com:");
      dados.listaInimigos.printInimigosSemIndice();
    }
    
    /* para facilitar a leitura */
    Heroi heroi = dados.heroi;
    Mao mao = heroi.getMao();
    PilhaCompra pilhaCompra = heroi.getPilhaCompra();
    PilhaDescarte pilhaDescarte = heroi.getPilhaDescarte();
    
    ListaInimigos listaInimigos = dados.listaInimigos;
    
    /* embaralhamos a pilha de compra do heroi do heroi */
    pilhaCompra.embraralhaPlha();
    /* compramos 5 cartas */
    pilhaCompra.compraCarta(mao, pilhaDescarte, 5);
    
    int comando;
    Carta carta = null;
    int indiceRand = 0;
    int inimigosMortos = 0;
    Inimigo inimigo;
    int qntInimigosInicial = listaInimigos.getTamanho();
    
    while(true){
      /* escolhemos o inimigo que ira atacar */
      indiceRand = random.nextInt(listaInimigos.getTamanho());
      /* inimigo que for atacar estar na posicao indiceRand */
      inimigo = listaInimigos.buscarInimigo(indiceRand + 1);
      inimigo.anunciar(); // fazemos o seu anuncio
      /* resetamos o valor de escudo como pedido no enunciado */
      heroi.setaEscudo(0);
      /* completamos a energia do heroi como pedido no enunciado */
      heroi.setaEnergia(heroi.getEnergiaLimite());
      
      /* como pedido pelo enunciado remover a mao inteira no inicio do turno */
      /* colocar a Mao inteira no descarte */
      /* comprar 5 cartas */
      numTurno++;
      if(numTurno > 0) {
        pilhaDescarte.removeMao(mao);
        pilhaCompra.compraCarta(mao, pilhaDescarte, 5);
      }
      
      /* turno do heroi */
      while(heroiAgiu == 0){
        
        if(heroi.estaVivo() == false){
          System.out.println(Cores.ANSI_PURPLE + "Você morreu!" + Cores.ANSI_RESET);
          return true;
        } 

        /* Vemos qual acao o heroi quer tomar */
        printAcoes();
        comando = teclado.nextInt(); // lemos o comando
        // verificamos se o comando eh valido
        if(comando != Turnos.USAR && comando != Turnos.FUGIR && comando != Turnos.PASSAR){
          comando = teclado.nextInt();
        }
        
        if(comando == Turnos.PASSAR){
          break;
        }
        
        if(comando == Turnos.FUGIR){
          /* calculamos a chance de fuga */
          if(random.nextInt(100) <= 10){
            for(int i = 1; i < listaInimigos.getTamanho() + 1; i++){
              inimigo = listaInimigos.buscarInimigo(i);
              listaInimigos.removerInimigo(inimigo);
            }
            System.out.println(Cores.ANSI_PURPLE + " >> Dificilmente você escapará dessa << " + Cores.ANSI_RESET);
            return true;
          }
          else{
            System.out.println(Cores.ANSI_PURPLE + " >> Parabéns... você escapou... -_- << " + Cores.ANSI_RESET);
            break;
          }
        }
        
        /* Caso a escolha seja USAR, verificamos se ha energia suficiente */
        if(comando == Turnos.USAR && heroi.verificaEnergia(mao)){
          System.out.println("Escolha uma opção:");
          mao.printMao();;
          comando = teclado.nextInt(); // lemos o numero da carta que queremos usar
          
          /* tentamos puxar a carta */
          carta = null;
          while(0 > comando  || comando > mao.cartas.size()){
            System.out.println("Comando inválido");
            comando = teclado.nextInt();
            mao.printMao();
          }
          
          /* removemos a carta da mao e colocamos em descarte */
          carta = mao.cartas.remove(comando);
          pilhaDescarte.pilha.add(carta);
          
          /* usamos a carta */
          if(carta.isDano()){
            System.out.println("Escolha um alvo:\n"); 
            listaInimigos.mostrarInimigos();
            comando = teclado.nextInt();
            
            /* caso o numero nao tenha sido aprovado lemos o numero denovo*/
            while(comando <= 0 || comando > listaInimigos.getTamanho()){
              System.out.println("Numero inválido, escolha outro:");
              listaInimigos.mostrarInimigos();;
              comando = teclado.nextInt();
            }
            
            /* buscamos o inimigo */
            inimigo = listaInimigos.buscarInimigo(comando);
            /* aplicamos o dano ao inimigo */
            carta.usar(inimigo, heroi);
            

            /* verificamos se o inimigo morreu */
            if(inimigo.estaVivo() == false){
              inimigosMortos++;
              /* caso ele morreu removemos ele da lista de inimigos */
              listaInimigos.removerInimigo(inimigo);
            }

            /* verificamos se todos morreram e o turno deve acabar */
            if(inimigosMortos == qntInimigosInicial){
              heroiAgiu = 1;
              return true;
            }

            if(heroi.getEnergia() == 0){
              heroiAgiu = 1;
            }
          }
          else if(carta.isEscudo()){
            carta.usar(null, heroi);
          }

        }
      }

      /* turno inimigo */
      /* queremos um inimigo random atacar o heroi */
      System.out.println("Turno dos Inimigos");
      /* verificamos se o inimigo que iria atacar morreu */
      if(!inimigo.estaVivo()) {
        System.out.println();
        System.out.println("O inimigo que iria te atacar morreu...");
        System.out.println("Cuidado, que outro irá atacar:");
        /* caso nao esteja vivo */
        indiceRand = random.nextInt(listaInimigos.getTamanho());
        /* inimigo que for atacar estar na posicao indiceRand */
        inimigo = listaInimigos.buscarInimigo(indiceRand + 1);
        inimigo.anunciar();
      }
      inimigo.atacar(heroi);
      
      inimigoAgiu = 1;
    }
  }

}