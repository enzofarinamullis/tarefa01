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
      System.out.println("☠️  " + entidade.getNome() + " envenenado! Perdeu " + dano + " HP");
      entidade.receberDano(dano);
    }

  }
  
  @Override
  public boolean ehEnvenamento(){
    return true;
  }
}
