package sistematurnos.fogueira;

import anim.Animacao;
import anim.AnimacaoFogo;
import dados.Dados;
import dados.Heroi;
import sistematurnos.Evento;

/*
 * O sistema de fogueira funcionará com o template method
 * ref: https://refactoring.guru/design-patterns/template-method
 */
public abstract class Fogueira extends Evento {
  public Fogueira(Dados dados, String nome){
    super(dados, nome, Tipo.FOGUEIRA);
  }

  public boolean executarEvento(){
    Animacao animacaoFogueira = new AnimacaoFogo();
    animacaoFogueira.run();
    Heroi heroi = dados.heroi;
    heroi.setVida(heroi.getVida() + getVidaRecuperar());
    return true;
  }

  protected abstract int getVidaRecuperar();
}
