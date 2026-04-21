package usaveis.D;
import java.util.Random;

import constantes.Cores;

/**
 * Representa um dado de precisão no jogo, utilizado para determinar o resultado de ações
 * relacionadas à precisão.
 *
 * <p>
 *   O dado de precisão é um dado personalizado que tem 20 lados, representando um
 *   resultado de 1 a 20. Ele é utilizado para determinar o sucesso ou falha de
 *   ações relacionadas à precisão, como ataques ou habilidades que exigem precisão.
 * </p>
 * @see usaveis.D
 */
public class Precisao {
  private Random aleatorio;

  /* Construtor */
  public Precisao(){
    this.aleatorio = new Random();
  }

  /* Métodos */
  
  private void pausa(long milissegundos){
    try {
      Thread.sleep(milissegundos);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
   
  public int rolarDado(){
    int resultado = aleatorio.nextInt(20) + 1;
    System.out.println("Rolando D20 de precisão:");
    pausa(700);
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
    System.out.println("Rolando D20 de precisão com vantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.max(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }

  public int rolarDesvantagem() {
    System.out.print("> Rolando ");
    Cores.cprint(Cores.ANSI_BLUE, "D20");
    System.out.print(" de precisão com desvantagem <\n");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.min(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }
  public boolean teste(int dificuldade) {
    return rolarDadoSemAnim()>= dificuldade;
  }
  
  public int rolarDadoSemAnim(){
    int resultado = aleatorio.nextInt(20) + 1;
    System.out.println("Rolando D20 de precisão:");
    System.out.println(resultado);
    
    return resultado;
  }
  
  public int rolarVantagemSemAnim() {
    System.out.println("Rolando D20 de precisão com vantagem:");
    int dado_1 = rolarDadoSemAnim();
    int dado_2 = rolarDadoSemAnim();
    int resultado = Math.max(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }
  
  public int rolarDesvantagemSemAnim() {
    System.out.print("> Rolando ");
    Cores.cprint(Cores.ANSI_BLUE, "D20");
    System.out.print(" de precisão com desvantagem <\n");
    int dado_1 = rolarDadoSemAnim();
    int dado_2 = rolarDadoSemAnim();
    int resultado = Math.min(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }
  
  public void critico(int resultado) {
    if (resultado == 20) {
      System.out.print("Acerto ");
      Cores.cprintn(Cores.ANSI_PURPLE, "crítico!");
    }
    else if (resultado == 1) {
      System.out.println("Erro ");
      Cores.cprintn(Cores.ANSI_PURPLE, "crítico");
    }
  } 
}