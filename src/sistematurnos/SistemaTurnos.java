package sistematurnos;
import java.util.Scanner;
import java.util.Random;
import constantes.Cores;
import dados.Dados;
import dados.Inimigo;
import usaveis.cartadano.CartaDano;

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
    System.out.println("1 - Usar Carta de Dano");
    System.out.println("2 - Usar Carta de Escudo");
    System.out.println("3 - Tentar fugir");
    System.out.println("0 - Passar turno");
    System.out.println("HP = " + dados.heroi.vida + "\n");
    System.out.println("ENG = " + dados.heroi.energia + "\n");
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
    /* Sistema de turnos será feito por ação */
    /* turno heroi */
    int comando;
    CartaDano cartaDano;
    //CartaEscudo cartaEscudo;
    int indiceRand = 0;
    int inimigosMortos = 0;
    Inimigo inimigo;
    while(true){

      heroiAgiu = 0;
      inimigoAgiu = 0;

      printAcoes();
      comando = teclado.nextInt(); // lemos o comando

      if(heroiAgiu == 0 && comando == 0){
        break;
      }
      
      /* caso o Heroi tenha escolhido usar carta de dano */
      if(comando == 1){
        dados.heroi.deque.printDoDeck();
        comando = teclado.nextInt(); // lemos o numero da carta que queremos usar
        
        /* tentamos puxar a carta dano */
        cartaDano = null;
        while(cartaDano == null){
          cartaDano = dados.heroi.deque.buscarCartaNumero(comando);
          /* caso não exista, lemos de novo */
          if(cartaDano == null){
            dados.heroi.deque.printDoDeck();;
            comando = teclado.nextInt();
          }
        }

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
        cartaDano.usar(inimigo, dados.heroi);

        /* verificamos se o inimigo morreu */
        if(inimigo.estaVivo() == false){
          inimigosMortos++;
          /* caso ele morreu removemos ele da lista de inimigos */
          dados.listaInimigos.removerInimigo(inimigo);
        }

        heroiAgiu = 1;
      }
      
      /* Usar escudo */
      if(heroiAgiu == 0 && comando == 2){
      }

      if(inimigosMortos == dados.listaInimigos.qntInimigos){
        break;
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

    
    
    return true; 
  }
}
