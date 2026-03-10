import java.util.Scanner;

public class SistemaTurnos {
  Dados dados;
  Scanner acao;
  int heroiAgiu;
  int inimigoAgiu;

  public SistemaTurnos(Dados dados){
    this.dados = dados;
    this.heroiAgiu = 0;
    this.inimigoAgiu = 0;
    this.acao = new Scanner(System.in);
  }


  public int converteCharInt(char caracter){
    if(caracter <= '0' && caracter < 10){
      return caracter - 30;
    }
    else{
      return -1;
    }
  }
  public int converteStringInt(String string){
    int comprimento = string.length();

    int multiplicador = 1;

    int numero = 0;
    int casa = 0;
    for(comprimento--; comprimento >= 0; comprimento--){
      casa = converteCharInt(string.charAt(comprimento));
      if(casa == -1){
        return -1; // string invalida
      }
      numero = numero + multiplicador * casa;
      multiplicador = multiplicador * 10;
    }

    return numero;

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
    String comando;
    CartaDano cartaDano;
    int numero = 0;
    while(true){
      /* printamos as acoes */
      printAcoes();
      /* lemos o comando */
      comando = acao.nextLine();

      if(comando == "0"){
        break;
      }
      
      /* caso o Heroi tenha escolhido usar carta de dano */
      if(comando == "1"){
        dados.heroi.dequeDano.mostrarCartaDano();
        /* lemos o comando */
        comando = acao.nextLine();

        /* como a entrada do teclado retorna uma string, precisamos converte-la em int */
        numero = converteStringInt(comando);

          /* caso o numero nao tenha sido aprovado lemos o numero denovo*/
        while(numero <= 0 || numero > dados.heroi.dequeDano.qntCartas){
          System.out.println("Numero inválido, escolha outro:");
          dados.heroi.dequeDano.mostrarCartaDano();
          comando = acao.next();
          numero = converteStringInt(comando);
        }

        /* puxamos a carta que queremos usar */
        cartaDano = dados.heroi.dequeDano.selecionarCartaDano(numero);

        System.out.println("Escolha um alvo:\n"); 
        dados.listaInimigos.mostrarInimigos();
        comando = acao.nextLine();
        
        numero = converteStringInt(comando);
        /* caso o numero nao tenha sido aprovado lemos o numero denovo*/
        while(numero <= 0 || numero > dados.listaInimigos.qntInimigos){
          System.out.println("Numero inválido, escolha outro:");
          dados.listaInimigos.mostrarInimigos();;
          comando = acao.next();
          numero = converteStringInt(comando);
        }
        
        /* buscamos o inimigo */
        Inimigo inimigo = this.dados.listaInimigos.buscarInimigo(numero);
        /* aplicamos o dano ao inimigo */
        cartaDano.usar(inimigo, dados.heroi);
      }

      if(comando == "2"){

      }
    }
    return true; 
  }
}
