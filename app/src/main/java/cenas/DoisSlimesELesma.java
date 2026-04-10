package cenas;
import dados.Dados;
import dados.Inimigo;

public class DoisSlimesELesma extends Cena {
  
  public DoisSlimesELesma(Dados dados) {
    this.nome = "Dois Slimes Selvagens e uma Lesma Venenosa";
    for(int i = 0; i < 2; i++) {
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