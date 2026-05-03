package sistematurnos;
import dados.Dados;

import java.util.List;

public abstract class Evento {
  protected GameManager gameManager;
  protected Dados dados;
  protected String nome;
  protected Tipo tipo;
  protected boolean esta_concluido;

  protected  enum Tipo { BATALHA, LOJA, JOGO, DIALOGO, FOGUEIRA, INDEFINIDO};

  public Evento(Dados dados, String nome, Tipo tipo) {
    this.dados = dados;
    this.nome = nome;
    gameManager = new GameManager(dados);
    this.tipo = tipo;
    this.esta_concluido = false;
  }

  public interface Strategy{
    boolean excecutarEvento();
  }

  public abstract boolean executarEvento();

  public String getNome() {
    return nome;
  }

  public String toString(){
    return nome;
  }
}