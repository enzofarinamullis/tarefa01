package usaveis.cartas.efeitos;

import constantes.Turnos;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.Efeito;

public class Corrupcao extends Efeito {
  public Corrupcao(String nome, int duracao, int intensidade, int alcance) {
    super(nome, duracao, intensidade, alcance, Turnos.INSTANTANEO);
  }
  public void aplicar(Entidade entidade) {
    if (entidade != null) {
      int dano = getIntensidade()*10;
      if (entidade instanceof Inimigo) {
        Inimigo inimigo = (Inimigo) entidade;
        inimigo.receberDano(dano);
        System.out.println("☠️ " + entidade.getNome() + " sofreu corrupção! Perdeu " + dano + " HP");
        
      }
      else if (entidade instanceof Heroi) {
        Heroi heroi = (Heroi) entidade;
        heroi.receberDano(dano);
        System.out.println("☠️ " + entidade.getNome() + " sofreu corrupção! Perdeu " + dano + " HP");
        
      }
    }
    
  }
  
  @Override
  public boolean ehCurrupcao(){
    return true;
  }
}
