package usaveis.pilhas;

import constantes.Cores;
import java.util.NoSuchElementException;
import java.util.Random;
import usaveis.*;
import usaveis.danos.*;
import usaveis.escudos.*;

public class PilhaCompra extends Pilha {
  /* ja temos a pilha exatamente pronta */
  public PilhaCompra() {
    super();
    /* criamos o deque */
    Carta carta = new espadaCurta();
    pilha.add(carta);
    carta = new espadaMedia();
    pilha.add(carta);
    carta = new espadaLonga();
    pilha.add(carta);
    carta = new espadaEL();
    pilha.add(carta);
    carta = new escudoPequeno();
    pilha.add(carta);
    carta = new escudoMedio();
    pilha.add(carta);
    carta = new escudoGrande();
    pilha.add(carta);
    carta = new escudoEL();
    pilha.add(carta);
  }
  
  public void embraralhaPlha() {
    Pilha temp = new Pilha();
    Carta atual;
    Random aleatorio = new Random();
    while(pilha.size() != 0){
      /* pegamos uma carta aleatoria da pilha */
      atual = pilha.remove(aleatorio.nextInt(pilha.size()));
      /* colocamos na pilha temporaria */
      temp.pilha.add(atual);
      /* fazemos isto ate zerar a pilha */
    }
    /* copiamos a referencia para a pilha embaralhada */
    pilha = temp.pilha;
  }
  
  private void transfereDescarte(PilhaDescarte pilhaDescarte){
    Carta carta;
    while(!pilhaDescarte.pilha.isEmpty()){
      carta = pilhaDescarte.pilha.removeFirst();
      pilha.add(carta);
    }
    System.out.println("Transferindo:");
    System.out.println(Cores.COR_CIMENTO_3 + "Pilha de Descarte" +
      Cores.ANSI_RESET + "->" + Cores.ANSI_BLUE + "Pilha Compra" + Cores.ANSI_RESET);
  }
  
  public void compraCarta(Mao mao, PilhaDescarte pilhaDescarte, int qnt) {
    try {
      for (int i = 0; i < qnt; i++) {
        Carta carta = pilha.removeFirst();
        mao.cartas.add(carta);
      }
    } catch (NoSuchElementException e) {
      /* caso estiver vazia */
      /* colocamos a pilha de descarte embaralhada */
      pilhaDescarte.removeMao(mao); // removemos todas as cartas da mao
      transfereDescarte(pilhaDescarte);
      embraralhaPlha();
      compraCarta(mao, pilhaDescarte, qnt);
    }
  }
}
