package sistematurnos;
import dados.Dados;

public abstract class evento {
  protected GameManager gameManager;
  protected Dados dados;
  protected String nome;
  protected Tipo tipo;
  protected boolean esta_concluido;

  protected  enum Tipo { BATALHA, LOJA, JOGO, DIALOGO, FOGUEIRA, INDEFINIDO};

  public evento(Dados dados, String nome, Tipo tipo) {
    this.dados = dados;
    this.nome = nome;
    gameManager = new GameManager(dados);
    this.tipo = tipo;
    this.esta_concluido = false;
  }

  public abstract boolean iniciar();

  public String getNome() {
    return nome;
  }




}