package sistematurnos.fogueira;

import anim.Animacao;
import anim.AnimacaoFogo;
import constantes.Dialogos;
import dados.Dados;
import dados.Heroi;
import sistematurnos.Evento;
import sistematurnos.interfaces.FogueiraTemplate;
import utilitarios.PrintTerminal;

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
    PrintTerminal.limparTerminal();
    System.out.println("Você encontrou uma fogueira");
    System.out.println("Aqui você pode descansar para recuperar sua vida guerreiro");
    PrintTerminal.pausa(Dialogos.PAUSA_MEDIA);
    Animacao animacaoFogueira = new AnimacaoFogo();
    animacaoFogueira.run();
    Heroi heroi = dados.heroi;
    heroi.setVida(heroi.getVida() + template.getVidaRecuperar());
    PrintTerminal.limparTerminal();
    System.out.println("Você recuperou " + template.getVidaRecuperar() + " de vida!");
    System.out.println("Pronto para seguir a jornada?");
    PrintTerminal.pausa(Dialogos.PAUSA_MEDIA);
    PrintTerminal.limparTerminal();
    return true;
  }
}
