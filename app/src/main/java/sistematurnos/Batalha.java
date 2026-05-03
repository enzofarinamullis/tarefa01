package sistematurnos;
import constantes.Turnos;
import dados.Dados;


public abstract class Batalha extends Evento {
  protected String nome;

  
  public Batalha(Dados dados, String nome) {
    super(dados, nome, Tipo.BATALHA);
  }

  public boolean executarEvento(){

    adicionarInimigos();
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
