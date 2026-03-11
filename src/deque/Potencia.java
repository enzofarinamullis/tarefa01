package deque;
import java.util.Random;

public class Potencia {
  private Random aleatorio;
  private int NdeLados;
  //construtor:
  public Potencia(int NdeLados) {
    this.aleatorio =  new Random();
    this.NdeLados = NdeLados;
  }
  public int rolarDado(){
      int resultado = aleatorio.nextInt(NdeLados) + 1;
      System.out.println("Rolando dado de potencia:");
      System.out.println(resultado);
      
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
    else if (resultado == NdeLados) {
      System.out.println("Passou de raspão");
    }
  } 
}