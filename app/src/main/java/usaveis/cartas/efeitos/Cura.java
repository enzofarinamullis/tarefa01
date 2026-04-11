package usaveis.cartas.efeitos;

import constantes.Turnos;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.Efeito;

import java.nio.charset.StandardCharsets;


/**
 * Representa um efeito de cura aplicado a uma entidade
 *
 * <p>
 *   A quantidade de cura é baseada na intensidade do efeito,
 *   sendo calculada como:<br>
 *   intensidade * 2
 * </p>
 *
 * <p>
 *   Este efeito é aplicado instantaneamente e pode afetar uma ou mais entidades
 *   dependendo do alcance.
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   Cura cura = new Cura("Cura Fraca", 1, 5, 1);<br>
 *   cura.aplicar(alvo);<br>
 * </p>
 */
public class Cura extends Efeito{
  public Cura(String nome, int duracao, int intensidade, int alcance) {
    super(nome, duracao, intensidade, alcance, Turnos.INSTANTANEO);
  }
  
  public void aplicar(Entidade entidade) {
    if( entidade != null) {
      int cura = getIntensidade()*2;
      if (entidade instanceof Heroi) {
        Heroi heroi = (Heroi) entidade;
        heroi.ganharVida(cura);
        System.out.println("❤️‍🩹 " +  heroi.getNome() + " foi curado! Cura = " + cura);
      }
      else if (entidade instanceof Inimigo) {
        Inimigo inimigo = (Inimigo) entidade;
        inimigo.ganharVida(cura);
      }
      entidade.ganharVida(cura);
    }
  }
  
  /**
    * ❤️‍🩹 VERIFICA SE ESTE EFEITO É UMA CURA ❤️‍🩹
    * <p>
    *   Este método sobrescrito retorna {@code true} para indicar que esta
    *   instância representa um efeito de cura.
    */
  @Override
  public boolean ehCura(){
    return true;
  }
}
