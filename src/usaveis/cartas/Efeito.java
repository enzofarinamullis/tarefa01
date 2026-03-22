package usaveis.cartas;

import dados.Entidade;
import java.util.List;

public abstract class Efeito {
  private String nome;
  private int duracao;
  private int intensidade;

  public Efeito(String nome, int duracao, int intensidade) {
    this.nome = nome;
    this.duracao = duracao;
    this.intensidade = intensidade;
  }
  
  public abstract void aplicar(Entidade entidade);
  public void aplicar(List<Entidade> entidades) {
    for (Entidade e : entidades) {
      aplicar(e);
    }
  }

  public String getNome() {
    return nome;
  }
  public int getDuracao() {
    return duracao;
  }
  public int getIntensidade() {
    return intensidade;
  }
}