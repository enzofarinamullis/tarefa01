public class NoCartas {
  String tipo;
  CartaDano cartaDano;
  CartaEscudo cartaEscudo;
  NoCartas prox;
  NoCartas ant;

  public NoCartas(String tipo){
    if(tipo == "ESCUDO"){
      this.cartaDano = null;
    }
    if(tipo == "DANO"){
      this.cartaEscudo = null;
    }
    this.tipo = tipo;
    this.prox = null;
    this.ant = null;
  }

  public void atribuiCartaEscudo(CartaEscudo cartaEscudo){
    this.cartaEscudo = cartaEscudo;
  }

  public void atribuiCartaDano(CartaDano cartaDano){
    this.cartaDano = cartaDano;
  }
}
