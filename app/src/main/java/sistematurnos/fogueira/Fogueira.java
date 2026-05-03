package sistematurnos.fogueira;

import anim.Animacao;
import anim.AnimacaoFogo;
import dados.Dados;
import dados.Heroi;

/*
 * O sistema de fogueira funcionará com o template method
 * ref: https://refactoring.guru/design-patterns/template-method
 */
public abstract class Fogueira {
  public void executar(Dados dados){
    Animacao animacaoFogueira = new AnimacaoFogo();
    animacaoFogueira.run();
    Heroi heroi = dados.heroi;
    heroi.setVida(heroi.getVida() + getVidaRecuperar());
  }

  protected abstract int getVidaRecuperar();
}
