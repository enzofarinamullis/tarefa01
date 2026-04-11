package usaveis.cartas;

import constantes.Cores;
import dados.Entidade;
import dados.Heroi;
import java.util.List;

/**
 * Classe abstrata que representa um efeito aplicável as entidades do jogo
 *
 * <p>
 *   Um efeito pode representar ações como dano, cura, veneno, sangramento,
 *   buff ou debuffs, podendo ser aplicado em um único alvo, em área ou em todos os alvos.
 * </p>
 *
 * <p>
 *   Cada efeito possuí:<br>
 *    - Nome identificador
 *    - Duração (acúmulos)
 *    - Intensidade (força do efeito)
 *    - Alcance (quantidade de alvos afetados)
 *    - ID de ativação (momento do combate que o efeito é aplicado)
 * </p>
 *
 * <p>
 *   O método {@link #aplicar(Entidade)} deve ser implementado pelas subclasses
 *   para definir o comportamento específico do efeito.
 * </p>
 *
 * <p>
 *   Regras:<br>
 *    - O alcance deve ser sempre um número ímpar (1, 3, 5, ...)<br>
 *    - O alvo central é usado como referência para efeitos em área<br>
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   Efeito envenenamento = new Envenenamento(...);
 *   envenenamento.aplicar(alvo);
 * </p>
 */
public abstract class Efeito{
  private String nome;
  private String dono;
  private int acumulos;
  private int intensidade;
  private int idAtivacao;
  private int alcance; // sempre vai ser ímpar (1, 3, 5, 7...)

  public Efeito(String nome, int duracao, int intensidade, int alcance, int idAtivacao) {
    this.nome = nome;
    this.acumulos = duracao;
    this.intensidade = intensidade;
    this.alcance = alcance;
    this.idAtivacao = idAtivacao;
  }
  
  public int getIdAtivacao() {
    return idAtivacao;
  }
  
  // Método abstrato para aplicar em um único alvo
  public abstract void aplicar(Entidade entidade);
  
  // Método para aplicar efeito em área baseado no alcance
  public void aplicarEmArea(List<Entidade> entidades, Entidade alvo) {
    if (entidades == null || entidades.isEmpty() || alvo == null) {
      return;
    }
    
    int indiceAlvo = entidades.indexOf(alvo);
    if (indiceAlvo == -1) {
      System.out.println("⚠️ Alvo não encontrado na lista!");
      return;
    }
    
    int raio = (alcance - 1) / 2;  // Quantos alvos para cada lado
    int inicio = Math.max(0, indiceAlvo - raio);
    int fim = Math.min(entidades.size() - 1, indiceAlvo + raio);
    
    System.out.println();
    Cores.cprint(Cores.ANSI_PURPLE, "✨ Aplicando ");
    Cores.cprint(Cores.ANSI_CYAN, getNome());
    Cores.cprint(Cores.ANSI_PURPLE, " em área (alcance: ");
    Cores.cprintInt(Cores.ANSI_YELLOW, alcance);
    Cores.cprintn(Cores.ANSI_PURPLE, ")!");
    
    System.out.println();
    Cores.cprint(Cores.ANSI_WHITE, "   Alvo central: ");
    Cores.cprint(Cores.ANSI_YELLOW, alvo.getNome());
    System.out.println();
    Cores.cprint(Cores.ANSI_WHITE, "   Afetados: ");
    
    // Aplica o efeito em todos os alvos dentro do alcance
    for (int i = inicio; i <= fim; i++) {
      Entidade entidade = entidades.get(i);
      aplicar(entidade);
      
      if (i != indiceAlvo) {
        Cores.cprint(Cores.ANSI_CYAN, "     ↳ ");
        Cores.cprint(Cores.ANSI_YELLOW, entidade.getNome());
        Cores.cprintn(Cores.ANSI_WHITE, " também foi afetado!");
      }
    }
    
    System.out.println();
    Cores.cprint(Cores.ANSI_GREEN, "   ✅ Efeito aplicado a ");
    Cores.cprintInt(Cores.ANSI_GREEN, (fim - inicio + 1));
    Cores.cprintn(Cores.ANSI_GREEN, " alvos!");
  };
  
