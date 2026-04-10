package usaveis.cartas.efeitos;

import constantes.Turnos;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.Efeito;

/**
 * Representa um efeito de envenenamento aplicado a uma entidade
 *
 * <p>
 *   O envenenamento causa dano periódico baseado na intensidade do efeito,
 *   sendo calculado como:<br>
 *   intensidade * 2
 * </p>
 *
 * <p>
 *    Este efeito é ativado no início do turno do jogador, causando dano contínuo
 *    enquanto durar.
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   Envenenamento veneno = new Envenenamento("Veneno", 3, 2, 1);<br>
 *   veneno.aplicar(alvo);<br>
 * </p>
 */
public class Envenenamento extends Efeito {
  public Envenenamento(String nome, int duracao, int intensidade, int alcance) {
    super(nome, duracao, intensidade, alcance, Turnos.INICIO_TURNO_JOAGADOR);
  }
  public void aplicar(Entidade entidade) {
    if (entidade != null) {
      int dano = getIntensidade()*2;
      if (entidade instanceof Inimigo) {
        Inimigo inimigo = (Inimigo) entidade;
        inimigo.receberDano(dano);
        System.out.println("💀 " + inimigo.getNome() + " sangrando! Dano: " + dano);
      }
      else if (entidade instanceof Heroi) {
        Heroi heroi = (Heroi) entidade;
        heroi.receberDano(dano);
        System.out.println("💀 " + heroi.getNome() + "envenenado! Dano: " + dano);
      }
      entidade.receberDano(dano);
      System.out.println("☠️ " + entidade.getNome() + " envenenado! Perdeu " + dano + " HP");
    }

  }
  
  
  /**
    * 💀 VERIFICA SE ESTE EFEITO É UM ENVENENAMENTO 💀
    * <p>
    *   Este método sobrescrito retorna {@code true} para indicar que esta
    *   instância representa um efeito de envenenamento.
    */
  public boolean ehEnvenenamento(){
    return true;
  }

}
  