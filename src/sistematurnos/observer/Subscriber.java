package sistematurnos.observer;

public abstract class Subscriber {
  protected int idAtivacao;
  protected int quantidadeNotificacoes;
  
  /* chamado pelo metodo de notificacao do publisher */
  abstract void serNotificado();
  
  protected int getIdAtivacao(){
    return idAtivacao;
  }
  
  protected boolean ehEfeito(){
    return false;
  }
  
  
}
