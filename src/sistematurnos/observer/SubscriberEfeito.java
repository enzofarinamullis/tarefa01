package sistematurnos.observer;

import dados.Entidade;
import usaveis.cartas.Efeito;

public class SubscriberEfeito extends Subscriber{
  Entidade alvo;
  Efeito efeito;
  
  public SubscriberEfeito(Entidade alvo, Efeito efeito){
    this.alvo = alvo;
    this.efeito = efeito;
  }
  
  protected void serNotificado(){
    efeito.aplicar(alvo);
  }
}
