package sistematurnos.batalhas;

import dados.Dados;
import dados.inimigos.Slime;
import sistematurnos.Batalha;

public class BatalhaNSlimes extends Batalha {
  int quantidadeSlimes;
  public BatalhaNSlimes(Dados dados, int quantidadeSlimes){
    super(dados);
    this.quantidadeSlimes = quantidadeSlimes;
  }
  
  protected void adicionarInimigos(){
    for(int i = 0; i < quantidadeSlimes; i++) {
      Slime slime = new Slime();
      this.dados.listaInimigos.adicionarInimigo(slime);
    }
  }
}