  // Método alternativo usando índice diretamente
  public void aplicarEmArea(List<Entidade> entidades, int indiceAlvo) {
    if (indiceAlvo >= 0 && indiceAlvo < entidades.size()) {
      aplicarEmArea(entidades, entidades.get(indiceAlvo));
    }
  }
  
  // Método para aplicar efeito em todos os alvos
  public void aplicarEmTodos(List<Entidade> entidades) {
    if (entidades == null || entidades.isEmpty()) return;
    
    System.out.println();
    Cores.cprint(Cores.ANSI_PURPLE, "✨ Aplicando ");
    Cores.cprint(Cores.ANSI_CYAN, getNome());
    Cores.cprintn(Cores.ANSI_PURPLE, " em TODOS os alvos!");
    
    for (Entidade entidade : entidades) {
      aplicar(entidade);
    }
  }
  
  // Getters
  
  /**
   * Retorna o nome do efeito, que serve como identificador para o tipo de efeito.
   * @return O nome do efeito
   */
  public String getNome() {
    return nome;
  }
  
  /**
   * Retorna a duração do efeito em acúmulos, indicando quantas vezes o efeito será aplicado
   * @return A duração do efeito em acúmulos
   */
  public int getDuracao() {
    return acumulos;
  }
  
  
  /**
   * Retorna a intensidade do efeito, que representa a força ou magnitude do efeito aplicado.
   * @return A intensidade do efeito
   */
  public int getIntensidade() {
    return intensidade;
  }
  
  public int getAlcance() {
    return alcance;
  }
  
  // Setters
  
  /**
   * Define o nome do efeito, que serve como identificador para o tipo de efeito.
   * @param nome O nome do efeito a ser definido
   */
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  /**
   * Define a duração do efeito em acúmulos, indicando quantas vezes o efeito será aplicado.
   * @param duracao A duração do efeito em acúmulos a ser definida
   */
  public void setDuracao(int duracao) {
    this.acumulos = duracao;
  }
  
  /**
   * Define a intensidade do efeito, que representa a intensidade do efeito aplicado.
   * @param intensidade A intensidade do efeito a ser definida
   */
  public void setIntensidade(int intensidade) {
    this.intensidade = intensidade;
  }
  
  public void setAlcance(int alcance) {
    this.alcance = alcance;
  }
  
  /**
   * Indica se o efeito é do tipo cura, permitindo que as subclasses de efeitos de cura
   * sobrescrevam este método para retornar true, facilitando a identificação do tipo de efeito
   * @return true se o efeito for do tipo cura, false caso contrário
   */
  public boolean ehCura(){
    return false;
  }
  
  /**
   * Indica se o efeito é do tipo envenenamento, permitindo que as subclasses de efeitos de envenenamento
   * sobrescrevam este método para retornar true, facilitando a identificação do tipo de efeito
   * @return true se o efeito for do tipo envenenamento, false caso contrário
   */
  public boolean ehEnvenamento(){
    return false;
  }
  
  
  /**
   * Indica se o efeito é do tipo sangramento, permitindo que as subclasses de efeitos de sangramento
   * sobrescrevam este método para retornar true, facilitando a identificação do tipo de efeito
   * @return true se o efeito for do tipo sangramento, false caso contrário
   */
  public boolean ehSangramento(){
    return false;
  }
  
  /**
   * Indica se o efeito é do tipo corrupção, permitindo que as subclasses de efeitos de corrupção
   * sobrescrevam este método para retornar true, facilitando a identificação do tipo de efeito
   * @return true se o efeito for do tipo corrupção, false caso contrário
   */
  public boolean ehCurrupcao(){
    return false;
  }
}