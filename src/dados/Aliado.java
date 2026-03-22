package dados;
import constantes.Cores;

public class Aliado extends Entidade {
  protected String anuncio;
  public Aliado(String nome, int vida , int escudo, int dano, String anuncio) {
    setNome(nome);
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
    this.id = -1; // colocamos como -1 para quando nao tiver sido fornecido um id (ainda)
    this.anuncio = anuncio;
  }
    public void receberDano(int dano) {
        
    if (escudo > 0){
      int dano_no_escudo = Math.min(escudo, dano);
      escudo -= dano_no_escudo;
      dano -= dano_no_escudo;
      System.out.print("O ");
      Cores.cprint(Cores.ANSI_BLUE, "escudo");
      System.out.print(" absorveu ");
      Cores.cprintInt(Cores.ANSI_RED ,dano_no_escudo);
      Cores.cprintn(Cores.ANSI_RED, " de dano.");

    }
    Cores.cprint(Cores.ANSI_YELLOW, getNome());
    System.out.println(" recebeu ");
    Cores.cprintInt(Cores.ANSI_RED, dano);
    System.out.print(" de dano.\n");

    vida -= dano;
    if (vida <= 0) {
      Cores.cprint(Cores.ANSI_YELLOW, getNome());
      Cores.cprintn(Cores.ANSI_RED, " morreu!");
      vida = 0;
    }
    else{
      Cores.cprint(Cores.ANSI_YELLOW, getNome());
      System.out.print(" tem "); 
      Cores.cprintInt(Cores.ANSI_GREEN, vida);
      System.out.print(" de vida.\n");
    }
  }

  public String getAnuncio(){
    return anuncio;
  }

   public void anunciar(){
    System.out.println();
    System.out.println("Anúncio:");
    System.out.println(Cores.ANSI_RED + getNome() + ": " +
      Cores.ANSI_BLUE + getAnuncio() + Cores.ANSI_RESET);
  }
  
}
