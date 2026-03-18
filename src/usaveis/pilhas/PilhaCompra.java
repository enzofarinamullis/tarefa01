package usaveis.pilhas;

import usaveis.*;
import usaveis.danos.*;
import usaveis.escudos.*;
import java.util.Random;

public class PilhaCompra extends Pilha {
  /* ja temos a pilha exatamente pronta */
  public PilhaCompra() {
    super();
    /* criamos o deque */
    Cartas carta = new espadaCurta();
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
    Pilha temp = new Pilha;
    Cartas atual;
    Random aleatorio = new Random();
    while(pilha.size() != 0){
      /* pegamos uma carta aleatoria da pilha */
      atual = pilha.get(aleatorio.nextInt(pilha.size()));
      /* colocamos na pilha temporaria */
      temp.pilha.add(atual);
      /* fazemos isto ate zerar a pilha */
    }
    /* copiamos a referencia para a pilha embaralhada */
    pilha = temp.pilha;
  }
}
