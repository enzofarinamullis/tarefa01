package sistematurnos.batalhas;

import dados.Dados;
import dados.inimigos.Slime;
import sistematurnos.Batalha;

public class BatalhaTresSlimes extends Batalha {
  public BatalhaTresSlimes(Dados dados){
    super(dados);
  }
  
  protected void adicionarInimigos(){
    Slime slime = new Slime();
    this.dados.listaInimigos.adicionarInimigo(slime);
    slime = new Slime();
    dados.listaInimigos.adicionarInimigo(slime);
    slime = new Slime();
    dados.listaInimigos.adicionarInimigo(slime);
  }
}
