package sistematurnos;
import constantes.Turnos;
import dados.Dados;


public abstract class Batalha extends Evento {
  private GameManager gameManager;
  protected Dados dados;
  protected String nome;

  
  public Batalha(Dados dados, String nome) {
    super(dados, nome, Tipo.BATALHA);
    this.gameManager = new GameManager(dados);
  }

  public boolean iniciar(){
    
    int resultado = gameManager.turno();
   
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

  protected abstract void adicionarInimigos();


}
