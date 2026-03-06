import java.util.Random;

public class D20 {
  private Random aleatorio;

  /* Construtor */
  public D20(){
    this.aleatorio = new Random();
  }

  /* Métodos */

  public int rolarDado(){
    int resultado = aleatorio.nextInt();
    return resultado;
  }

  public int rolarVantagem() {
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.max(dado_1, dado_2);
    return resultado;
  }

  public int rolarDesvantagem() {
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.min(dado_1, dado_2);
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