package usaveis.D;
import java.util.Random;

import constantes.Cores;

/**
 * Classe responsável por simular rolagens de dados de precisão (D20) no sistema de RPG.
 * Fornece métodos para rolagens normais, com vantagem, com desvantagem, testes de dificuldade
 * e verificação de acertos/erros críticos.
 * 
 * <p>Esta classe implementa animações com pausas durante as rolagens para simular
 * a expectativa de um dado físico sendo lançado.</p>
 * @see constantes.Cores
 */

public class Precisao {
  /**
   * Gerador de números aleatórios utilizado para as rolagens de dados.
   */
  private Random aleatorio;
    /**
   * Construtor padrão que inicializa o gerador de números aleatórios.
   */
  public Precisao(){
    this.aleatorio = new Random();
  }
  /**
   * Pausa a execução da thread atual por um determinado número de milissegundos.
   * 
   * @param t (tempo de pausa em milissegundos).
   */
  private void pausa(long milissegundos){
    try {
      Thread.sleep(milissegundos);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
   /**
   * Realiza uma rolagem de um dado de 20 faces (D20).
   * Exibe animação de pontos durante a rolagem e mostra o resultado.
   * 
   * @return número inteiro entre 1 e 20 (inclusive)
   */
  
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

  /**
   * Realiza uma rolagem com vantagem, que consiste em rolar dois D20s e
   * utilizar o maior resultado.
   * 
   * <p>O método exibe uma mensagem indicando a rolagem com vantagem,
   * realiza duas rolagens individuais e retorna o maior valor.</p>
   * 
   * @return o maior valor entre as duas rolagens de D20
   * @see #rolarDado()
   */

  public int rolarVantagem() {
    System.out.println("Rolando D20 de precisão com vantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.max(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }
 /**
   * Realiza uma rolagem com desvantagem, que consiste em rolar dois D20s e
   * utilizar o menor resultado.
   * 
   * <p>O método exibe uma mensagem formatada com cor azul indicando a rolagem
   * com desvantagem, realiza duas rolagens individuais e retorna o menor valor.</p>
   * 
   * @return o menor valor entre as duas rolagens de D20
   * @see #rolarDado()
   * @see constantes.Cores#cprint(String, String)
   */

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
    /**
   * Realiza um teste de dificuldade, verificando se o resultado da rolagem
   * do D20 é maior ou igual ao valor de dificuldade especificado.
   * 
   * @param dificuldade valor numérico que representa a dificuldade do teste
   * @return {@code true} se o resultado da rolagem for maior ou igual à dificuldade,
   *         {@code false} caso contrário
   * @see #rolarDado()
   */
  public boolean teste(int dificuldade) {
    return rolarDado()>= dificuldade;
  }
  
/**
   * Verifica se o resultado de uma rolagem é um acerto crítico (20) ou erro crítico (1)
   * e exibe mensagens apropriadas com formatação de cor.
   * 
   * <p>Para acerto crítico (resultado = 20): exibe "Acerto crítico!" em roxo.<br>
   * Para erro crítico (resultado = 1): exibe "Erro crítico" em roxo.</p>
   * 
   * @param resultado o valor obtido na rolagem do dado (esperado entre 1 e 20)
   * @see constantes.Cores#cprintn(String, String)
   */

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