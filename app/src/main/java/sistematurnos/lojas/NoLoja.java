package sistematurnos.lojas;

import usaveis.cartas.Carta;

public class NoLoja {
  private Carta carta;
  private int custo;
  private String descricao;

  protected NoLoja(Carta carta, int custo, String descicao){
    this.carta = carta;
    this.custo = custo;
    this.descricao = "uma descrição qualquer";
  }

  public String getNome(){
    return carta.getNome();
  }

  public String getDescricao(){
    return descricao;
  }

  public int getCusto(){
    return custo;
  }
}
