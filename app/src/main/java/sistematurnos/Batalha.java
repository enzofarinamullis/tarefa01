package sistematurnos;
import constantes.Turnos;
import dados.Dados;
import sistematurnos.idsBatalhas.GeradorIds;

public abstract class Batalha {
  private GameManager gameManager;
  protected Dados dados;
  protected String nome;
  protected int id;
  
  public Batalha(Dados dados, String nome) {
    this.dados = dados;
    gameManager = new GameManager(dados);
    this.nome = nome;
    this.id = GeradorIds.proximoId();
  }
  
  
  protected abstract void adicionarInimigos();
  
  public boolean iniciarBatalha(){
    /* Adicionamos os inimigos necessários para cada batalha */
    adicionarInimigos();
    /* Iniciamos o turno */
    int resultado = gameManager.turno();
    /* Analisamos o resultado da batalha */
    if(resultado == Turnos.GANHOU){
      System.out.println("Ganhou");
      return true;
    }
    else if(resultado == Turnos.PERDEU){
      System.out.println("Perdeu");
      return false;
    }
    else{
      System.out.println("Fugiu");
      return true;
    }
  }
  
  /* Para a impressão do nosso mapa ficar bonita */
  @Override
  public String toString(){
    return nome + id;
  }
  
}
