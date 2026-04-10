package usaveis.cartas.efeitos;
import constantes.Turnos;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.Efeito;


/**
 * Representa um efeito de sangramento aplicado a uma entidade
 *
 * <p>
 *   O sangramento causa dano periódico baseado a intensidade do efeito,
 *   sendo calculado como:<br>
 *   intensidade * 3
 * </p>
 *
 * <p>
 *   Este efeito é ativado ao final do turno do jogador, causando dano contínuo
 *   enquanto durar.
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   Sangramento sangramento = new Sangramento("Sangrar", 2, 3, 1);<br>
 *   sangrameno.aplicar();<br>
 * </p>
 */
public class Sangramento extends Efeito {
  public Sangramento(String nome, int duracao, int intensidade, int alcance) {
    super(nome, duracao, intensidade, alcance, Turnos.FINAL_TURNO_JOGADOR);
  }
  public void aplicar(Entidade entidade) {
    if (entidade != null) {
      int dano = getIntensidade()*3;
      if (entidade instanceof Inimigo) {
        Inimigo inimigo = (Inimigo) entidade;
        inimigo.receberDano(dano);
        System.out.println("🩸 " + inimigo.getNome() + " sangrando! Dano: " + dano);
      }
      else if (entidade instanceof Heroi) {
        Heroi heroi = (Heroi) entidade;
        heroi.receberDano(dano);
        System.out.println("🩸 " + heroi.getNome() + " sangrando! Dano: " + dano);
      }
    }

  }
  
  @Override
  public boolean ehSangramento(){
    return true;
  }
  
}

