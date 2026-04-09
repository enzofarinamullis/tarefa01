package cenas;
import dados.Dados;
import dados.Inimigo;

public class TresSlimes extends Cena {
  
  public TresSlimes(Dados dados) {
    this.nome = "Três Slimes Selvagens";
    for(int i = 0; i < 3; i++) {
      Inimigo slime = new dados.inimigos.Slime();
      dados.listaInimigos.adicionarInimigo(slime);
    }
    Inimigo lesma = new dados.inimigos.lesmaVenenosa();
    dados.listaInimigos.adicionarInimigo(lesma);
  }

  
  /* Atualiza a Cena */
  public void atualizaCena(){
  }
}