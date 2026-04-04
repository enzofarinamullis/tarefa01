package dados;
import sistematurnos.observer.Publisher;

public class Dados {
  /* Nesta classe de dados, salvaremos todos os dados necessarios */
  /* para uma Cena, ou seja... qntInimigos, e o proprio Heroi que */
  /* estara em todas as cenas */

  // para adicionar os inimigos, podemos trabalhar com uma lista!
  // onde cada inimigo sera um elemento da lista!
  public Heroi heroi;
  public ListaInimigos listaInimigos;
  /* colocamos o publisher em dados, pois queremos armazenar os efeitos para alem do combate */
  private Publisher publisher;
  public int frame;

  public Dados(Heroi heroi){
    this.heroi = heroi;
    this.listaInimigos = new ListaInimigos();
    this.frame = 0;
    this.publisher = new Publisher();
  }
  
  public Publisher getPublisher(){
    return publisher;
  }
}
