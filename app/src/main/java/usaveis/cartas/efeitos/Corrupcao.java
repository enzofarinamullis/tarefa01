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
      System.out.println("☠️  " + entidade.getNome() + " sofreu corrupção! Perdeu " + dano + " HP");
      entidade.receberDano(dano);
     
    }
    
  }
  
  @Override
  public boolean ehCurrupcao(){
    return true;
  }
}
