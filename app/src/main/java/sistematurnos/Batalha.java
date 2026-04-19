package sistematurnos;
import constantes.Turnos;
import dados.Dados;

public abstract class Batalha {
  private GameManager gameManager;
  protected Dados dados;
  
  public Batalha(Dados dados) {
    this.dados = dados;
    gameManager = new GameManager(dados);
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
  
}
