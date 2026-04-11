package usaveis.D;
import java.util.Random;

/**
 * Classe responsável por simular rolagens de dados de potência para dano ou outros
 * efeitos no sistema de RPG.
 * 
 * <p>Diferente da classe {@link Precisao}, esta classe trabalha com dados de diferentes
 * quantidades de lados (ex: D6, D8, D10, D12) e possui regras próprias para
 * acertos críticos (Golpe Brutal) e falhas (Passou de raspão).</p>
 * 
 * <p>Os métodos incluem rolagens normais, com vantagem, com desvantagem e
 * verificação de resultados críticos específicos para dados de potência.</p>
 * @see Precisao
 */

public class Potencia {
    /**
   * Gerador de números aleatórios utilizado para as rolagens de dados.
   */
  private Random aleatorio;
    /**
   * Número de faces do dado de potência (ex: 6, 8, 10, 12, 20).
   */
  private int NdeLados;
   /**
   * Construtor que inicializa o dado de potência com um número específico de faces.
   * 
   * @param NdeLados número de faces do dado (deve ser um inteiro positivo)
   */

  public Potencia(int NdeLados) {
    this.aleatorio =  new Random();
    this.NdeLados = NdeLados;
  }
  /**
   * Pausa a execução da thread atual por um determinado número de milissegundos.
   * Utilizado para criar uma animação durante as rolagens.
   * 
   * @param milissegundos tempo de pausa em milissegundos
   */
  private void pausa(long milissegundos){
    try {
      Thread.sleep(milissegundos);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

   /**
   * Realiza uma rolagem do dado de potência.
   * Exibe uma animação de pontos durante a rolagem e mostra o resultado.
   * 
   * <p>O resultado será um número entre 1 e o número de faces do dado
   * (definido no construtor).</p>
   * 
   * @return número inteiro entre 1 e {@link #NdeLados} (inclusive)
   */
  
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
  /**
   * Realiza uma rolagem com vantagem para o dado de potência.
   * Consiste em rolar dois dados e utilizar o maior resultado.
   * 
   * <p>O método exibe uma mensagem indicando a rolagem com vantagem,
   * realiza duas rolagens individuais e retorna o maior valor.</p>
   * 
   * @return o maior valor entre as duas rolagens do dado de potência
   * @see #rolarDado()
   */
   public int rolarVantagem() {
    System.out.println("Rolando dado de potencia com vantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.max(dado_1, dado_2);
    return resultado;
  }
   /**
   * Realiza uma rolagem com desvantagem para o dado de potência.
   * Consiste em rolar dois dados e utilizar o menor resultado.
   * 
   * <p>O método exibe uma mensagem indicando a rolagem com desvantagem,
   * realiza duas rolagens individuais, exibe o resultado e retorna o menor valor.</p>
   * 
   * @return o menor valor entre as duas rolagens do dado de potência
   * @see #rolarDado()
   */
  
  public int rolarDesvantagem() {
    System.out.println("Rolando dado de potencia com desvantagem:");
    int dado_1 = rolarDado();
    int dado_2 = rolarDado();
    int resultado = Math.min(dado_1, dado_2);
    System.out.println(resultado);
    return resultado;
  }

   /**
   * Verifica se o resultado de uma rolagem é um acerto crítico (Golpe Brutal)
   * ou uma falha (Passou de raspão) baseado no número máximo de faces do dado.
   * 
   * <p><b>Nota:</b> O método atual contém um bug onde ambas as condições verificam
   * o mesmo caso (resultado == NdeLados). A intenção provavelmente é que:
   * <ul>
   *   <li>resultado == NdeLados → "Golpe Brutal" (máximo)</li>
   *   <li>resultado == 1 → "Passou de raspão" (mínimo)</li>
   * </ul>
   * </p>
   * 
   * @param resultado o valor obtido na rolagem do dado de potência
   */

  public void critico(int resultado) {
    if (resultado == NdeLados) {
      System.out.println("Golpe Brutal");
    }
    else if (resultado == 1) {
      System.out.println("Passou de raspão");
    }
  } 
}
