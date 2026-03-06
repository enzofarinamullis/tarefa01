import java.util.Random;

public class D20 {
  private Random aleatorio;

  /* Construtor */
  public D20(){
    this.aleatorio = new Random();
  }

  /* Métodos */

  public int rolar_dado(){
    int resultado = aleatorio.nextInt();
    return resultado;
  }

  public int rolar_com_vantagem() {
    int dado_1 = rolar_dado();
    int dado_2 = rolar_dado();
    int resultado = Math.max(dado_1, dado_2);
    return resultado;
  }

  public int rolar_com_desvantagem() {
    int dado_1 = rolar_dado();
    int dado_2 = rolar_dado();
    int resultado = Math.min(dado_1, dado_2);
    return resultado;
  }
  public boolean teste(int dificuldade) {
    return rolar_dado()>= dificuldade;
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