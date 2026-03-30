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
  
  public void notificar(){
    /* chama um metodo para todos os subscribers */
    for(int i = 0; i < subscribers.size(); i++){
      subscribers.get(i).serNotificado();
    }
  }
  
  
}
