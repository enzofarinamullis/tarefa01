package sistematurnos;
import java.util.Scanner;
import java.util.Random;

import constantes.Cabecalho;
import constantes.Cores;
import dados.Dados;
import dados.Inimigo;
import utilitarios.PrintTerminal;
import usaveis.*;

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

    if(dados.listaInimigos == null || dados.listaInimigos.qntInimigos == 0){
      System.out.println("Não há inimigos, por aqui!");
      return true;
    }
    else{
      System.out.println(Cores.ANSI_YELLOW + "Cuidado!" + Cores.ANSI_RESET +
        " Você entrou em combate com:");
      dados.listaInimigos.printInimigosSemIndice();
    }

    int comando;
    Cartas carta = null;
    int indiceRand = 0;
    int inimigosMortos = 0;
    Inimigo inimigo;
    int qntInimigosInicial = dados.listaInimigos.qntInimigos;
    while(true){
      while(heroiAgiu == 0){
        if(heroiAgiu == 1){
          dados.heroi.escudo = 0;
          heroiAgiu = 0;
        }
        dados.heroi.energia = dados.heroi.energiaLimite;
        printAcoes();

        comando = teclado.nextInt(); // lemos o comando
        // verificamos se o comando eh valido
        if(comando != 1 && comando != 2 && comando != 3 && comando != 0){
          comando = teclado.nextInt();
        }

        if(comando == 0){
          break;
        }
        if(comando == 2){
          if(random.nextInt(100) <= 10){
            for(int i = 1; i < dados.listaInimigos.qntInimigos + 1; i++){
              inimigo = dados.listaInimigos.buscarInimigo(i);
              dados.listaInimigos.removerInimigo(inimigo);
            }
            System.out.println(Cores.ANSI_PURPLE + " >> Dificilmente você escapará dessa << " + Cores.ANSI_RESET);
            return true;
          }
          else{
            System.out.println(Cores.ANSI_PURPLE + " >> Dificilmente você escapará dessa << " + Cores.ANSI_RESET);
            break;
          }
        }
        
        /* caso o comando seja usar carta */
        if(comando == 1 && dados.heroi.verificaEnergia() == true){
          dados.heroi.mao.printMao();;
          comando = teclado.nextInt(); // lemos o numero da carta que queremos usar
          
          /* tentamos puxar a carta */
          while(carta == null){
            carta = dados.heroi.mao.buscaCartaNum(comando);
            /* caso não exista, lemos de novo */
            if(carta == null){
              dados.heroi.mao.printMao();
              comando = teclado.nextInt();
            }
          }

          if(carta.ehDano == true){
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

        }
      }

      /* turno inimigo */
      /* queremos um inimigo random atacar o heroi */
      System.out.println("Turno dos Inimigos");
      indiceRand = random.nextInt(dados.listaInimigos.qntInimigos);
      /* inimigo que for atacar estar na posicao indiceRand */
      inimigo = dados.listaInimigos.buscarInimigo(indiceRand);
      inimigo.atacar(dados.heroi);
      
      inimigoAgiu = 1;
    }
  }

}