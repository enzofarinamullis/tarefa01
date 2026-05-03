package sistematurnos.fogueira;

import anim.Animacao;
import anim.AnimacaoFogo;
import dados.Dados;
import dados.Heroi;
import sistematurnos.Evento;
import sistematurnos.interfaces.FogueiraTemplate;

/*
 * O sistema de fogueira funcionará com o template method
 * ref: https://refactoring.guru/design-patterns/template-method
 */
public class Fogueira extends Evento {
  FogueiraTemplate template;

  public Fogueira(Dados dados, String nome, FogueiraTemplate template){
    super(dados, nome, Tipo.FOGUEIRA);
    this.template = template;
  }

  public boolean executarEvento(){
    Animacao animacaoFogueira = new AnimacaoFogo();
    animacaoFogueira.run();
    Heroi heroi = dados.heroi;
    heroi.setVida(heroi.getVida() + template.getVidaRecuperar());
    return true;
  }
}
