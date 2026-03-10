import java.util.Random;

public class Potencia {
  private Random aleatorio;
  public Potencia() {
    this.aleatorio =  new Random();
  }
  public int rolarDado(){
    int resultado = aleatorio.nextInt();
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




  



  

}