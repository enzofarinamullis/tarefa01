package dados;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por representar uma lista de inimigos.
 *
 * <p>
 *   Esta classe encapsula uma lista de {@link Inimigo} e fornece métodos para
 *   gerenciar os inimigos presentes em uma cena ou combate.
 * </p>
 * <p>
 *   Responsabilidades:<br>
 *   - Adicionar inimigos à lista<br>
 *   - Exibir os inimigos com ou sem índice<br>
 *   - Buscar um inimigo por número<br>
 *   - Remover inimigos da lista<br>
 *   - Obter o número total de inimigos<br>
 * </p>
 * <p>
 *   Exemplo de uso:<br>
 *   ListaInimigos lista = new ListaInimigos();<br>
 *   lista.adicionarInimigo(new Inimigo(...));<br>
 *   lista.mostrarInimigos();<br>
 * </p>
 */
public class ListaInimigos {
  private List<Inimigo> inimigos;
  
  public ListaInimigos(){
    inimigos = new ArrayList<>();
  }
  
  public void adicionarInimigo(Inimigo inimigo){
    inimigos.add(inimigo);
  }
  
  public void mostrarInimigos(){
    int indice = 0;
    Inimigo atual;
    for(int i = 0; i < inimigos.size(); i++){
      indice = i + 1;
      System.out.print(indice + " ");
      atual = inimigos.get(i);
      atual.printStats();
    }
  }

  public void printInimigosSemIndice(){
    Inimigo atual;
    for(int i = 0; i < inimigos.size(); i++){
      atual = inimigos.get(i);
      atual.printStats();
    }
  }

  public Inimigo buscarInimigo(int numero){
    int indice = numero - 1;
    return inimigos.get(indice);
  }

  public void removerInimigo(Inimigo inimigoRemover){
    inimigos.remove(inimigoRemover);
  }
  
  public int getTamanho(){
    return inimigos.size();
  }
}
