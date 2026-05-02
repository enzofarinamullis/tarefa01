package sistematurnos;
import constantes.Turnos;
import dados.Dados;
import dados.inimigos.*;
import dados.Inimigo;
import dados.ListaInimigos;
import java.util.ArrayList;
import java.util.List;


public class Batalha extends evento {
  private GameManager gameManager;
  protected Dados dados;
  protected String nome;
  protected ListaInimigos listaDeInimigos;
  
  
  public Batalha(Dados dados, String nome, ListaInimigos inimigos) {
    super(dados, nome, Tipo.BATALHA);
    this.gameManager = new GameManager(dados);
    this.listaDeInimigos = inimigos;
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
  
  public String toString(){
    return " - " + nome;
  }
}
