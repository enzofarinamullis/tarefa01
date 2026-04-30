package sistematurnos;
import dados.Dados;

public abstract class evento {
  protected GameManager gameManager;
  protected Dados dados;
  protected String nome;
  protected Tipo tipo;
  protected boolean esta_concluido;

  protected  enum Tipo { BATALHA, LOJA, JOGO, DIALOGO, INDEFINIDO};

  public evento(Dados dados, String nome, Tipo tipo) {
    this.dados = dados;
    this.nome = nome;
    gameManager = new GameManager(dados);
    this.tipo = tipo;
    this.esta_concluido = false;
  }

  public abstract void iniciar();
  public abstract boolean ver_se_esta_concluido();
  public String getNome() {
    return nome;
  }
  public Tipo getTipo() {
    return tipo;
  }



}