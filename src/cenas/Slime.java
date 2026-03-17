package cenas;
import dados.Dados;
import dados.Inimigo;
import dados.ListaInimigos;

public class Slime extends Cena {
  
  public Slime(Dados dados) {
    this.nome = "Slime Selvagem";
    Inimigo slime = new Inimigo("Slime Selvagem", 30, 10, 1);
    dados.listaInimigos.adicionarInimigo(slime);
  }

  
  /* Atualiza a Cena */
  public void atualizaCena(){
  }
}