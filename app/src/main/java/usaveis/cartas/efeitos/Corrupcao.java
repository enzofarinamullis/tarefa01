package usaveis.cartas.efeitos;

import constantes.Turnos;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.Efeito;

/**
 * Representa um efeito de corrupção aplicado a uma entidade
 *
 * <p>
 *   A corrupção causa dano instantâneo baseado na intensidade do efeito,
 *   sendo calculado como:<br>
 *   intensidade * 10
 * </p>
 *
 * <p>
 *   Este efeito é aplicado instantaneamente.
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   Corrupcao corrupcao = new Corrupcao("Corrupção", 1, 3, 1);<br>
 *   corrupcao.aplicar(alvo);<br>
 * </p>
 */
public class Corrupcao extends Efeito {
  public Corrupcao(String nome, int duracao, int intensidade, int alcance) {
    super(nome, duracao, intensidade, alcance, Turnos.INSTANTANEO);
  }
  public void aplicar(Entidade entidade) {
    if (entidade != null) {
      int dano = getIntensidade()*10;
      System.out.println("☠️  " + entidade.getNome() + " sofreu corrupção! Perdeu " + dano + " HP");
      entidade.receberDano(dano);
     
    }
    
  }
  
  @Override
  public boolean ehCurrupcao(){
    return true;
  }
}
