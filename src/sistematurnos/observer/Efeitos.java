package sistematurnos.observer;

import dados.Entidade;
import usaveis.cartas.Efeito;

public class Efeitos extends Subscriber{
  Entidade alvo;
  Efeito efeito;
  
  protected void serNotificado(){
    efeito.aplicar(alvo);
  }
}
