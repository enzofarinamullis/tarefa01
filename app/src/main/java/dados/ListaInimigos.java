package dados;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por representar uma lista de inimigos.
 *
 * <p>
 * Esta classe encapsula uma lista de {@link Inimigo} e fornece métodos para
 * gerenciar os inimigos presentes em uma cena ou combate.
 * </p>
 * 
 * <p>
 * Responsabilidades:<br>
 * - Adicionar inimigos à lista<br>
 * - Exibir os inimigos com ou sem índice<br>
 * - Buscar um inimigo por número<br>
 * - Remover inimigos da lista<br>
 * - Obter o número total de inimigos<br>
 * </p>
 * 
 * <p>
 * Exemplo de uso:
 * <pre>
 * ListaInimigos lista = new ListaInimigos();
 * lista.adicionarInimigo(new Inimigo(...));
 * lista.mostrarInimigos();
 * </pre>
 * </p>
 * 
 * @see Inimigo
 */
public class ListaInimigos {
  
  /**
   * Lista interna que armazena todos os inimigos.
   */
  private List<Inimigo> inimigos;
  
  /**
   * Construtor padrão que inicializa uma lista vazia de inimigos.
   */
  public ListaInimigos() {
    inimigos = new ArrayList<>();
  }
  
  /**
   * Adiciona um inimigo à lista.
   * 
   * @param inimigo o inimigo a ser adicionado
   */
  public void adicionarInimigo(Inimigo inimigo) {
    inimigos.add(inimigo);
  }
  
  /**
   * Exibe todos os inimigos da lista com seus respectivos índices numerados.
   * 
   * <p>O índice de cada inimigo é mostrado antes de suas estatísticas,
   * facilitando a seleção do inimigo pelo jogador durante o combate.</p>
   * 
   * <p>Formato de exibição: "1 [estatísticas do inimigo 1]"</p>
   * 
   * @see Inimigo#printStats()
   */
  public void mostrarInimigos() {
    int indice = 0;
    Inimigo atual;
    for(int i = 0; i < inimigos.size(); i++) {
      indice = i + 1;
      System.out.print(indice + " ");
      atual = inimigos.get(i);
      atual.printStats();
    }
  }

  /**
   * Exibe todos os inimigos da lista sem mostrar seus índices.
   * 
   * <p>Apenas as estatísticas de cada inimigo são impressas no console,
   * sem numeração associada.</p>
   * 
   * @see Inimigo#printStats()
   */
  public void printInimigosSemIndice() {
    Inimigo atual;
    for(int i = 0; i < inimigos.size(); i++) {
      atual = inimigos.get(i);
      atual.printStats();
    }
  }

  /**
   * Busca e retorna um inimigo da lista pelo número de ordem (1-based).
   * 
   * <p>O parâmetro {@code numero} representa a posição do inimigo na lista
   * começando em 1. Internamente, converte para índice 0-based da ArrayList.</p>
   * 
   * @param numero número de ordem do inimigo (1 = primeiro inimigo, 2 = segundo, etc.)
   * @return o inimigo na posição especificada
   * @throws IndexOutOfBoundsException se o número estiver fora dos limites da lista
   */
  public Inimigo buscarInimigo(int numero) {
    int indice = numero - 1;
    return inimigos.get(indice);
  }
  
  public void limparListaInimigos(){
    while(!inimigos.isEmpty()){
      inimigos.removeFirst();
    }
  }

  /**
   * Remove um inimigo específico da lista.
   * 
   * @param inimigoRemover o inimigo a ser removido
   * @return {@code true} se o inimigo foi removido com sucesso,
   *         {@code false} caso o inimigo não estivesse na lista
   * @see List#remove(Object)
   */
  public void removerInimigo(Inimigo inimigoRemover) {
    inimigos.remove(inimigoRemover);
  }
  
  /**
   * Retorna o número total de inimigos na lista.
   * 
   * @return quantidade de inimigos armazenados
   */
  public int getTamanho() {
    return inimigos.size();
  }
}