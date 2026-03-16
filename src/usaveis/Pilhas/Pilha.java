package usaveis.Pilhas;
import usaveis.Cartas;

public abstract class Pilha {
  int tamanho;
  No raiz;
  
  public Pilha() {
    tamanho = 0;
    raiz = null;
  }
  
  public class No {
    Cartas carta;
    No prox;
    
    public No (Cartas carta) {
      this.carta = carta;
      this.prox = null;
    }
  }
  
  public void adicionaCartaInicio(Cartas carta) {
    No adicionar = new No(carta);
    if(tamanho == 0) {
      raiz = new No(carta);
      return;
    }
    adicionar.prox = raiz;
    raiz = adicionar;
    tamanho++;
  }
  
  public void adicionarCartaFinal(Cartas carta){
    No adicionar = new No(carta);
    No atual = raiz;
    if(tamanho == 0){
      raiz = adicionar;
    }
    for(int i = 0; i < tamanho; i++){
      atual.prox = adicionar;
    }
  }
  
  public Cartas removeCartaTopo() {
    Cartas cartaTopo = raiz.carta;
    raiz = raiz.prox;
    return cartaTopo;
  }
}
