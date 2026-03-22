package dados;
import constantes.Cores;
import java.util.ArrayList;
import java.util.List;

public class ListaAliados {
  private List<Aliado> aliados;

  public ListaAliados() {
    aliados = new ArrayList<>();
  }

   public void adicionarAliado(Aliado aliado) {
    if (aliado != null) {
      aliados.add(aliado);
      Cores.cprint(Cores.ANSI_GREEN, "✅ ");
      Cores.cprint(Cores.ANSI_YELLOW, aliado.getNome());
      Cores.cprintn(Cores.ANSI_GREEN, " Lutará contigo!");
    }
  }
  
  public boolean removerAliado(String nome) {
    for (int i = 0; i < aliados.size(); i++) {
      if (aliados.get(i).getNome().equalsIgnoreCase(nome)) {
        Aliado removido = aliados.remove(i);
        Cores.cprint(Cores.ANSI_RED, "❌ ");
        Cores.cprint(Cores.ANSI_YELLOW, removido.getNome());
        Cores.cprintn(Cores.ANSI_RED, " Não irá mais lutar contigo!");
        return true;
      }
    }
    Cores.cprint(Cores.ANSI_RED, "⚠️ Aliado ");
    Cores.cprint(Cores.ANSI_YELLOW, nome);
    Cores.cprintn(Cores.ANSI_RED, " não encontrado!");
    return false;
  }
  
  public Aliado removerAliado(int indice) {
    if (indice >= 0 && indice < aliados.size()) {
      Aliado removido = aliados.remove(indice);
      Cores.cprint(Cores.ANSI_RED, "❌ ");
      Cores.cprint(Cores.ANSI_YELLOW, removido.getNome());
      Cores.cprintn(Cores.ANSI_RED, " deixou o grupo!");
      return removido;
    }
    return null;
  }
  
  public Aliado buscarAliado(String nome) {
    for (Aliado aliado : aliados) {
      if (aliado.getNome().equalsIgnoreCase(nome)) {
        return aliado;
      }
    }
    return null;
  }
  
  public Aliado getAliado(int indice) {
    if (indice >= 0 && indice < aliados.size()) {
      return aliados.get(indice);
    }
    return null;
  }

   public boolean todosVivos() {
    for (Aliado aliado : aliados) {
      if (!aliado.estaVivo()) {
        return false;
      }
    }
    return true;
  }
  
  public boolean temVivos() {
    for (Aliado aliado : aliados) {
      if (aliado.estaVivo()) {
        return true;
      }
    }
    return false;
  }
  
  public int contarVivos() {
    int vivos = 0;
    for (Aliado aliado : aliados) {
      if (aliado.estaVivo()) {
        vivos++;
      }
    }
    return vivos;
  }
  
  public int contarMortos() {
    return aliados.size() - contarVivos();
  }

   public void listarAliados() {
    if (aliados.isEmpty()) {
      Cores.cprintn(Cores.ANSI_YELLOW, "📋 Nenhum aliado no grupo.");
      return;
    }
    
    System.out.println();
    Cores.cprint(Cores.ANSI_CYAN, "╔══════════════════════════════════════════════════════════════╗");
    System.out.println();
    Cores.cprint(Cores.ANSI_CYAN, "║                    ");
    Cores.cprint(Cores.ANSI_YELLOW, "LISTA DE ALIADOS");
    Cores.cprint(Cores.ANSI_CYAN, " (" + aliados.size() + ")                    ║");
    System.out.println();
    Cores.cprint(Cores.ANSI_CYAN, "╠══════════════════════════════════════════════════════════════╣");
    System.out.println();
    
    for (int i = 0; i < aliados.size(); i++) {
      Aliado a = aliados.get(i);
      String status = a.estaVivo() ? "❤️ VIVO" : "💀 MORTO";
      Cores.cprint(Cores.ANSI_CYAN, "║ ");
      Cores.cprint(Cores.ANSI_WHITE, String.format("%2d", i + 1));
      Cores.cprint(Cores.ANSI_CYAN, ". ");
      Cores.cprint(Cores.ANSI_YELLOW, String.format("%-12s", a.getNome()));
      Cores.cprint(Cores.ANSI_CYAN, " | ");
      
      if (a.estaVivo()) {
        Cores.cprint(Cores.ANSI_GREEN, status);
      } else {
        Cores.cprint(Cores.ANSI_RED, status);
      }
      
      Cores.cprint(Cores.ANSI_CYAN, " | ");
      Cores.cprint(Cores.ANSI_RED, String.format("%3d", a.getVida()));
      Cores.cprint(Cores.ANSI_CYAN, "/");
      Cores.cprint(Cores.ANSI_CYAN, " HP | ");
      Cores.cprint(Cores.ANSI_BLUE, String.format("%2d", a.getEnergia()));
      Cores.cprint(Cores.ANSI_CYAN, " EN ║");
      System.out.println();
    }
    
    Cores.cprint(Cores.ANSI_CYAN, "╚══════════════════════════════════════════════════════════════╝");
    System.out.println();
  }
  
  public void listarAliadosVivos() {
    System.out.println();
    Cores.cprint(Cores.ANSI_GREEN, "❤️ ALIADOS VIVOS:");
    System.out.println();
    
    int contador = 0;
    for (Aliado aliado : aliados) {
      if (aliado.estaVivo()) {
        contador++;
        Cores.cprint(Cores.ANSI_WHITE, "   " + contador + ". ");
        Cores.cprint(Cores.ANSI_YELLOW, aliado.getNome());
        Cores.cprint(Cores.ANSI_WHITE, " | ");
        System.out.print(aliado.getVida());
        Cores.cprint(Cores.ANSI_WHITE, " HP | ");
        System.out.print(aliado.getEnergia());
        Cores.cprintn(Cores.ANSI_WHITE, " EN");
      }
    }
    
    if (contador == 0) {
      Cores.cprintn(Cores.ANSI_RED, "   Nenhum aliado vivo!");
    }
  }
  
  public void listarAliadosMortos() {
    System.out.println();
    Cores.cprint(Cores.ANSI_RED, "💀 ALIADOS MORTOS:");
    System.out.println();
    
    int contador = 0;
    for (Aliado aliado : aliados) {
      if (!aliado.estaVivo()) {
        contador++;
        Cores.cprint(Cores.ANSI_WHITE, "   " + contador + ". ");
        Cores.cprint(Cores.ANSI_YELLOW, aliado.getNome());
        Cores.cprintn(Cores.ANSI_RED, " 💀");
      }
    }
    
    if (contador == 0) {
      Cores.cprintn(Cores.ANSI_GREEN, "   Nenhum aliado morto!");
    }
  }
  
  public void listarAliadosResumido() {
    if (aliados.isEmpty()) {
      Cores.cprintn(Cores.ANSI_YELLOW, "📋 Nenhum aliado no grupo.");
      return;
    }
    
    System.out.print("[");
    for (int i = 0; i < aliados.size(); i++) {
      Aliado a = aliados.get(i);
      if (a.estaVivo()) {
        Cores.cprint(Cores.ANSI_GREEN, a.getNome());
      } else {
        Cores.cprint(Cores.ANSI_RED, a.getNome());
      }
      if (i < aliados.size() - 1) {
        System.out.print(", ");
      }
    }
    Cores.cprintn(Cores.ANSI_WHITE, "]");
  }
}