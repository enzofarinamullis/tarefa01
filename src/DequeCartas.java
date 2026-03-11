public class DequeCartas {
  private class No {
    private CartaDano carta;
    private No anterior;
    private No proximo;

    public No (CartaDano carta) {
      this.carta = carta;
    }
    public CartaDano pegarCarta() {
      return carta;
    }
    public No pegarNoAnterior() {
      return anterior;
    }
    public No pegarProximoNo() {
      return  proximo;
    }
    public void  modificarCarta(CartaDano carta) {
      this.carta = carta; 
    }
    public void modificarProximo(No proximo) {
      this.proximo = proximo;
    }
    public void modificarAnterior(No anterior) {
      this.anterior = anterior;
    }
  }

  private No cabeca;
  private No calda;
  private int tamanho;

  public DequeCartas() {
    cabeca = null;
    calda = null;
    tamanho = 0;
  }

  public No buscarNo(String nome) {
    if (tamanho == 0) {
      throw new IllegalArgumentException("Deque vazio");
    } else {
      No atual = cabeca;
      while (atual != null) {
        if (atual.carta.nome.equals(nome)) {

          return atual;
        }
        atual = atual.proximo;
      }
      return null;
    }
  }

  public void passar_carta_para_o_final(String nome) {
    if (tamanho == 0) {
      throw  new IllegalArgumentException("Deque vazio");
    } else {
      No NoBuscado = buscarNo(nome);
      if (NoBuscado == null) {
        return;
      } else {
        //Já está no final
        if (NoBuscado == calda) return;
        //Removendo o nó da posicao
        if (NoBuscado == cabeca) {
          cabeca = cabeca.proximo;
          if (cabeca != null) {
            cabeca.anterior = null;
          }
        } else {
          NoBuscado.anterior.proximo = NoBuscado.proximo;
          NoBuscado.proximo.anterior = NoBuscado.anterior;
        }

        //insere o no no final:
        NoBuscado.anterior = calda;
        NoBuscado.proximo = null;
        if (calda != null) {
          calda.proximo = NoBuscado;
        }
        calda = NoBuscado;
      }
    }
  }

  public void adicionar_no_inicio(CartaDano carta) {
    No novoNo = new No(carta);
    if (tamanho == 0) {
      cabeca = novoNo;
      calda = novoNo;
    } else {
      novoNo.proximo = cabeca;
      cabeca.anterior = novoNo;
      cabeca = novoNo;
    }
    tamanho++;

  }

  public void adicionar_no_fim(CartaDano carta) {
    No novoNo = new No(carta);
    if (tamanho == 0) {
      cabeca = novoNo;
      calda = novoNo;
    } else {
      novoNo.anterior = calda;
      calda.proximo = novoNo;
      calda = novoNo;
    }
    tamanho++;
  }

  public CartaDano removerDoInicio() {
    if (tamanho == 0) {
      throw new IllegalArgumentException("Deque vazio");
    } else {
      CartaDano retirada = cabeca.carta;
      if (cabeca == calda) {
        cabeca = null;
        calda = null;
      } else {
        cabeca = cabeca.proximo;
        cabeca.anterior = null;
      }
      tamanho--;
      return retirada;
    }
  }

  public CartaDano removerDoFinal() {
    if (tamanho == 0) {
      throw new IllegalArgumentException("Deque vazio");
    } else {
      CartaDano retirada = calda.carta;
      if (cabeca == calda) {
        cabeca = null;
        calda = null;
      } else {
        calda = calda.anterior;
        calda.proximo = null;
      }
      tamanho--;
      return retirada;
    }
  }
  public void printDoDeck() {
    No atual = cabeca;
    while (atual != null) {
      if (atual.proximo != null) {
        System.out.print(atual.carta.nome + " <-> ");
      } else {
        System.out.print(atual.carta.nome);
      }
      atual = atual.proximo;
    }
    System.out.println();
  }

}