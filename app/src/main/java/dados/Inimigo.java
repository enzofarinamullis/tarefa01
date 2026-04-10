package dados;
import java.util.ArrayList;
import java.util.List;

import constantes.Cores;
import usaveis.cartas.Efeito;


/**
 * Representa uma entidade inimiga.
 *
 * <p>
 *   Estende{@link Entidade} e adiciona comportamentos específicos de um inimigo
 *  como o anúncio de ataque e aplicação de efeitos no herói.
 * </p>
 * <p>
 *   Responsabilidades:
 *    - Gerenciar o dano recebido com lógica de escudo
 *    - Exibir mensagens de combate no terminal
 *    - Aplicar efeitos ao herói
 * </p>
 * <p>
 *   Cada inimigo pode possuir uma lista de {@link Efeito} que são aplicados
 *  durante o combate, cada efeito terá um momento de atuação durante o combate.
 * </p>
 * <p>
 *   Exemplo de uso:<br>
 *   Inimigo Slime = new Inimigo("Slime Selvagem", 30, 10, 1, "morra seu fedelho miserável!");<br>
 *   slime.anunciar();
 * </p>
 */
public class Inimigo extends Entidade {
  protected String anuncio;
  protected String ASCII;
  protected List<Efeito> listaEfeitos;
  
  /* Construtor */
  public Inimigo(String nome, int vida, int escudo, int dano, String anuncio) {
    setNome(nome);
    this.vida = vida;
    this.escudo = escudo;
    this.dano = dano;
    this.id = -1; // colocamos como -1 para quando nao tiver sido fornecido um id (ainda)
    this.anuncio = anuncio;
    this.listaEfeitos = new ArrayList<>();
  }
  
  @Override
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

  public void usarEfeitoHeroi(Heroi heroi){
    Efeito efeito;
    if(!listaEfeitos.isEmpty()){
      for(int i = 0; i < listaEfeitos.size(); i++){
        efeito = listaEfeitos.get(i);
        efeito.aplicar(heroi);
      }
    }
  }
  
  public String getCaminho(){
    return ASCII;
  }
}
