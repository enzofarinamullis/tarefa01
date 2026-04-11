package sistematurnos.observer;

import constantes.IdsSubscribers;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
  List<Subscriber> subscribers;
  
  public Publisher(){
    subscribers = new ArrayList<>();
  }
  
  public void inscrever(Subscriber adicionar){
    subscribers.add(adicionar);
  }
  
  public void desinscrever(Subscriber remover){
    subscribers.remove(remover);
  }
  
  public void notificar(int idAtivacao){
    /* chama um metodo para todos os subscribers com o id de ativacao especificado */
    Subscriber subscriber;
    List<Subscriber> acabaram = new ArrayList<>();
    for(int i = 0; i < subscribers.size(); i++){
      subscriber = subscribers.get(i);
      if(subscriber.getIdAtivacao() == idAtivacao) {
        subscriber.serNotificado();
        pausa(1500);
      }
    }
  }
  
  private void pausa(long milissegundos){
    try {
      Thread.sleep(milissegundos);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public List<SubscriberEfeito> getSubscribersEfeitos(){
    List<SubscriberEfeito> subscriberEfeitos = new ArrayList<>();
    Subscriber subscriber;
    for(int i = 0; i < subscribers.size(); i++){
      subscriber = subscribers.get(i);
      if(subscriber.ehEfeito()) {
        /* fazemos um cast para especificar que sera um efeito */
        subscriberEfeitos.add((SubscriberEfeito) subscriber);
      }
    }
    return subscriberEfeitos;
  }
}
