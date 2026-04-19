package sistematurnos.batalhas;

import dados.Dados;
import dados.inimigos.Slime;
import sistematurnos.Batalha;

public class BatalhaUmSlime extends Batalha {
  public BatalhaUmSlime(Dados dados){
    super(dados);
  }
  
  protected void adicionarInimigos(){
    Slime slime = new Slime();
    this.dados.listaInimigos.adicionarInimigo(slime);
  }
}
