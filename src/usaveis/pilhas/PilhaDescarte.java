package usaveis.pilhas;

import usaveis.Mao;

public class PilhaDescarte extends Pilha {
  public PilhaDescarte() {
    super();
  }
  
  public void removeMao(Mao mao){
    for(int i = 0; i < mao.cartas.size(); i++){
      pilha.add(mao.cartas.remove(i));
    }
  }
}
