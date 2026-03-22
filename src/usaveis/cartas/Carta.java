package usaveis.cartas;
import dados.Entidade;
import dados.Heroi;
import dados.Inimigo;
import java.util.List;

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
    }

    System.out.println("Listando Efeitos da carta:");
    for (int i = 0; i < efeitos.size(); i++) {
      if( i < efeitos.size() - 1) {
        System.out.print(efeitos.get(i).getNome() + " <-> ");
      } else {
        System.out.print(efeitos.get(i).getNome());
      }
    }
  }

  public void aplicarEfeito(Entidade alvo) {
    if (!efeitos.isEmpty()) {
      for (Efeito efeito : efeitos) {
        System.out.println("O efeito " + efeito.getNome() + " foi aplicado");
        efeito.aplicar(alvo);
        }
    }
  }

  public void info(){
    System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║ Carta: " + nome);
        System.out.println("╠════════════════════════════════╣");
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
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║ Efeitos:");
            for (Efeito e : efeitos) {
                System.out.println("║   • " + e.getNome() + 
                                 " (Dur: " + e.getDuracao() + 
                                 ", Int: " + e.getIntensidade() + ")");
            }
        }
        System.out.println("╚════════════════════════════════╝");
  }
}
  





  