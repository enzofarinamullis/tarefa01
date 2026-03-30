package sistematurnos.observer;

import dados.Entidade;
import usaveis.cartas.Efeito;

public class SubscriberEfeito extends Subscriber{
  protected Entidade alvo;
  protected Efeito efeito;
  protected int usos;
  
  public SubscriberEfeito(Entidade alvo, Efeito efeito, int idAtivacao){
    this.alvo = alvo;
    this.efeito = efeito;
    this.idAtivacao = idAtivacao;
  }
  
  protected void serNotificado(){
    efeito.aplicar(alvo);
  }
  
  protected void incrementaUso(){ this.usos++; }
  
  /**
   *  Esta função nos retornara o efeito utilizado caso seus usos acabaram
   *  assim podemos com o publisher removê-lo
   */
  protected Efeito verificaUso(){
    if(this.usos == efeito.getDuracao()){
      return efeito;
    }
  }
}
