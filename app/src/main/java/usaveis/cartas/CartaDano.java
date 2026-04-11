package usaveis.cartas;
import dados.Heroi;
import dados.Inimigo;
import java.util.ArrayList;
import usaveis.D.Potencia;
import usaveis.D.Precisao;

/**
 * Representa uma carta de ataque que causa dano a um inimigo
 * <p>
 *   O dano é calculado com base em dois fatores:<br>
 *    - Precisão (rolagem de d20)
 *    - Potência (baseada no nível da carta)
 * </p>
 *
 * <p>
 *   A precisão determina um multiplicador aplicado à potência,
 *   podendo resulta em dano crítico ou falha total.
 * </p>
 *
 * <p>
 *   Caso a carta tenha efeitos adicionais, estes serão aplicados
 *   após o dano ser causado.
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   CartaDano carta = new CartaDano("Ataque Feroz", 2, 3, "Um ataque poderoso que pode causar muito dano.");<br>
 *   ataque.usar(inimigo, heroi);<br>
 * </p>
 */
public class CartaDano extends Carta{

  public CartaDano(String nome, int custoEnergia, int nivel, String descricao){
    setNome(nome);
    this.custoEnergia = custoEnergia;
    this.nivel = nivel;
    this.ehDano = true;
    this.ehEscudo = false;
    this.descricao = descricao;
    this.efeitos = new ArrayList<>();
  }
  
  /**
   * Calcula o dano causado pela carta com base na precisão e potência.
   * <p>
   * A precisão é determinada por uma rolagem de d20, onde:
   * - 1: Falha crítica (dano 0)<br>
   * - 2-5: Dano normal (potência x1)<br>
   * - 6-10: Dano forte (potência x2)<br>
   * - 11-15: Dano muito forte (potência x4)<br>
   * - 16-19: Dano crítico (potência x16)<br>
   * - 20: Dano devastador (potência x64)<br>
   * </p>
   * <p>
   * A potência é calculada com base no nível da carta, onde cada nível aumenta a potência base.
   * </p>
   * <p>
   *   Exemplo de cálculo:<br>
   *   Se a precisão for 12 e a potência base for 5, o dano seria 5 x 4 = 20.<br>
   *   Se a precisão for 1, o dano seria 0, independentemente da potência.
   * </p>
   *
   * <p>
   *   Este método é chamado internamente ao usar a carta para determinar o dano final causado
   *   ao inimigo.
   * </p>
   *
   * @return O dano final calculado com base na precisão e potência.
   */
  public int calcularDano(){
    Precisao d20 = new Precisao();
    Potencia dx = new Potencia(nivel);
    int precisao = d20.rolarDado();
    if (precisao == 1) {
      System.out.println("Errou o ataque!");
      return 0;
    }
    int potencia = dx.rolarDado();
    if (precisao >= 2 && precisao <= 5) {
      return potencia;
    }
    else if (precisao >= 6 && precisao <= 10) {
      return potencia * 2;
    }
    else if (precisao >= 11 && precisao <= 15) {
      return potencia * 4;
    }
    else if (precisao >= 16 && precisao <= 19) {
      return potencia * 16;
    }
    else {
      return potencia * 64;
    }
  }
  
  
  /**
   * Usa a carta de dano, causando dano ao inimigo com base no cálculo de precisão e potência.
   * O uso da carta consome energia do herói.
   * <p>
   *   Se o herói não tiver energia suficiente, a carta não será usada e uma
   *   mensagem de aviso será exibida.
   * </p>
   *
   * @param inimigo - O inimigo alvo da carta, que receberá o dano calculado.
   * @param heroi - O herói que usará a carta e causará o dano ao inimigo.
   */
  public void usar(Inimigo inimigo, Heroi heroi){
    if (heroi.temEnergia(custoEnergia)) {
      heroi.setaEnergia(heroi.getEnergia() - custoEnergia);
      int dano = calcularDano();
      int danoRecebido = dano - inimigo.getEscudo();
      inimigo.receberDano(dano);
    }
    else{
      System.out.println("Energia Insuficiente!");
    }
  } 
}