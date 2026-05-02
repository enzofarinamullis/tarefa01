package sistematurnos.lojas;

import usaveis.cartas.Carta;

public class NoLoja {
  private Carta carta;
  private int custo;

  protected NoLoja(Carta carta, int custo){
    this.carta = carta;
    this.custo = custo;
  }

  public String getNome(){
    return carta.getNome();
  }

  public String getDescricao(){
    return carta.getDescricao();
  }

  public int getCusto(){
    return custo;
  }

  public Carta getCarta(){
    return carta;
  }
}
