package dados;
import sistematurnos.SistemaRecompensas;
import sistematurnos.interfaces.RecompensaBasica;
import sistematurnos.observer.Publisher;

/**
 * Classe responsável por armazenar o estado global do jogo durante as cenas e combate
 *
 * <p>
 * Contém referências às principais entidades ativas, como herói e lista de inimigos,
 * além de classes auxiliares utilizadas ao longo do jogo (como {@link Publisher}).
 * </p>
 *
 * <p>
 * Responsabilidades:<br>
 *  - Armazenar o herói atual<br>
 *  - Gerenciar os inimigos da cena<br>
 *  - Fornecer acesso ao sistema de eventos ({@link Publisher})<br>
 *  - Controlar o estado temporal da cena (frame)<br>
 * </p>
 *
 * <p>
 * O {@link Publisher} é mantido aqui para permitir que efeitos persistam
 * entre diferentes momentos do combate.
 * </p>
 *
 * <p>
 * Exemplo de uso:<br>
 * Dados dados = new Dados(new Heroi());<br>
 * dados.listaInimigos.adicionar(new Inimigo(...));
 * </p>
 */
public class Dados {
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
