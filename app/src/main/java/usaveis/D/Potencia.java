package usaveis.D;
import java.util.Random;


/**
 * Representa um dado de potência no jogo, utilizado para determinar o resultado de ações
 * relacionadas à potência.
 *
 * <p>
 *   O dado de potência é um dado personalizado que pode ter um número variável de lados,
 *   dependendo do contexto do jogo. Ele é utilizado para determinar o sucesso ou falha de
 *   ações relacionadas à potência, como ataques ou habilidades especiais.
 * </p>
 * @see usaveis.D
 */
public class Potencia {
  private Random aleatorio;
  private int NdeLados;
  //construtor:
  public Potencia(int NdeLados) {
    this.aleatorio =  new Random();
    this.NdeLados = NdeLados;
  }
  
  private void pausa(long milissegundos){
    try {
      Thread.sleep(milissegundos);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public int rolarDado(){
    int resultado = aleatorio.nextInt(NdeLados) + 1;
    System.out.println("Rolando dado de potencia:");
    System.out.println(".");
    pausa(700);
    System.out.println("..");
    pausa(700);
    System.out.println("...");
    pausa(700);
    System.out.println(resultado);
    pausa(700);
    
    return resultado;
  }
   public int rolarVantagem() {
    System.out.println("Rolando dado de potencia com vantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.max(dado_1, dado_2);
    return resultado;
  }
  public int rolarDesvantagem() {
    System.out.println("Rolando dado de potencia com desvantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.min(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }

  public void critico(int resultado) {
    if (resultado == NdeLados) {
      System.out.println("Golpe Brutal");
    }
    else if (resultado == 1) {
      System.out.println("Passou de raspão");
    }
  } 
}
