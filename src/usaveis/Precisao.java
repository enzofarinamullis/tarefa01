package usaveis;
import java.util.Random;

public class Precisao {
  private Random aleatorio;

  /* Construtor */
  public Precisao(){
    this.aleatorio = new Random();
  }

  /* Métodos */
   
  public int rolarDado(){
    int resultado = aleatorio.nextInt(20) + 1;
    System.out.println("Rolando D20 de precisão:");
    System.out.println(resultado);
      
    return resultado;
  }

  public int rolarVantagem() {
    System.out.println("Rolando D20 de precisão com vantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.max(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }

  public int rolarDesvantagem() {
    System.out.println("Rolando D20 de precisão com desvantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.min(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }
  public boolean teste(int dificuldade) {
    return rolarDado()>= dificuldade;
  }

  public void critico(int resultado) {
    if (resultado == 20) {
      System.out.println("Acerto crítico");
    }
    else if (resultado == 1) {
      System.out.println("Erro crítico");
    }
  } 
}