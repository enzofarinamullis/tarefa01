package sistematurnos.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
  List<Subscriber> subscribers;
  
  protected Publisher(){
    subscribers = new ArrayList<>();
  }
  
  private void inscrever(Subscriber adicionar){
    subscribers.add(adicionar);
  }
  
  private void desinscrever(Subscriber remover){
    subscribers.remove(remover);
  }
  
  private void notificar(){
    /* chama um metodo para todos os subscribers */
    for(int i = 0; i < subscribers.size(); i++){
      subscribers.get(i).serNotificado();
    }
  }
  
  
}
