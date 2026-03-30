package sistematurnos.observer;

public abstract class Subscriber {
  protected int idAtivacao;
  /* chamado pelo metodo de notificacao do publisher */
  abstract void serNotificado();
  
  protected int getIdAtivacao(){
    return idAtivacao;
  }
}
