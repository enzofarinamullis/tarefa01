package usaveis;
import usaveis.Cartas;
import constantes.Cores;

public class Mao{
  int qntCartas;
  Cartas raiz;

  public Mao(){
    this.qntCartas = 0;
    this.raiz = null;
  }

  public void adicionarCartaMao(Cartas carta){
    Cartas atual = raiz;
    if(qntCartas == 0){
      raiz = carta;
      qntCartas++;
      return;
    }
    else if(qntCartas == 1){
      raiz.prox = carta;
      qntCartas++;
      return;
    }
    int indice = qntCartas - 1;
    for(int i = 0; i < indice; i++){
      atual = atual.prox;
    }
    /* achamos o ultimo elemento */
    atual.prox = carta;
    qntCartas++;
  }

  public void printMao(){
    Cartas atual = raiz;
    int num = 1;
    while(atual != null){
      Cores.cprintInt(Cores.ANSI_BLUE, num);
      Cores.cprint(Cores.ANSI_BLUE, " - ");
      System.out.print(atual.nome + "\n");
      atual = atual.prox;
      num++;
    }
  }

  public Cartas buscaCartaNum(int num){
    int indice = num - 1;
    Cartas atual = raiz;
    for(int i = 0; i < indice; i++){
      atual = atual.prox;
    }
    return atual;
  }
}

