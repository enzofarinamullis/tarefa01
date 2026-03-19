package usaveis;
import dados.Inimigo;
import dados.Heroi;

public abstract class Cartas{
  private String nome;
  protected int custoEnergia;
  protected int nivel;
  protected int escudo;
  protected boolean ehDano;
  protected boolean ehEscudo;
  protected String descricao;

  public abstract void usar(Inimigo inimigo, Heroi heroi);
  public void usarEscudo(Heroi heroi){}
  
  protected void setNome(String nome){
    this.nome = nome;
  }
  
  public String getNome(){
    return nome;
  }
  
  public int getEscudo(){
    return escudo;
  }
  
  public int getNivel(){
    return nivel;
  }
  
  public int getCustoEnergia() {
    return custoEnergia;
  }
  
  public boolean isDano(){
    return this.ehDano;
  }
  
  public boolean isEscudo(){
    return this.ehEscudo;
  }
}
