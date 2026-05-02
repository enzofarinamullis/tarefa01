package usaveis.cartas;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import java.util.List;

/**
 * Classe abstrata que representa uma carta no jogo.
 *
 * <p>
 *   Cartas podem causar dano, conceder escudo ou aplicar efeitos em entidades.
 *   Cada carta possui custo de energia, nível, descrição e uma lista opcional de
 *   efeitos associados.
 * </p>
 *
 * <p>
 *   Responsabilidades:<br>
 *    - Definir o comportamento da carta ao ser usada<br>
 *    - Gerenciar efeitos aplicados ao alvo<br>
 *    - Fornecer informações para exibição ao jogador<br>
 * </p>
 *
 * <p>
 *   O método {@link #usar(Inimigo, Heroi)} deve ser implementado pelas subclasses
 *   para definir a ação específica da carta.
 * </p>
 *
 * <p>
 *   Exemplo de uso:<br>
 *   Carta carta = new espadaCurta();<br>
 *   carta.usar(inimigo, heroi);<br>
 * </p>
 */
public abstract class Carta{
  private String nome;
  protected int custoEnergia;
  protected int nivel;
  protected int escudo;
  protected boolean ehDano;
  protected boolean ehEscudo;
  protected String descricao;
  protected List<Efeito> efeitos;

  public abstract void usar(Inimigo inimigo, Heroi heroi);
  public void usarEscudo(Heroi heroi){}
  
  protected void setNome(String nome){
    this.nome = nome;
  }
  
  public String getNome(){
    return nome;
  }

  public String getDescricao(){
    return descricao;
  }
  
  public int getEscudo(){
    return escudo;
  }
  
  public int getNivel(){
    return nivel;
  }
  
  public int getCustoEnergia() {
    return custoEnergia;
  }
  
  public boolean isDano(){
    return this.ehDano;
  }
  
  public boolean isEscudo(){
    return this.ehEscudo;
  }
  public Efeito BuscaEfeito(String nome) {
    if (efeitos == null || efeitos.isEmpty()) {
      return null;
    }
    for (int i = 0; i < efeitos.size(); i++) {
      if (efeitos.get(i).getNome().equals(nome)) {
        return efeitos.get(i);
      }
    }
    return null;
  }

  public void listarEfeitos() {
    if (efeitos.isEmpty()) {
      System.out.println("Sem efeitos.");
      return;
    }

    System.out.println("Listando Efeitos da carta:");
    for (int i = 0; i < efeitos.size(); i++) {
      if( i < efeitos.size() - 1) {
        System.out.print(efeitos.get(i).getNome() + " <-> ");
      } else {
        System.out.println(efeitos.get(i).getNome());
      }
    }
  }

  public void aplicarEfeito(Entidade alvo) {
    if (!efeitos.isEmpty()) {
      for (Efeito efeito : efeitos) {
        System.out.println("O efeito " + efeito.getNome() + " foi aplicado");
        efeito.aplicar(alvo);
        }
    } else {
      return;
    }
  }

  public boolean temEfeito() {
    if (efeitos == null || efeitos.isEmpty()) {
      return false;
    }
    else {
      return true;
    }
  }
  
  public int quantidadeEfeitos(){
    return efeitos.size();
  }
  
  public Efeito retornarEfeito(int indice){
    return efeitos.get(indice);
  }
  
  public void adicionarEfeito(Efeito efeito) {
    if (efeito == null) {
      return;
    }
    efeitos.add(efeito);
  }

  public void info(int indice){
    System.out.println("\n╔═════════════════════════════════");
    System.out.println("║ " + indice + " - Carta: " + nome);
    System.out.println("╠═════════════════════════════════");
    System.out.println("║ Custo Energia: " + custoEnergia);
    System.out.println("║ Nível: " + nivel);
    System.out.println("║ Descrição: " + descricao);
        
    if (ehDano) {
      System.out.println("║ Tipo: 🔥 Dano");
    }
    if (ehEscudo) {
      System.out.println("║ Tipo: 🛡️ Escudo (" + escudo + ")");
    }
    
    if (efeitos != null && !efeitos.isEmpty()) {
      System.out.println("╠═════════════════════════════════");
      System.out.println("║ Efeitos:");
      for (Efeito e : efeitos) {
        System.out.println("║   • " + e.getNome() +
                       " (Dur: " + e.getDuracao() +
                       ", Int: " + e.getIntensidade() + ")");
        }
    }
    System.out.println("╚═════════════════════════════════");
  }
}