package usaveis.pilhas;

import usaveis.*;
import usaveis.danos.*;
import usaveis.escudos.*;

public class PilhaCompra extends Pilha {
  /* ja temos a pilha exatamente pronta */
  public PilhaCompra() {
    super();
    /* criamos o deque */
    Cartas carta = new espadaCurta();
    adicionaCartaInicio(carta);
    carta = new espadaMedia();
    adicionaCartaInicio(carta);
    carta = new espadaLonga();
    adicionaCartaInicio(carta);
    carta = new espadaEL();
    adicionaCartaInicio(carta);
    carta = new escudoPequeno();
    adicionaCartaInicio(carta);
    carta = new escudoMedio();
    adicionaCartaInicio(carta);
    carta = new escudoGrande();
    adicionaCartaInicio(carta);
    carta = new escudoEL();
    adicionaCartaInicio(carta);
  }
}
