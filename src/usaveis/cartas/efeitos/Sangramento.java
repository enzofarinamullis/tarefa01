package usaveis.cartas.efeitos;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import usaveis.cartas.Efeito;

public class Sangramento extends Efeito {
  public Sangramento(String nome, int duracao, int intensidade) {
    super(nome, duracao, intensidade);
  }
  public void aplicar(Entidade entidade) {
    if (entidade != null) {
      int dano = getIntensidade()*5;
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
}

