package sistematurnos.observer;

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
        if(subscriber)
      }
    }
  }
  
  
}
