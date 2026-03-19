package cenas;
import dados.Dados;
import dados.Inimigo;

public class Slime extends Cena {
  
  public Slime(Dados dados) {
    this.nome = "Slime Selvagem";
    Inimigo slime = new dados.inimigos.Slime();
    dados.listaInimigos.adicionarInimigo(slime);
  }

  
  /* Atualiza a Cena */
  public void atualizaCena(){
  }
}