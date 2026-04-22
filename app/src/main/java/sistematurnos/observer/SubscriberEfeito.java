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
    usos = 0;
  }
  
  protected void serNotificado(){
    if(alvo != null) {
      efeito.aplicar(alvo);
      usos++;
    }
  }
  
  public void matarEfeito(Entidade alvo){
    if(alvo == this.alvo) {
      alvo = null;
    }
  }
  
  /**
   *  Esta função nos retornara o efeito utilizado caso seus usos acabaram
   *  assim podemos com o publisher removê-lo com o publisher
   */
  public boolean acabou(){
    if(efeito.getDuracao() < 0){
      return false;
    }
    return this.usos >= efeito.getDuracao();
  }
  
  @Override
  public boolean ehEfeito(){
    return true;
  }

  public int getUsos() {
    return usos;
  }
  public void setUsos(int usos) {
    this.usos = usos;
  }
  public Entidade getAlvo(){
    return alvo;
  }
}
