package usaveis.cartas.efeitos;

import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import java.util.List;
import javax.crypto.NullCipher;
import usaveis.cartas.Efeito;

public class Cura extends Efeito {
  public Cura(String nome, int duracao, int intensidade) {
    super(nome, duracao, intensidade);
  }
  
  public void aplicar(Entidade entidade) {
    if( entidade != null) {
      int cura = getIntensidade()*6;
      if (entidade instanceof Heroi) {
        Heroi heroi = (Heroi) entidade;
        heroi.ganharVida(cura);
      }
      else if (entidade instanceof Inimigo) {
        Inimigo inimigo = (Inimigo) entidade;
        inimigo.ganharVida(cura);
      }
    }
  }
}
