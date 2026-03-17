package usaveis;
import dados.Inimigo;
import dados.Heroi;

public class Cartas{
  public String nome;
  public int custoEnergia;
  public int nivel;
  public int escudo;
  public Cartas prox;
  public boolean ehDano;
  public boolean ehEscudo;
  public String descricao;

  public void usar(Inimigo inimigo, Heroi heroi){}
  public void usarEscudo(Heroi heroi){}
}
