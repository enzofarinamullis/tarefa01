package usaveis.pilhas;

import usaveis.Mao;

public class PilhaDescarte extends Pilha {
  public PilhaDescarte() {
    super();
  }
  
  public void removeMao(Mao mao){
    while(!mao.cartas.isEmpty()){
      pilha.add(mao.cartas.removeFirst());
    }
  }
}
