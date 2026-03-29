package usaveis.cartas;

import constantes.Cores;
import dados.Entidade;
import java.util.List;

public abstract class Efeito{
  private String nome;
  private int duracao;
  private int intensidade;
  private int alcance; // sempre vai ser ímpar (1, 3, 5, 7...)

  public Efeito(String nome, int duracao, int intensidade, int alcance) {
    this.nome = nome;
    this.duracao = duracao;
    this.intensidade = intensidade;
    this.alcance = alcance;
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
  }
  
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
  public String getNome() {
    return nome;
  }
  
  public int getDuracao() {
    return duracao;
  }
  
  public int getIntensidade() {
    return intensidade;
  }
  
  public int getAlcance() {
    return alcance;
  }
  
  // Setters (opcionais)
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public void setDuracao(int duracao) {
    this.duracao = duracao;
  }
  
  public void setIntensidade(int intensidade) {
    this.intensidade = intensidade;
  }
  
  public void setAlcance(int alcance) {
    this.alcance = alcance;
  }
}