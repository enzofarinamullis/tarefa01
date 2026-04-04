package usaveis.cartas.efeitos;

import constantes.Turnos;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.Efeito;

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
        System.out.println("☠️ " + entidade.getNome() + " envenenado! Perdeu " + dano + " HP");
        
      }
      else if (entidade instanceof Heroi) {
        Heroi heroi = (Heroi) entidade;
        heroi.receberDano(dano);
        System.out.println("☠️ " + entidade.getNome() + " envenenado! Perdeu " + dano + " HP");

      }
    }

  }
  
  @Override
  public boolean ehEnvenamento(){
    return true;
  }
}
