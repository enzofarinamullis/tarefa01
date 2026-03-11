
public class FilaCartasEscudo {
  private class No {
    CartaEscudo carta;
    No proximo;

    public No(CartaEscudo carta) {
      this.carta = carta;
    }
  }
  private No frente;
  private No tras;
  int tamanho;

  public FilaCartasEscudo() {
    this.frente = null;
    this.tras = null;
    this.tamanho = 0;
  }

  public void enfileirar(CartaEscudo carta) {
    No novoNo = new No(carta);
    if (tamanho == 0) {
      frente = novoNo;
      tras = novoNo;
    }
    else {
      tras.proximo = novoNo;
      tras = novoNo;
    }
    tamanho++;
  }

  public CartaEscudo desenfileirar() {
    if (tamanho == 0) {
      throw new IllegalArgumentException("Sem carta de escudo disponíveis.");
    }

    else {
      CartaEscudo cartaRetirada = frente.carta;
      frente = frente.proximo;
      if (frente == null) {
        tras = null;
      }
      tamanho--;
      return cartaRetirada;
    }

  }

}
