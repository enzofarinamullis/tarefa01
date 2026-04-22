package sistematurnos.observer;

public abstract class Subscriber {
  protected int idAtivacao;
  protected int quantidadeNotificacoes;
  
  /* chamado pelo metodo de notificacao do publisher */
  abstract void serNotificado();
  
  public int getIdAtivacao(){
    return idAtivacao;
  }
  public int getQuantidadeDeNotificacoes() {
    return quantidadeNotificacoes;
  }
  public void setQuantidadeDeNotificacoes(int n) {
    this.quantidadeNotificacoes = n;
  }
  
  public boolean ehEfeito(){
    return false;
  }
  
  
}
