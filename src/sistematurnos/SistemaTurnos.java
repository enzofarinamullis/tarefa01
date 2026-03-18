package sistematurnos;
import java.util.Scanner;
import java.util.Random;

import constantes.Cabecalho;
import constantes.Cores;
import dados.Dados;
import dados.Heroi;
import dados.Inimigo;
import usaveis.pilhas.PilhaCompra;
import usaveis.pilhas.PilhaDescarte;
import utilitarios.PrintTerminal;
import usaveis.*;
import constantes.Turnos;

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
    
    /* sanity check: verificamos se nao existem inimigos */
    if(dados.listaInimigos == null || dados.listaInimigos.qntInimigos == 0){
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
    Mao mao = heroi.mao;
    PilhaCompra pilhaCompra = heroi.pilhaCompra;
    PilhaDescarte pilhaDescarte = heroi.pilhaDescarte;
    
    /* embaralhamos a pilha de compra do heroi do heroi */
    pilhaCompra.embraralhaPlha();
    /* compramos 5 cartas */
    pilhaCompra.compraCarta(mao, 5);
    
    int comando;
    Cartas carta = null;
    int indiceRand = 0;
    int inimigosMortos = 0;
    Inimigo inimigo;
    int qntInimigosInicial = dados.listaInimigos.qntInimigos;
    
    while(true){
      /* resetamos o valor de escudo como pedido no enunciado */
      dados.heroi.escudo = 0;
      /* completamos a energia do heroi como pedido no enunciado */
      dados.heroi.energia = dados.heroi.energiaLimite;
      
      /* turno do heroi */
      while(heroiAgiu == 0){
        
        if(dados.heroi.estaVivo() == false){
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
            for(int i = 1; i < dados.listaInimigos.qntInimigos + 1; i++){
              inimigo = dados.listaInimigos.buscarInimigo(i);
              dados.listaInimigos.removerInimigo(inimigo);
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
        if(comando == Turnos.USAR && heroi.verificaEnergia()){
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
          
          
          carta = mao.cartas.remove(comando);
          
          /* usamos a carta */
          if(carta.ehDano){
            System.out.println("Escolha um alvo:\n"); 
            dados.listaInimigos.mostrarInimigos();
            comando = teclado.nextInt();
            
            /* caso o numero nao tenha sido aprovado lemos o numero denovo*/
            while(comando <= 0 || comando > dados.listaInimigos.qntInimigos){
              System.out.println("Numero inválido, escolha outro:");
              dados.listaInimigos.mostrarInimigos();;
              comando = teclado.nextInt();
            }
            
            /* buscamos o inimigo */
            inimigo = this.dados.listaInimigos.buscarInimigo(comando);
            /* aplicamos o dano ao inimigo */
            carta.usar(inimigo, dados.heroi);

            /* verificamos se o inimigo morreu */
            if(inimigo.estaVivo() == false){
              inimigosMortos++;
              /* caso ele morreu removemos ele da lista de inimigos */
              dados.listaInimigos.removerInimigo(inimigo);
            }

            /* verificamos se todos morreram e o turno deve acabar */
            if(inimigosMortos == qntInimigosInicial){
              heroiAgiu = 1;
              return true;
            }

            if(dados.heroi.energia == 0){
              heroiAgiu = 1;
            }
          }
          else if(carta.ehEscudo){
            carta.usarEscudo(dados.heroi);
          }

        }
      }

      /* turno inimigo */
      /* queremos um inimigo random atacar o heroi */
      System.out.println("Turno dos Inimigos");
      indiceRand = random.nextInt(dados.listaInimigos.qntInimigos);
      /* inimigo que for atacar estar na posicao indiceRand */
      inimigo = dados.listaInimigos.buscarInimigo(indiceRand + 1);
      inimigo.atacar(dados.heroi);
      
      inimigoAgiu = 1;
    }
  }

}