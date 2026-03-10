import java.util.Scanner;

public class SistemaTurnos {
  Dados dados;
  Scanner teclado;
  int heroiAgiu;
  int inimigoAgiu;
  int random

  public SistemaTurnos(Dados dados){
    this.dados = dados;
    this.heroiAgiu = 0;
    this.inimigoAgiu = 0;
    this.teclado = new Scanner(System.in);
    this.random = 0;
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
    /* Sistema de turnos será feito por ação */
    /* turno heroi */
    int comando;
    CartaDano cartaDano;
    CartaEscudo cartaEscudo;
    int numero = 0;
    int inimigosMortos = 0;
    while(true){

      heroiAgiu = 0;
      inimigoAgiu = 0;

      /* printamos as acoes */
      printAcoes();
      /* lemos o comando */
      comando = teclado.nextInt();

      if(heroiAgiu == 0 && comando == 0){
        break;
      }
      
      /* caso o Heroi tenha escolhido usar carta de dano */
      if(comando == 1){
        dados.heroi.dequeDano.mostrarCartaDano();
        /* lemos o comando */
        comando = teclado.nextInt();

        /* verificamos se o numero é valido */
        while(comando <= 0 || comando > dados.heroi.dequeDano.qntCartas){
          System.out.println("Numero inválido, escolha outro:");
          dados.heroi.dequeDano.mostrarCartaDano();
          comando = teclado.nextInt();
        }

        /* puxamos a carta que queremos usar */
        cartaDano = dados.heroi.dequeDano.selecionarCartaDano(comando);

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
        Inimigo inimigo = this.dados.listaInimigos.buscarInimigo(numero);
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

      if(heroiAgiu == 0 && comando == 2){
        dados.heroi.dequeEscudo.mostrarCartaEscudo();
        comando = teclado.nextInt();
        if(comando <= 0 || comando > dados.heroi.dequeEscudo.qntCartas){
          System.out.println("Número Inválido, escolha outro:");
          dados.heroi.dequeEscudo.mostrarCartaEscudo();
          comando = teclado.nextInt();
        }

        cartaEscudo = dados.heroi.dequeEscudo.selecionarCartaEscudo(comando);
        cartaEscudo.usar(dados.heroi);
        heroiAgiu = 1;
      }

      if(inimigosMortos == dados.listaInimigos.qntInimigos){
        break;
      }

      /* turno inimigo */
      System.out.println("Turno dos Inimigos");
      
    }

    
    
    return true; 
  }
}
